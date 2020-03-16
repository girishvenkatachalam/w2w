import actions from "./actions";

export default (state = {}, action) => {
  switch (action.type) {
    case actions.VIEW_DETAILS:
      return {
        result: action.payload
      };
    default:
      return state;
  }
};
