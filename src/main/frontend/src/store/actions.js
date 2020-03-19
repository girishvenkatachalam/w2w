import actions from "./constants";

export const viewDetails = payload => dispatch => {
  dispatch({
    type: actions.VIEW_DETAILS,
    payload
  });
};

export const fetchUserProfile = userEmail => dispatch => {
  fetch("/user/" + userEmail, {
    method: "GET"
  })
    .then(response => {
      if (!response.ok) {
        return response.text().then(text => {
          throw new Error(
            `Request rejected with status ${response.status} and message ${text}`
          );
        });
      } else {
        return response.json();
      }
    })
    .then(payload => {
      dispatch({
        type: actions.FETCH_USER_PROFILE,
        payload
      });
    })
    .catch(error => console.log(`Catch fetchUserProfile: ${error}`));
};

export const loginUser = (payload) => dispatch => {
  const data = payload.profileObj;
  fetch("/user/login", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then(res => res.json())
    .then(data => {
      dispatch({
        type: actions.UPDATE_USER_DETAILS,
        payload: data
      });
    })
    .catch(error => {
      console.error("Error:", error);
    });
};

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

export const deleteGenrePreference = payload => dispatch => {
  dispatch({
    type: actions.DELETE_GENRE_PREFERENCE,
    payload
  });
};

export const deleteLanguagePreference = payload => dispatch => {
  dispatch({
    type: actions.DELETE_LANGUAGE_PREFERENCE,
    payload
  });
};

export const deleteCompanyPreference = payload => dispatch => {
  dispatch({
    type: actions.DELETE_COMPANY_PREFERENCE,
    payload
  });
};


export const fetchAllMovies = () => {
  fetch('/movie-details/home')
      .then((response) => response.json())
      .then((data) => {
        console.log('Success:', data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
}