import React, { useState } from "react";
import {
  Card,
  CardHeader,
  Avatar,
  IconButton,
  CardMedia,
  CardContent,
  Typography,
  CardActions,
  Divider,
} from "@mui/material";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ShareIcon from "@mui/icons-material/Share";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import ChatBubbleIcon from "@mui/icons-material/ChatBubble";
import { red } from "@mui/material/colors";

const PostCard = ({ post }) => {
  const [showComments, setShowComments] = useState(false);
  const handleShowComments = () => {
    setShowComments(!showComments);
  };
  return (
    <Card className="">
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={post.user.firstName + " " + post.user.lastName}
        subheader={
          "@" +
          post.user.firstName.toLowerCase() +
          "_" +
          post.user.lastName.toLowerCase()
        }
      />
      <CardMedia
        component="img"
        height="194"
        image={post.image}
        alt="Paella dish"
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {post.caption}
        </Typography>
      </CardContent>

      <CardActions className="flex justify-between" disableSpacing>
        <div>
          <IconButton>
            {true ? <FavoriteIcon /> : <FavoriteBorderIcon />}
          </IconButton>
          <IconButton>
            <ShareIcon />
          </IconButton>
          <IconButton onClick={handleShowComments}>
            <ChatBubbleIcon />
          </IconButton>
        </div>
        <div>
          <IconButton>
            {true ? <BookmarkIcon /> : <BookmarkBorderIcon />}
          </IconButton>
        </div>
      </CardActions>
      {showComments && (
        <section>
          <div className="flex items-center space-x-5 mx-3 my-5">
            <Avatar src={post.user?.photo} sx={{}} />
            <input
              onKeyPress={(e) => {
                if (e.key === "Enter") {
                  console.log("enter pressed");
                }
              }}
              className="w-full outline-none bg-transparent border border-[#3b4054] rounded-full px-5 py-2"
              placeholder="Write your comment..."
              type="text"
            />
          </div>
          <Divider />
          <div className="mx-3 space-y-2 my-5 text-sx">
            <div className="flex justify-between items-center">
              <div className="flex items-center space-x-5">
                <Avatar
                  sx={{ height: "2rem", width: "2rem", fontSize: "8rem" }}
                >
                  C
                </Avatar>
                <p>nice image</p>
              </div>
            </div>
          </div>
        </section>
      )}
    </Card>
  );
};

export default PostCard;
