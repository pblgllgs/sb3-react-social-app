import {
    CREATE_CHAT_FAILURE,
    CREATE_CHAT_REQUEST,
    CREATE_CHAT_SUCCESS,
    CREATE_MESSAGE_FAILURE,
    CREATE_MESSAGE_REQUEST,
    CREATE_MESSAGE_SUCCESS,
    GET_ALL_CHATS_FAILURE,
    GET_ALL_CHATS_REQUEST,
    GET_ALL_CHATS_SUCCESS,
  } from "./message.actionType";
  import { API_BASE_URL, api } from "../../config/api";
  
  export const createMessage = (message) => async (dispatch) => {
    dispatch({ type: CREATE_MESSAGE_REQUEST });
    try {
      const { data } = await api.post(
        `${API_BASE_URL}/messages/chat/${message.chatId}`,
        message
      );
      console.log("🚀 ~ createMessage ~ data:", data);
      dispatch({ type: CREATE_MESSAGE_SUCCESS, payload: data });
    } catch (error) {
      console.log("🚀 ~ createMessage ~ error:", error);
      dispatch({ type: CREATE_MESSAGE_FAILURE, payload: error });
    }
  };
  
  export const createChat = (chat) => async (dispatch) => {
    dispatch({ type: CREATE_CHAT_REQUEST });
    try {
      const { data } = await api.post(`${API_BASE_URL}/chats`, chat);
      console.log("🚀 ~ createChat ~ data:", data);
      dispatch({ type: CREATE_CHAT_SUCCESS, payload: data });
    } catch (error) {
      console.log("🚀 ~ createChat ~ error:", error);
      dispatch({ type: CREATE_CHAT_FAILURE, payload: error });
    }
  };
  
  export const getAllChats = () => async (dispatch) => {
    dispatch({ type: GET_ALL_CHATS_REQUEST });
    try {
      const { data } = await api.get(`${API_BASE_URL}/chats`);
      console.log("🚀 ~ getAllChats ~ data:", data);
      dispatch({ type: GET_ALL_CHATS_SUCCESS, payload: data });
    } catch (error) {
      console.log("🚀 ~ getAllChats ~ error:", error);
      dispatch({ type: GET_ALL_CHATS_FAILURE, payload: error });
    }
  };
  