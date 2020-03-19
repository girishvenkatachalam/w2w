import actions from "./constants";

export const initialState = {
  user: {
    name: "",
    email: "",
    picture: "images/profile-image.png",
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
  movies: [],
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
    case actions.FETCH_USER_PROFILE:
      return fetchUserProfile(state, action.payload);
    case actions.FETCH_ALL_GENERS:
      return fetchAllGenrePreference(state, action.payload);
    case actions.FETCH_ALL_LANGUAGES:
      return fetchAllLanguagePreference(state, action.payload);
    case actions.FETCH_ALL_COMPANIES:
      return fetchAllCompanyPreference(state, action.payload);
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
    case actions.UPDATE_USER_DETAILS:
      return updateUserDetails(state, action.payload);
    case actions.UPDATE_DASHBOARD_MOVIES:
      return updateDashBoardMovies(state, action.payload);
    default:
      return state;
  }
};

const fetchAllGenrePreference = (state, payload) => {
  const newState = { ...state };
  var strArr = payload.map(function(e) {
    var newElement = { ...e };
    newElement.id = String(e.id);
    newElement.text = e.name;
    return newElement;
  });
  newState.suggestions.genre = strArr;
  return newState;
};

const fetchAllLanguagePreference = (state, payload) => {
  const newState = { ...state };
  var strArr = payload.map(function(e) {
    var newElement = { ...e };
    newElement.id = e._id;
    newElement.text = e.name;
    return newElement;
  });
  newState.suggestions.language = strArr;
  return newState;
};

const fetchAllCompanyPreference = (state, payload) => {
  const newState = { ...state };
  var strArr = payload.map(function(e) {
    var newElement = { ...e };
    newElement.id = String(e.id);
    newElement.text = e.name;
    return newElement;
  });
  newState.suggestions.company = strArr;
  debugger;
  return newState;
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

const fetchUserProfile = (state, payload) => {
  const newState = { ...state };
  newState.user.preferences = {
    genre: payload.genres ? payload.genres : [],
    language: payload.languages ? payload.languages : [],
    company: payload.production_companies ? payload.production_companies : []
  };
  newState.user.email = payload.email;
  newState.user.name = payload.name;
  return newState;
};

const updateUserDetails = (state, payload) => {
  const { id, name, emailId, email } = payload;
  return {
    ...state,
    user: {
      ...state.user,
      id,
      name,
      email: emailId || email
    }
  };
};

const updateDashBoardMovies = (state, payload) => {
  return {
    ...state,
    movies: payload
  };
};
