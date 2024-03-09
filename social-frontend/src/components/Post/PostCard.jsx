import React, { useState } from "react";
import {
  Card,
  CardHeader,
  Avatar,
  IconButton,
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
import {
  createCommentAction,
  likePostAction,
} from "../../Redux/Post/post.action";
import { useDispatch,useSelector } from "react-redux";
import { isLikedByReqUser } from "../../utils/isLikedByReqUser";

const PostCard = ({ item }) => {
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);
  const [showComments, setShowComments] = useState(false);
  const handleShowComments = () => {
    setShowComments(!showComments);
  };

  const handleCreateComment = (content) => {
    const reqData = {
      postId: item.id,
      data: {
        content,
      },
    };
    dispatch(createCommentAction(reqData));
    // console.log(reqData);
  };

  const handleLikePost = () => {
    dispatch(likePostAction(item.id));
  };

  return (
    <Card className="my-3">
      <CardHeader
        avatar={
          <Avatar
            src={item.user.image.url}
            sx={{ bgColor: red[500] }}
            aria-label="recipe"
          >
            <p className="opacity-70">
              {"@" +
                item.user?.firstName.toLowerCase() +
                "_" +
                item.user?.lastName.toLowerCase()}
            </p>
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={item.user.firstName + " " + item.user.lastName}
        subheader={
          "@" +
          item.user.firstName.toLowerCase() +
          "_" +
          item.user.lastName.toLowerCase()
        }
      />
      <img className="w-full max-h-[30rem] object-cover object-top" src={item.image} alt="post" />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {item.caption}
        </Typography>
      </CardContent>

      <CardActions className="flex justify-between" disableSpacing>
        <div>
          <IconButton onClick={handleLikePost}>
            {isLikedByReqUser(auth.user.id, item) ? (
              <FavoriteIcon />
            ) : (
              <FavoriteBorderIcon />
            )}
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
            <Avatar src={item.user?.image.url} sx={{}} />
            <input
              onKeyPress={(e) => {
                if (e.key === "Enter") {
                  handleCreateComment(e.target.value);
                }
              }}
              className="w-full outline-none bg-transparent border border-[#3b4054] rounded-full px-5 py-2"
              placeholder="Write your comment..."
              type="text"
            />
          </div>
          <Divider />
          {item.comments.map((comment, i) => {
            return (
              <div key={i} className="mx-3 space-y-2 my-5 text-sx">
                <div className="flex items-center space-x-5">
                  <Avatar
                    sx={{ height: "2rem", width: "2rem", fontSize: "8rem" }}
                    src={comment.user.image.url}
                  ></Avatar>
                  <p>{comment.content}</p>
                </div>
              </div>
            );
          })}
        </section>
      )}
    </Card>
  );
};

export default PostCard;
