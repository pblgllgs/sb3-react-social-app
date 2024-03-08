import React, {useEffect, useState} from "react";
import {Avatar, Card, IconButton} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import ImageIcon from "@mui/icons-material/Image";
import VideocamIcon from "@mui/icons-material/Videocam";
import ArticleIcon from "@mui/icons-material/Article";
import StoryCircle from "./StoryCircle";
import PostCard from "../Post/PostCard";
import CreatePostModal from "../CreatePost/CreatePostModal";
import {useDispatch, useSelector} from "react-redux";
import {getAllPostAction} from "../../Redux/Post/post.action";

const story = [1, 1, 1];

const MiddlePart = () => {
  const [openCreatePostModal, setOpenCreatePostModal] = useState(false);
  const {post, auth} = useSelector(store => store)
  const openModal = () => setOpenCreatePostModal(true);
  const closeModal = () => setOpenCreatePostModal(false);
  const dispatch = useDispatch();

  const handleOpenCreatePostModal = () => {
    openModal();
    console.log("handle");
  };

  useEffect(() => {
    dispatch(getAllPostAction())
  }, [post.newComment])


  return (
    <div className="px-20">
      <section className="flex items-center p-5  rounded-b-md">
        <div className="flex flex-col items-center mr-4 cursor-pointer">
          <Avatar
            sx={{width: "5rem", height: "5rem"}}
            className="flex flex-col items-center mr-4 cursor-pointer"
          >
            <AddIcon sx={{fontSize: "3rem"}}/>
          </Avatar>
        </div>
        {story.map((item, i) => (
          <StoryCircle key={i}/>
        ))}
      </section>
      <Card className="p-5 mt-5">
        <div className="flex justify-between">
          <Avatar src={auth.user?.image.url}/>
          <input
            onClick={handleOpenCreatePostModal}
            readOnly
            type="text"
            className="outline-none w-[90%] bg-slate-100 rounded-full px-5 bg-transparent border-[#3b4054] border"
          />
        </div>
        <div className="flex justify-center space-x-9 mt-5">
          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModal}>
              <ImageIcon/>
            </IconButton>
            <span>Media</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModal}>
              <VideocamIcon/>
            </IconButton>
            <span>Video</span>
          </div>

          <div className="flex items-center">
            <IconButton color="primary" onClick={handleOpenCreatePostModal}>
              <ArticleIcon/>
            </IconButton>
            <span>Article</span>
          </div>
        </div>
      </Card>
      <div className="mt-5 space-y-5">
        {post.posts.map((item, i) => {
          return <PostCard key={i} item={item}/>;
        })}
      </div>
      <div>
        <CreatePostModal open={openCreatePostModal} handleClose={closeModal}/>
      </div>
    </div>
  );
};

export default MiddlePart;
