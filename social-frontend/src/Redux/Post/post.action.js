import { API_BASE_URL, api } from "../../config/api";
import {
  CREATE_COMMENT_FAILURE,
  CREATE_COMMENT_REQUEST, CREATE_COMMENT_SUCCESS,
  CREATE_POST_FAILURE,
  CREATE_POST_REQUEST,
  CREATE_POST_SUCCESS,
  GET_ALL_POST_FAILURE,
  GET_ALL_POST_REQUEST,
  GET_ALL_POST_SUCCESS,
  GET_USERS_POST_FAILURE,
  GET_USERS_POST_REQUEST,
  GET_USERS_POST_SUCCESS,
  LIKE_POST_FAILURE,
  LIKE_POST_REQUEST,
  LIKE_POST_SUCCESS,
} from "./post.actionType";

export const createPostAction = (postData) => async (dispatch) => {
  dispatch({ type: CREATE_POST_REQUEST });
  try {
    const { data } = await api.post(`${API_BASE_URL}/posts`, postData);
    // console.log("🚀 ~ createPostAction ~ data:", data);
    dispatch({ type: CREATE_POST_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ createPostAction ~ error:", error);
    dispatch({ type: CREATE_POST_FAILURE, payload: error });
  }
};

export const getAllPostAction = () => async (dispatch) => {
  dispatch({ type: GET_ALL_POST_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/posts`);
    // console.log("🚀 ~ createPostAction ~ data:", data);
    dispatch({ type: GET_ALL_POST_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ createPostAction ~ error:", error);
    dispatch({ type: GET_ALL_POST_FAILURE, payload: error });
  }
};

export const getUsersPostAction = (userId) => async (dispatch) => {
  dispatch({ type: GET_USERS_POST_REQUEST });
  try {
    const { data } = await api.get(`${API_BASE_URL}/post/user/${userId}/all`);
    // console.log("🚀 ~ getUsersPostAction ~ data:", data);
    dispatch({ type: GET_USERS_POST_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ getUsersPostAction ~ error:", error);
    dispatch({ type: GET_USERS_POST_FAILURE, payload: error });
  }
};

export const likePostAction = (postId) => async (dispatch) => {
  dispatch({ type: LIKE_POST_REQUEST });
  try {
    const { data } = await api.put(`${API_BASE_URL}/posts/liked/${postId}`);
    // console.log("🚀 ~ likePostAction ~ data:", data);
    dispatch({ type: LIKE_POST_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ likePostAction ~ error:", error);
    dispatch({ type: LIKE_POST_FAILURE, payload: error });
  }
};


export const createCommentAction = (reqData) => async (dispatch) => {
  dispatch({ type: CREATE_COMMENT_REQUEST });
  try {
    const { data } = await api.post(`${API_BASE_URL}/comments/post/${reqData.postId}`, reqData.data);
    // console.log("🚀 ~ createCommentAction ~ data:", data);
    dispatch({ type: CREATE_COMMENT_SUCCESS, payload: data });
  } catch (error) {
    console.log("🚀 ~ createCommentAction ~ error:", error);
    dispatch({ type: CREATE_COMMENT_FAILURE, payload: error });
  }
};