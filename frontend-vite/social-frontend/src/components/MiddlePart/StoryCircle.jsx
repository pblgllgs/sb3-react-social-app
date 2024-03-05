import React from 'react'
import { Avatar } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const StoryCircle = () => {
  return (
    <div>
        <div className="flex flex-col items-center mr-4 cursor-pointer">
          <Avatar
            sx={{ width: "5rem", height: "5rem" }}
            src="https://cdn.pixabay.com/photo/2016/10/15/05/02/girls-1741925_640.jpg"
            className="flex flex-col items-center mr-4 cursor-pointer"
          >
            <AddIcon sx={{ fontSize: "3rem" }} />
          </Avatar>
          <p>pblgllgs</p>
        </div>
    </div>
  )
}

export default StoryCircle