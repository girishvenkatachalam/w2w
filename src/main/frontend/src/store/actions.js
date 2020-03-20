import actions from "./constants";

export const upadateMovieDetail = payload => dispatch => {
  dispatch({
    type: actions.UPDATE_MOVIE_DETAIL,
    payload
  });
};

export const fetchMovieDetail = movieId => dispatch => {
  dispatch(setApiPendingStatus(true));

  fetch(`/movie-details/${movieId}`)
    .then(response => response.json())
    .then(data => {
      if (data.id) {
        dispatch(upadateMovieDetail(data));
      }
      dispatch(setApiPendingStatus(false));
    })
    .catch(error => {
      console.error("Error:", error);
      dispatch(setApiPendingStatus(false));
    });
};

export const setApiPendingStatus = payload => dispatch => {
  dispatch({
    type: actions.SET_PROMISE_PENDING,
    payload
  });
};

export const setApiFulfilledStatus = payload => dispatch => {
  dispatch({
    type: actions.SET_PROMISE_FULFILLED,
    payload
  });
};

export const setApiFailedStatus = payload => dispatch => {
  dispatch({
    type: actions.SET_PROMISE_REJECTED,
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

export const fetchUserDetails = () => dispatch => {
  fetch("/user/login")
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

export const fetchAllGenres = () => dispatch => {
  fetch("/genres")
    .then(res => res.json())
    .then(data => {
      dispatch({
        type: actions.FETCH_ALL_GENERS,
        payload: data
      });
    })
    .catch(error => {
      console.error("Error:", error);
    });
};

export const fetchAllLanguages = () => dispatch => {
  fetch("/languages")
    .then(res => res.json())
    .then(data => {
      data = data.filter(language => language.name);
      dispatch({
        type: actions.FETCH_ALL_LANGUAGES,
        payload: data
      });
    })
    .catch(error => {
      console.error("Error:", error);
    });
};

export const fetchAllProductionCompanies = () => dispatch => {
  dispatch(setApiPendingStatus(true));
  fetch("/productionCompanies")
    .then(res => res.json())
    .then(data => {
      dispatch({
        type: actions.FETCH_ALL_COMPANIES,
        payload: data
      });
      dispatch(setApiPendingStatus(false));
    })
    .catch(error => {
      console.error("Error:", error);
      dispatch(setApiPendingStatus(false));
    });
};

export const addGenrePreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.ADD_GENRE_PREFERENCE,
    payload: payload
  });
  fetch("/user/genre/add?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      console.error("Error:", error);
      dispatch({
        type: actions.DELETE_GENRE_PREFERENCE,
        payload: payload
      });
    });
};

export const addLanguagePreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.ADD_LANGUAGE_PREFERENCE,
    payload: payload
  });
  fetch("/user/language/add?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      console.error("Error:", error);
      dispatch({
        type: actions.DELETE_LANGUAGE_PREFERENCE,
        payload: payload
      });
    });
};

export const addCompanyPreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.ADD_COMPANY_PREFERENCE,
    payload: payload
  });
  fetch("/user/ProductionCompany/add?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      dispatch({
        type: actions.DELETE_COMPANY_PREFERENCE,
        payload: payload
      });
      console.error("Error:", error);
    });
};

export const deleteGenrePreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.DELETE_GENRE_PREFERENCE,
    payload: payload
  });
  fetch("/user/genre/remove?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      dispatch({
        type: actions.ADD_GENRE_PREFERENCE,
        payload: payload
      });
      console.error("Error:", error);
    });
};

export const deleteLanguagePreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.DELETE_LANGUAGE_PREFERENCE,
    payload: payload
  });
  fetch("/user/language/remove?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      console.error("Error:", error);
      dispatch({
        type: actions.ADD_LANGUAGE_PREFERENCE,
        payload: payload
      });
    });
};

export const deleteCompanyPreference = (userEmail, payload) => dispatch => {
  dispatch({
    type: actions.DELETE_COMPANY_PREFERENCE,
    payload: payload
  });
  fetch("/user/ProductionCompany/remove?email=" + userEmail, {
    method: "PUT",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {})
    .catch(error => {
      dispatch({
        type: actions.ADD_COMPANY_PREFERENCE,
        payload: payload
      });
      console.error("Error:", error);
    });
};

export const fetchAllMovies = () => dispatch => {
  dispatch(setApiPendingStatus(true));

  fetch("/movie-details/home")
    .then(response => response.json())
    .then(data => {
      dispatch({
        type: actions.UPDATE_DASHBOARD_MOVIES,
        payload: data
      });
      dispatch(setApiPendingStatus(false));
    })
    .catch(error => {
      console.error("Error:", error);
      dispatch(setApiPendingStatus(false));
    });
};
