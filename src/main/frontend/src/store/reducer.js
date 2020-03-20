import actions, { appConstants } from "./constants";

export const initialState = {
  user: {
    id: "",
    name: "",
    email: "",
    picture: "",
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
    genre: [],
    language: [],
    company: [],
    filteredGenre: [],
    filteredLanguage: [],
    filteredCompany: []
  }
};

export default (state = {}, action) => {
  switch (action.type) {
    case actions.UPDATE_MOVIE_DETAIL:
      return {
        ...state,
        movieDetail: action.payload
      };
    case actions.SET_PROMISE_PENDING:
      return {
        ...state,
        promise: {
          ...state.promise,
          isPending: action.payload
        }
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
  newState.suggestions.filteredGenre = filterPreferences(
    strArr,
    newState.user.preferences.genre
  );
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
  newState.suggestions.filteredLanguage = filterPreferences(
    strArr,
    newState.user.preferences.language
  );
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
  newState.suggestions.filteredCompany = filterPreferences(
    strArr,
    newState.user.preferences.company
  );
  return newState;
};

const addGenrePreference = (state, payload) => {
  const newState = { ...state };
  payload = mapOnePreference(payload);
  newState.user.preferences.genre.push(payload);
  newState.suggestions.filteredGenre = filterPreferences(
    newState.suggestions.genre,
    newState.user.preferences.genre
  );
  return newState;
};

const addLanguagePreference = (state, payload) => {
  const newState = { ...state };
  payload = mapOnePreference(payload);
  newState.user.preferences.language.push(payload);
  newState.suggestions.filteredLanguage = filterPreferences(
    newState.suggestions.language,
    newState.user.preferences.language
  );
  return newState;
};

const addCompanyPreference = (state, payload) => {
  const newState = { ...state };
  payload = mapOnePreference(payload);
  newState.user.preferences.company.push(payload);
  newState.suggestions.filteredCompany = filterPreferences(
    newState.suggestions.company,
    newState.user.preferences.company
  );
  return newState;
};

const deleteGenrePreference = (state, payload) => {
  const newState = { ...state };
  const showingGenre = [...newState.suggestions.filteredGenre];
  const genreCopy = newState.user.preferences.genre;
  var filtered = [];
  for (var i = 0; i < genreCopy.length; i++) {
    const mappedGenre = mapOnePreference(genreCopy[i]);
    if (genreCopy[i]._id !== payload._id) {
      filtered.push(mappedGenre);
    } else {
      showingGenre.push(mappedGenre);
    }
  }
  newState.user.preferences.genre = filtered;
  newState.suggestions.filteredGenre = showingGenre.sort((a, b) =>
    a._id.localeCompare(b._id)
  );
  return newState;
};

const deleteLanguagePreference = (state, payload) => {
  const newState = { ...state };
  const showingLanguage = [...newState.suggestions.filteredLanguage];
  const languageCopy = newState.user.preferences.language;
  var filtered = [];
  for (var i = 0; i < languageCopy.length; i++) {
    const mappedLanguage = mapOnePreference(languageCopy[i]);
    if (languageCopy[i]._id !== payload._id) {
      filtered.push(mappedLanguage);
    } else {
      showingLanguage.push(mappedLanguage);
    }
  }
  newState.user.preferences.language = filtered;
  newState.suggestions.filteredLanguage = showingLanguage.sort((a, b) =>
    a._id.localeCompare(b._id)
  );
  return newState;
};

const deleteCompanyPreference = (state, payload) => {
  const newState = { ...state };
  const showingCompany = [...newState.suggestions.filteredCompany];
  const companyCopy = newState.user.preferences.company;
  var filtered = [];
  for (var i = 0; i < companyCopy.length; i++) {
    const mappedCompany = mapOnePreference(companyCopy[i]);
    if (companyCopy[i]._id !== payload._id) {
      filtered.push(mappedCompany);
    } else {
      showingCompany.push(mappedCompany);
    }
  }
  newState.user.preferences.company = filtered;
  newState.suggestions.filteredCompany = showingCompany.sort((a, b) =>
    a._id.localeCompare(b._id)
  );
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

const mapArrayOfPreferences = payload => {
  if (!payload || payload.length === 0) {
    return payload;
  }
  var strArr = payload.map(function(e) {
    var newElement = mapOnePreference(e);
    return newElement;
  });
  return strArr;
};

const objectsEqual = (o1, o2) => {
  return o1._id === o2._id;
};

const subtractArrays = (a1, a2) => {
  if (!a1 || !a2) {
    return a1;
  }
  var arr = [];
  a1.forEach(o1 => {
    var found = false;
    a2.forEach(o2 => {
      if (objectsEqual(o1, o2)) {
        found = true;
      }
    });
    if (!found) {
      arr.push(o1);
    }
  });
  return arr;
};

const filterPreferences = (allPreferences, selectedPreferences) => {
  return subtractArrays(allPreferences, selectedPreferences);
};

const mapOnePreference = payload => {
  var newElement = { ...payload };
  if (payload.id) {
    newElement.id = String(payload.id);
  } else {
    newElement.id = String(payload._id);
  }
  newElement.text = payload.name;
  return newElement;
};

const updateUserDetails = (state, payload) => {
  payload.genres = mapArrayOfPreferences(payload.genres);
  payload.languages = mapArrayOfPreferences(payload.languages);
  payload.production_companies = mapArrayOfPreferences(
    payload.production_companies
  );
  const {
    userId: id = "",
    name = "",
    email = "",
    pictureUrl: picture = appConstants.defaultProfileImage,
    genres: genre = [],
    languages: language = [],
    production_companies: company = []
  } = payload;

  return {
    ...state,
    user: {
      id,
      name,
      email,
      picture,
      preferences: {
        genre,
        language,
        company
      }
    }
  };
};

const updateDashBoardMovies = (state, payload) => {
  return {
    ...state,
    movies: payload
  };
};
