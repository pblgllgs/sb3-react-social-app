import React, { useState } from "react";
import {
  Modal,
  Box,
  Avatar,
  Button,
  IconButton,
  Backdrop,
  CircularProgress,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useFormik } from "formik";
import ImageIcon from "@mui/icons-material/Image";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import { uploadToCloudinary } from "../../utils/uploadToCloudinary";
import { createPostAction} from "../../Redux/Post/post.action";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  outline: "none",
  borderRadius: ".6rem",
};

const CreatePostModel = ({ open, handleClose }) => {
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);
  const [selectedImage, setSelectedImage] = useState(null);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const formik = useFormik({
    initialValues: {
      caption: "",
      video: "",
      image: "",
    },
    onSubmit: (values) => {
      console.log("🚀 ~ ProfileModal ~ values:", selectedVideo);
      dispatch(createPostAction(values))
    },
  });

  const handleSelectImage = async (event) => {
    setIsLoading(true);
    const imageUrl = await uploadToCloudinary(event.target.files[0], "image");
    setSelectedImage(imageUrl);
    setIsLoading(false);
    formik.setFieldValue("image", imageUrl);
  };

  const handleSelectVideo = async (event) => {
    setIsLoading(true);
    const videoUrl = await uploadToCloudinary(event.target.files[0], "video");
    setSelectedVideo(videoUrl);
    setIsLoading(false);
  };

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <form onSubmit={formik.handleSubmit}>
          <div>
            <div className="flex items-center space-x-4 ">
              <div className="flex items-center space-x-3">
                <Avatar src={auth.user?.image.url} />
                <div>
                  <h1 className="font-bold text-lg white">
                    {auth.user?.firstName + " " + auth.user?.lastName}
                  </h1>
                  <p className="text-sm white">
                    {"@" +
                      auth.user?.firstName.toLowerCase() +
                      "_" +
                      auth.user?.lastName.toLowerCase()}
                  </p>
                </div>
              </div>
              <Button type="submit" className="white">Save</Button>
            </div>
            <textarea
              className="outline-none w-full mt-5 p-2 bg-transparent border border-[#3b4054] rounded-sm white"
              name="caption"
              value={formik.values.caption}
              onChange={formik.handleChange}
              placeholder="Write a caption"
              rows="4"
            ></textarea>
            <div className="flex space-x-5 items-center mt-5">
              <div>
                <input
                  type="file"
                  accept="image/*"
                  onChange={handleSelectImage}
                  style={{ display: "none" }}
                  id="image-input"
                />
                <label htmlFor="image-input">
                  <IconButton className="white" component="span" color="primary">
                    <ImageIcon />
                  </IconButton>
                </label>
                <span className="white">Image</span>
              </div>
              <div>
                <input
                  type="file"
                  accept="video/*"
                  onChange={handleSelectVideo}
                  style={{ display: "none" }}
                  id="video-input"
                />
                <label htmlFor="video-input">
                  <IconButton component="span" color="primary" >
                    <VideoCallIcon />
                  </IconButton>
                </label>
                <span className="white">Video</span>
              </div>
            </div>
            {selectedImage && (
              <div>
                <img className="h-[10rem]" src={selectedImage} alt="selected" />
              </div>
            )}
            <div className="flex w-full justify-end white">
              <Button
                variant="container"
                type="submit"
                sx={{ borderRadius: "1.5rem" }}
              >
                Post
              </Button>
            </div>
          </div>
        </form>
        <Backdrop
          sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={isLoading}
          onClick={handleClose}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
      </Box>
    </Modal>
  );
};

export default CreatePostModel;
