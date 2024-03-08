import axios from "axios";
import { API_BASE_URL, api } from "../../config/api";
import {
  GET_PROFILE_SUCCESS,
  GET_PROFILE_FAILURE,
  LOGIN_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  REGISTER_FAILURE,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  UPDATE_PROFILE_SUCCESS,
  UPDATE_PROFILE_FAILURE,
  GET_PROFILE_REQUEST,
  UPDATE_PROFILE_REQUEST,
  SEARCH_USER_REQUEST,
  SEARCH_USER_SUCCESS,
  SEARCH_USER_FAILURE,
} from "./auth.actionType";

export const loginUserAction = (loginData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST });
  try {
    const { data } = await axios.post(
      `${API_BASE_URL}/auth/login`,
      loginData.data
    );
    if (data.token) {
      localStorage.setItem("jwt", data.token);
    }
    console.log("🚀 ~ loginUserAction ~ data:", data);
    dispatch({ type: LOGIN_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ loginUserAction ~ error:", error);
    dispatch({ type: LOGIN_FAILURE, payload: error });
  }
};

export const registerUserAction = (registerData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST });
  try {
    const { data } = await axios.post(
      `${API_BASE_URL}/auth/register`,
      registerData.data
    );
    if (data.token) {
      localStorage.setItem("jwt", data.token);
    }
    console.log("🚀 ~ registerUserAction ~ data:", data);
    dispatch({ type: REGISTER_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: REGISTER_FAILURE, payload: error });
    console.log("🚀 ~ registerUserAction ~ error:", error);
  }
};

export const getProfileAction = (jwt) => async (dispatch) => {
  dispatch({ type: GET_PROFILE_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/users/profile`);
    console.log("🚀 ~ getProfileAction ~ data:", data);
    dispatch({ type: GET_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: GET_PROFILE_FAILURE, payload: error });
    console.log("🚀 ~ getProfileAction ~ error:", error);
  }
};

export const updateProfileAction = (reqUser) => async (dispatch) => {
  dispatch({ type: UPDATE_PROFILE_REQUEST });
  try {
    const { data } = await api.put(`${API_BASE_URL}/users`, reqUser);
    console.log("🚀 ~ updateUserAction ~ data:", data);
    dispatch({ type: UPDATE_PROFILE_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ updateUserAction ~ error:", error);
    dispatch({ type: UPDATE_PROFILE_FAILURE, payload: error });
  }
};

export const searchUser = (query) => async (dispatch) => {
  dispatch({ type: SEARCH_USER_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/users/search?query=${query}`);
    console.log("🚀 ~ searchUser ~ data:", data);
    dispatch({ type: SEARCH_USER_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: SEARCH_USER_FAILURE, payload: error });
    console.log("🚀 ~ searchUser ~ error:", error);
  }
};
