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
  movieDetail: {},
  suggestions: [
    { id: "USA", text: "USA" },
    { id: "Germany", text: "Germany" },
    { id: "Austria", text: "Austria" },
    { id: "Costa Rica", text: "Costa Rica" },
    { id: "Sri Lanka", text: "Sri Lanka" },
    { id: "Thailand", text: "Thailand" }
  ]
};

export default (state = {}, action) => {
  switch (action.type) {
    case actions.VIEW_DETAILS:
      return {
        ...state,
        movieDetail: action.payload
      };
    case actions.ADD_GENRE_PREFERENCE:
      return addGenrePreference(state, action.payload);
    default:
      return state;
  }
};

const addGenrePreference = (state, payload) => {
  const newState = { ...state };
  newState.user.preferences.genre.push(payload);

  return newState;
};
