import React from "react";
import { Grid,Card } from "@mui/material";
import Login from "./Login";
import Register from "./Register";

const Authentication = () => {
  return (
    <div>
      <Grid container>
        <Grid className="h-screen overflow-hidden" item xs={7}>
          <img
            className="h-full w-full"
            alt="social-init"
            src="https://cdn.pixabay.com/photo/2018/02/08/11/13/network-3139201_1280.jpg"
          />
        </Grid>
        <Grid item xs_={5}>
          <div className="px-20 flex flex-col justify-center h-full">
            <Card className="card p-8">
              <div className="flex flex-col items-center mb-5 space-y-1">
                <h1 className="logo text-center">World Social</h1>
                <p className="text-center text-sm w-[70&]">
                  Connecting lives, experiences and friendship and mush more...!!!
                </p>
              </div>
              {/* <Login/> */}
              <Register/>
            </Card>
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default Authentication;
