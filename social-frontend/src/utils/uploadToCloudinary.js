const cloud_name = "pblgllgs";

const upload_preset = "social-app";

export const uploadToCloudinary = async (pics, fileType) => {
  if (pics && fileType) {
    const data = new FormData();
    data.append("file", pics);
    data.append("upload_preset", upload_preset);
    data.append("cloud_name", cloud_name);
    const response = await fetch(
      `https://api.cloudinary.com/v1_1/${cloud_name}/${fileType}/upload`,
      { method: "POST", body: data }
    );
    console.log(response);

    const fileData = await response.json();
    console.log(fileData.url);
    return fileData.url;
  } else {
    console.log("error upload");
  }
};
