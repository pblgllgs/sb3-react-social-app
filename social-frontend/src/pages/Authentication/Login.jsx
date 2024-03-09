import { ErrorMessage, Field, Form, Formik } from "formik";
// import React, { useState } from "react";
// import * as Yup from "yup";
import { Button, TextField } from "@mui/material";
import { useDispatch } from "react-redux";
import { loginUserAction } from "../../Redux/Auth/auth.action";
import { useNavigate } from "react-router-dom";

// const validationSchema = {
//   email: Yup.string().email("Invalid email").required("Email is required"),
//   password: Yup.string()
//     .min(8, "must be contain 8 characters")
//     .required("Password is required"),
// };

const initialValues = { email: "pbl.gllgs@gmail.com", password: "pass" };

const Login = () => {
  // const [formValues, setFormValues] = useState();
  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleSubmit = (values) => {
    // console.log("🚀 ~ handleSubmit ~ values:", values);
    dispatch(loginUserAction({ data: values }));
  };
  return (
    <>
      <Formik
        onSubmit={handleSubmit}
        // validationSchema={validationSchema}
        initialValues={initialValues}
      >
        <Form className="space-y-5">
          <div className="space-y-5">
            <div>
              <Field
                as={TextField}
                type="email"
                name="email"
                variant="outlined"
                placeholder="Email"
                fullWidth
              />
              <ErrorMessage
                name="email"
                component="div"
                className="text-red-500"
              />
            </div>
            <div>
              <Field
                as={TextField}
                type="password"
                variant="outlined"
                name="password"
                placeholder="Password"
                fullWidth
              />
              <ErrorMessage
                name="password"
                component="div"
                className="text-red-500"
              />
            </div>
          </div>
          <Button
            sx={{ padding: ".8rem 0rem" }}
            fullWidth
            type="submit"
            variant="contained"
            color="primary"
          >
            Login
          </Button>
        </Form>
      </Formik>
      <div className="flex gap-2 items-center justify-center pt-5">
        <p>If you don't have account ? </p>
        <Button onClick={() => navigate("/register")}>REGISTER</Button>
      </div>
    </>
  );
};

export default Login;
