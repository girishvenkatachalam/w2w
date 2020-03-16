import actions from "./constants";

export const viewDetails = payload => dispatch => {
  dispatch({
    type: actions.VIEW_DETAILS,
    payload
  });
};
