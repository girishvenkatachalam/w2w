import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import rootReducer, { initialState } from "./reducer";

const configureStore = () => {
  return createStore(rootReducer, initialState, applyMiddleware(thunk));
};

export default configureStore;
