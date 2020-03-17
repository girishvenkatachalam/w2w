import actions from "./constants";

export const viewDetails = payload => dispatch => {
  dispatch({
    type: actions.VIEW_DETAILS,
    payload
  });
};

//export const fetchUserProfile = (userId) => dispatch => {
//  fetch()
//};

export const addGenrePreference = payload => dispatch => {
  dispatch({
    type: actions.ADD_GENRE_PREFERENCE,
    payload
  });
};

export const addLanguagePreference = payload => dispatch => {
  dispatch({
    type: actions.ADD_LANGUAGE_PREFERENCE,
    payload
  });
};

export const addCompanyPreference = payload => dispatch => {
  dispatch({
    type: actions.ADD_COMPANY_PREFERENCE,
    payload
  });
};
