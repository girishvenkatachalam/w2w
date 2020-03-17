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

export const addPreference = payload => dispatch => {
  dispatch({
    type: actions.ADD_GENRE_PREFERENCE,
    payload
  });
};
