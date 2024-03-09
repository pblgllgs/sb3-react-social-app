import React, { useState } from "react";
import { Card, CardHeader, Avatar } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { createChat } from "../../Redux/Message/message.action";
import { searchUserFind } from "../../Redux/Auth/auth.action";

const SearchUser = () => {
  const [username, setUsername] = useState();
  const dispatch = useDispatch();
  const searchUser = useSelector((store) => store.auth.searchUser);


  const handleSearchUser = (e) => {
    setUsername(e.target.value);
    // console.log("search user...");
    dispatch(searchUserFind(username));
  };

  const handleClick = (id) => {
    const chatDto = {
      userId: id,
    };
    dispatch(createChat(chatDto));
  };

  return (
    <div>
      <div className="py-5 relative">
        <input
          type="text"
          className="bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full"
          placeholder="search user..."
          onChange={handleSearchUser}
        />
        {username &&
          searchUser.map((user, i) => {
            return (
              <Card
                key={i}
                className="absolute w-full z-10 top-[4.5rem] cursor-pointer"
              >
                <CardHeader
                  onClick={() => {
                    handleClick(user.id);
                    setUsername("");
                  }}
                  avatar={
                    <Avatar src={user.image.url} />
                  }
                  title={user.firstName + " " + user.lastName}
                  subheader={
                    "@" +
                    user.firstName.toLowerCase() +
                    "_" +
                    user.lastName.toLowerCase()
                  }
                ></CardHeader>
              </Card>
            );
          })}
      </div>
    </div>
  );
};

export default SearchUser;
