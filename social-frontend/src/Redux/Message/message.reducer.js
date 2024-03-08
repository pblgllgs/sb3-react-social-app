import {
  CREATE_CHAT_FAILURE,
  CREATE_CHAT_SUCCESS,
  CREATE_MESSAGE_FAILURE,
  CREATE_MESSAGE_SUCCESS,
  GET_ALL_CHATS_FAILURE,
  GET_ALL_CHATS_SUCCESS,
} from "./message.actionType";

const initialState = {
  messages: [],
  message: null,
  chats: [],
  loading: false,
  error: null,
};

export const messageReducer = (state = initialState, action) => {
  switch (action.type) {
    case CREATE_MESSAGE_SUCCESS:
      return { ...state, message: action.payload, loading: false, error: null };

    case CREATE_CHAT_SUCCESS:
      return {
        ...state,
        chats: [action.payload, ...state.chats],
        loading: false,
        error: null,
      };

    case GET_ALL_CHATS_SUCCESS:
      return { ...state, chats: action.payload, loading: false, error: null };

    case CREATE_CHAT_FAILURE:
    case GET_ALL_CHATS_FAILURE:
    case CREATE_MESSAGE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };

    default:
      return state;
  }
};
