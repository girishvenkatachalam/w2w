import actions from "./actions";

export const viewDetails = payload => dispatch => {
  dispatch({
    type: actions.VIEW_DETAILS,
    payload
  });
};
