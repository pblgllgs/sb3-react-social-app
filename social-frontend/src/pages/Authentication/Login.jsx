import { ErrorMessage, Field, Form, Formik } from "formik";
import React, { useState } from "react";
import * as Yup from "yup";
import Button from "@mui/material/Button";

const Login = () => {
  const validationSchema = {
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string()
      .min(8, "must be contain 8 characters")
      .required("Password is required"),
  };

  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState([]);
  const handleSubmit = (values) => {
    console.log(values);
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
                type="email"
                name="email"
                variant="outlined"
                placeholder="Email"
              />
              <ErrorMessage
                name="email"
                component="div"
                className="text-red-500"
              />
            </div>
            <div>
              <Field
                type="password"
                variant="outlined"
                name="password"
                placeholder="Password"
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
    </>
  );
};

export default Login;
