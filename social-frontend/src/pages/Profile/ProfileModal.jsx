import { useFormik } from "formik";
import React from "react";
import {
  Button,
  TextField,
  Modal,
  Box,
  IconButton,
  Avatar,
} from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { updateProfileAction } from "../../Redux/Auth/auth.action";
import CloseIcon from "@mui/icons-material/Close";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 2,
  outline: "none",
  overFlow: "scroll-y",
  borderRadius: 3,
};

export default function ProfileModal({ open, handleClose }) {
  const dispatch = useDispatch();
  const { auth } = useSelector((store) => store);

  const formik = useFormik({
    initialValues: {
      firstName: auth.user?.firstName,
      lastName: auth.user?.lastName,
    },
    onSubmit: (values) => {
      console.log("🚀 ~ ProfileModal ~ values:", values);
      dispatch(updateProfileAction(values));
    },
  });

  const handleSubmit = (values) => {
    console.log("🚀 ~ handleSubmit ~ values:", values);
  };
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-3">
                <IconButton onClick={handleClose}>
                  <CloseIcon />
                </IconButton>
                <p>Edit Profile</p>
              </div>
              <Button type="submit">Save</Button>
            </div>
            <div className="h-[15rem]">
              <img
                className="w-full h-full rounded-t-md"
                src="https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&w=600"
                alt="profile"
              />
            </div>
            <div className="pl-5">
              <Avatar
                className="transform -translate-y-24"
                sx={{ width: "10rem", height: "10rem" }}
                src="https://cdn.sanity.io/images/m5wq2dq6/production/e51ad49e19bd01850398d26e3c5a8df2a91ba773-960x960.jpg"
              />
            </div>
            <div className="space-y-3">
              <TextField
                id="firstName"
                name="firstName"
                label="First Name"
                value={formik.values.firstName}
                onChange={formik.handleChange}
                fullWidth
              />
              <TextField
                id="lastName"
                name="lastName"
                label="Last Name"
                value={formik.values.lastName}
                onChange={formik.handleChange}
                fullWidth
              />
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}
