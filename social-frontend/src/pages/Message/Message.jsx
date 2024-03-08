import {
  Grid,
  Avatar,
  IconButton,
  Backdrop,
  CircularProgress,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import WestIcon from "@mui/icons-material/West";
import AddIcCallIcon from "@mui/icons-material/AddIcCall";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import SearchUser from "../../components/SearchUser/SearchUser";
import "./Message.css";
import UserChatCard from "./UserChatCard";
import ChatMessage from "./ChatMessage";
import { useDispatch, useSelector } from "react-redux";
import { createMessage, getAllChats } from "../../Redux/Message/message.action";
import { ChatBubbleOutline } from "@mui/icons-material";
import { uploadToCloudinary } from "../../utils/uploadToCloudinary";

const Message = () => {
  const dispatch = useDispatch();
  const { message, auth } = useSelector((store) => store);
  const [currentChat, setCurrentChat] = useState();
  console.log("🚀 ~ Message ~ currentChat:", currentChat);
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectedImage] = useState();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    dispatch(getAllChats());
  }, []);

  const handleSelectedImage = async (e) => {
    setLoading(true);
    const imgUrl = await uploadToCloudinary(e.target.files[0], "image");
    setSelectedImage(imgUrl);
    setLoading(false);
  };

  const handleCreateMessage = (value) => {
    const message = {
      chatId: currentChat.id,
      content: value,
      image: selectedImage,
    };
    dispatch(createMessage(message));
  };

  useEffect(() => {
    setMessages([...messages, message.message]);
  }, [message.message]);

  return (
    <div>
      <Grid container className="h-screen overflow-y-hidden">
        <Grid className="px-5" item xs={3}>
          <div className="flex h-full justify-between space-x-2">
            <div className="w-full">
              <div className="flex space-x-4 items-center py-5">
                <WestIcon />
                <h1 className="text-xl font-bold">Home</h1>
              </div>
              <div className="h-[83vh]">
                <div className="">
                  <SearchUser />
                </div>
                <div className="h-full spice-y-4 mt-5 overflow-y-scroll hideScrollBar">
                  {message.chats.map((item, i) => {
                    return (
                      <div
                        key={i}
                        onClick={() => {
                          setCurrentChat(item);
                          setMessages(item.messages);
                        }}
                      >
                        <UserChatCard key={i} item={item} />
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>
          </div>
        </Grid>
        <Grid className="h-full" item xs={9}>
          {currentChat ? (
            <div>
              <div className="flex justify-between items-center border-l p-5">
                <div className="flex items-center space-x-3">
                  <Avatar src={currentChat.users[1].image.url} />
                  <p>
                    {currentChat.users[1].firstName +
                      " " +
                      currentChat.users[1].lastName}
                  </p>
                </div>
                <div className="flex space-x-3">
                  <IconButton>
                    <AddIcCallIcon />
                  </IconButton>
                  <IconButton>
                    <VideoCallIcon />
                  </IconButton>
                </div>
              </div>
              <div className="hideScrollBar overflow-y-scroll h-[82ch] px-2 space-y-5 py-5">
                {messages.map((item, i) => {
                  return (
                    <>
                      <ChatMessage key={i} item={item} />
                    </>
                  );
                })}
              </div>
              <div className="sticky bottom-0 border-l">
                {selectedImage && (
                  <img
                    src={selectedImage}
                    className="w-[5rem] h-[5rem] object-cover px-2"
                    alt="selected"
                  />
                )}
                <div className="py-5 flex items-center justify-center space-x-5">
                  <input
                    onKeyPress={(e) => {
                      if (e.key === "Enter" && e.target.value) {
                        handleCreateMessage(e.target.value);
                        setSelectedImage(null)
                      }
                    }}
                    type="text"
                    className="bg-transparent border border-[#3b4054] rounded-full w-[90%] py-3 px-5"
                    placeholder="Type message..."
                  />
                  <div>
                    <input
                      type="file"
                      accept="image/*"
                      onChange={handleSelectedImage}
                      className="hidden"
                      id="image-input"
                    />
                    <label htmlFor="image-input">
                      <AddPhotoAlternateIcon />
                    </label>
                  </div>
                </div>
              </div>
            </div>
          ) : (
            <div className="h-full space-y-5 flex flex-col justify-center items-center">
              <ChatBubbleOutline sx={{ fontSize: "15rem" }} />
              <p className="text-xl font-semibold">No chat selected</p>
            </div>
          )}
        </Grid>
      </Grid>
      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  );
};

export default Message;
