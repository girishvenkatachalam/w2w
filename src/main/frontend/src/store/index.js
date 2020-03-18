import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer, { initialState } from "./reducer";

const configureStore = () => {
  const composeEnhancers =
    window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
  return createStore(
    rootReducer,
    initialState,
    composeEnhancers(applyMiddleware(thunk))
  );
};

export default configureStore;
