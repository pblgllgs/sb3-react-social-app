import React from "react";
import { navigationMenu } from "./SidebarNavigation";
import { Divider, Avatar, Button, MenuItem, Menu, Card } from "@mui/material";
import MoreVertIcon from "@mui/icons-material/MoreVert";

const Sidebar = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Card className="card h-screen flex flex-col justify-between py-5">
      <div className="space-y-8 pl-5">
        <div className="">
          <span className="logo font-bold text-xl">World Social</span>
        </div>
        <div className="space-y-8">
          {navigationMenu.map((item,i) => {
            return (
              <div key={i}>
                <div className="cursor-pointer flex space-x-3 items-center">
                  {item.icon}
                  <p className="text-xl">{item.title}</p>
                </div>
              </div>
            );
          })}
        </div>
      </div>
      <div>
        <Divider />
        <div className="pl-5 flex items-center justify-between pt-5">
          <div className="flex items-center space-x-3">
            <Avatar src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" />
            <div>
              <p className="font-bold">Pblgllgs</p>
              <p className="opacity-70">@pblgllgs</p>
            </div>
          </div>
          <Button
            id="basic-button"
            aria-controls={open ? "basic-menu" : undefined}
            aria-haspopup="true"
            aria-expanded={open ? "true" : undefined}
            onClick={handleOpen}
          >
            <MoreVertIcon />
          </Button>
          <Menu
            id="basic-menu"
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
            MenuListProps={{
              "aria-labelledby": "basic-button",
            }}
          >
            <MenuItem onClick={handleClose}>Logout</MenuItem>
          </Menu>
        </div>
      </div>
    </Card>
  );
};

export default Sidebar;
