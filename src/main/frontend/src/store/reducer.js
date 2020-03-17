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
  suggestions: {
    genre: [
      { id: "romantic", text: "Romantic" },
      { id: "action", text: "Action" },
      { id: "fantasy", text: "Fantasy" },
      { id: "adventure", text: "Adventure" },
      { id: "thriller", text: "Thriller" },
      { id: "crime", text: "Crime" },
      { id: "scienceFiction", text: "Science Fiction" },
      { id: "family", text: "Family" }
    ],
    language: [
      { id: "english", text: "English" },
      { id: "hindi", text: "Hindi" },
      { id: "french", text: "French" }
    ],
    company: [
      { id: "dc", text: "DC" },
      { id: "marvel", text: "Marvel" }
    ]
  }
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
    case actions.ADD_LANGUAGE_PREFERENCE:
      return addLanguagePreference(state, action.payload);
    case actions.ADD_COMPANY_PREFERENCE:
      return addCompanyPreference(state, action.payload);
    case actions.DELETE_GENRE_PREFERENCE:
      return deleteGenrePreference(state, action.payload);
    case actions.DELETE_LANGUAGE_PREFERENCE:
      return deleteLanguagePreference(state, action.payload);
    case actions.DELETE_COMPANY_PREFERENCE:
      return deleteCompanyPreference(state, action.payload);
    default:
      return state;
  }
};

const addGenrePreference = (state, payload) => {
  const newState = { ...state };
  newState.user.preferences.genre.push(payload);
  return newState;
};

const addLanguagePreference = (state, payload) => {
  const newState = { ...state };
  newState.user.preferences.language.push(payload);
  return newState;
};

const addCompanyPreference = (state, payload) => {
  const newState = { ...state };
  newState.user.preferences.company.push(payload);
  return newState;
};

const deleteGenrePreference = (state, payload) => {
  const newState = { ...state };
  const genreCopy = newState.user.preferences.genre;
  var filtered = [];
  for (var i = 0; i < genreCopy.length; i++) {
    if (genreCopy[i].id !== payload.id) {
      filtered.push(genreCopy[i]);
    }
  }
  newState.user.preferences.genre = filtered;
  return newState;
};

const deleteLanguagePreference = (state, payload) => {
  const newState = { ...state };
  const genreCopy = newState.user.preferences.language;
  var filtered = [];
  for (var i = 0; i < genreCopy.length; i++) {
    if (genreCopy[i].id !== payload.id) {
      filtered.push(genreCopy[i]);
    }
  }
  newState.user.preferences.language = filtered;
  return newState;
};

const deleteCompanyPreference = (state, payload) => {
  const newState = { ...state };
  const genreCopy = newState.user.preferences.company;
  var filtered = [];
  for (var i = 0; i < genreCopy.length; i++) {
    if (genreCopy[i].id !== payload.id) {
      filtered.push(genreCopy[i]);
    }
  }
  newState.user.preferences.company = filtered;
  return newState;
};
