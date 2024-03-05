import React from "react";
import { useParams } from "react-router-dom";
import { Avatar, Button, Box, Tabs, Tab, Card } from "@mui/material";
import PostCard from "../../components/Post/PostCard";
import UserReelCard from "../../components/Reels/UserReelCard";

const tabs = [
  {
    value: "post",
    label: "Posts",
  },
  {
    value: "reels",
    label: "Reels",
  },
  {
    value: "saved",
    label: "Saved",
  },
  {
    value: "repost",
    label: "Repost",
  },
];

const posts = [1, 1, 1, 1, 1];

const reels = [2, 2, 2, 2, 2];

const savedPosts = [2, 2, 2, 2, 2];

const Profile = () => {
  const [value, setValue] = React.useState("post");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const { id } = useParams();
  return (
    <Card className="my-10 w-[70%]">
      <div className="rounded-md ">
        <div className="h-[15rem]">
          <img
            className="w-full h-full rounded-t-md"
            src="https://cdn.pixabay.com/photo/2015/09/05/20/02/coding-924920_1280.jpg"
            alt="profile"
          />
        </div>
        <div className="px-5 flex justify-between items-start mt-5 h-[5rem]">
          <Avatar
            className="transform -translate-y-24"
            sx={{ width: "10rem", height: "10rem" }}
            src="https://cdn.sanity.io/images/m5wq2dq6/production/e51ad49e19bd01850398d26e3c5a8df2a91ba773-960x960.jpg"
          />
          {true ? (
            <Button sx={{ borderRadius: "20px" }} variant="outlined">
              Edit Profile
            </Button>
          ) : (
            <Button sx={{ borderRadius: "20px" }} variant="outlined">
              Follow
            </Button>
          )}
        </div>
        <div className="p-5">
          <div>
            <h1 className="py-1 font-bold text-xl">Pablo Gallegos</h1>
            <p>@pblgllgs</p>
          </div>
          <div className="flex gap-2 items-center py-3">
            <span>33 posts</span>
            <span>20 followers</span>
            <span>5 followings</span>
          </div>
          <div>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Mollitia
              iste, adipisci ullam omnis voluptatum ipsam odio soluta rerum qui
              excepturi, quis quam porro cum corporis ratione unde voluptatem
              eveniet voluptate.
            </p>
          </div>
        </div>
        <section>
          <Box sx={{ width: "100%", borderBottom: 1, borderColor: "divider" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="wrapped label tabs example"
            >
              {tabs.map((item, i) => {
                return (
                  <Tab key={i} value={item.value} label={item.label} wrapped />
                );
              })}
            </Tabs>
          </Box>
          <div className="flex justify-center">
            {value === "post" ? (
              <div className="space-y-5 w-[70%] my-10">
                {posts.map((item, i) => {
                  return (
                    <div className="border border-slate-100 rounded-md">
                      <PostCard key={i} />
                    </div>
                  );
                })}
              </div>
            ) : value === "reels" ? (
              <div className="flex gap-2 my-10 justify-normal flex-wrap">
                {reels.map((item, i) => {
                  return <UserReelCard key={i} />;
                })}
              </div>
            ) : value === "saved" ? (
              <div className="space-y-5 w-[70%] my-10">
                {savedPosts.map((item, i) => {
                  return (
                    <div className="border border-slate-100 rounded-md">
                      <PostCard key={i} />
                    </div>
                  );
                })}
              </div>
            ) : (
              <div>Repost</div>
            )}
          </div>
        </section>
      </div>
    </Card>
  );
};

export default Profile;
