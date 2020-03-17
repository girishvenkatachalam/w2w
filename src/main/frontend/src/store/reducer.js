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
    isPending: false,
    isRejected: false,
    isFulfilled: false
  },
  movies: [
    {
      name: "Trending",
      list: [
        {
          title: "Avatar",
          image: ""
        },
        {
          title: "Gladiator",
          image: ""
        },
        {
          title: "Rush Hour",
          image: ""
        },
        {
          title: "Titanic",
          image: ""
        }
      ]
    }
  ],
  movieDetail: {},
  suggestions: [
    { id: "romantic", text: "Romantic" },
    { id: "action", text: "Action" },
    { id: "fantasy", text: "Fantasy" },
    { id: "adventure", text: "Adventure" },
    { id: "thriller", text: "Thriller" },
    { id: "crime", text: "Crime" },
    { id: "scienceFiction", text: "Science Fiction" },
    { id: "family", text: "Family" }
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
