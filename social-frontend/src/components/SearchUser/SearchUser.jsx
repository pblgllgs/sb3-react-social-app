import React, { useState } from "react";
import { Card, CardHeader, Avatar } from "@mui/material";

const SearchUser = () => {
  const [username, setUsername] = useState();
  const handleSearchUser = (e) => {
    setUsername(e.target.value);
    console.log("search user...");
  };

  const handleClick = (id) => {
    console.log(id);
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
        {username && (
          <Card className="absolute w-full z-10 top-[4.5rem] cursor-pointer">
            <CardHeader
              onClick={() => {
                handleClick();
                setUsername("");
              }}
              avatar={
                <Avatar src="https://images.pexels.com/photos/1974927/pexels-photo-1974927.jpeg?auto=compress&cs=tinysrgb&w=400" />
              }
              title="pblgllgs"
              subheader="friend"
            ></CardHeader>
          </Card>
        )}
      </div>
    </div>
  );
};

export default SearchUser;
