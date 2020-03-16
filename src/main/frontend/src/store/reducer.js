import actions from "./constants";

export const initialState = {
  user: {
    name: "",
    email: "",
    preferences: {
      genre: [],
      language: [],
      company: []
    }
  },
  promise: {
    isReady: false
  },
  movies: [],
  movieDetail: {}
};

export default (state = {}, action) => {
  switch (action.type) {
    case actions.VIEW_DETAILS:
      return {
        ...state,
        movieDetail: action.payload
      };
    default:
      return state;
  }
};
