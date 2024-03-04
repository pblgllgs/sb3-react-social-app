import { ErrorMessage, Field, Form, Formik } from "formik";
import React, { useState } from "react";
import * as Yup from "yup";
import {
  Button,
  RadioGroup,
  Radio,
  FormControlLabel,
  TextField,
} from "@mui/material";

const Register = () => {
  
  const [gender, setGender] = useState("");

  const validationSchema = {
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string()
      .min(8, "must be contain 8 characters")
      .required("Password is required"),
  };

  const initialValues = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  };

  const handleSubmit = (values) => {
    values.gender = gender;
    console.log(values);
  };

  const handleChange = (e) => {
    setGender(e.target.value);
  };
  return (
    <>
      <Formik
        onSubmit={handleSubmit}
        // validationSchema={validationSchemaS}
        initialValues={initialValues}
      >
        <Form className="space-y-5">
          <div className="space-y-5">
            <div>
              <Field
                as={TextField}
                type="text"
                name="firstName"
                variant="outlined"
                placeholder="First Name"
                fullWidth
              />
              <ErrorMessage
                name="firstName"
                component="div"
                className="text-red-500"
              />
            </div>
            <div>
              <Field
                as={TextField}
                type="text"
                name="lastName"
                variant="outlined"
                placeholder="Last Name"
                fullWidth
              />
              <ErrorMessage
                name="lastName"
                component="div"
                className="text-red-500"
              />
            </div>
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
            <div>
              <RadioGroup
                onChange={handleChange}
                row
                aria-label="gender"
                name="gender"
              >
                <FormControlLabel
                  value="female"
                  control={<Radio />}
                  label="Female"
                />
                <FormControlLabel
                  value="male"
                  control={<Radio />}
                  label="Male"
                />
              </RadioGroup>
              <ErrorMessage
                name="gender"
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

export default Register;
