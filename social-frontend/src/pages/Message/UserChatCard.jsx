import React from "react";
import { Card, CardHeader, Avatar, IconButton } from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { useSelector } from "react-redux";

const UserChatCard = ({ item }) => {
  const user = useSelector((store) => store.auth.user);
  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar
            sx={{
              width: "3.5rem",
              height: "3.5rem",
              fontSize: "1.5rem",
              bgcolor: "#191c29",
              color: "rgb(88,199,250)",
            }}
            src={
              user?.id === item.users[0]?.id
                ? item.users[1].image.url
                : item.users[0].image.url
            }
          />
        }
        action={
          <IconButton>
            <MoreHorizIcon />
          </IconButton>
        }
        title={
          user?.id === item.users[1]?.id
            ? item.users[0].firstName + " " + item.users[0].lastName
            : item.users[1].firstName + " " + item.users[1].lastName
        }
        subheader={"new message"}
      ></CardHeader>
    </Card>
  );
};

export default UserChatCard;
