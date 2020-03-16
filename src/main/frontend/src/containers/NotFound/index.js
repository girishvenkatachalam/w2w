import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import texts from "../../texts.json";
import "./index.css";

const PageNotFound = ({ location }) => (
  <div className="not-found-wrapper">
    <h3>
      {location.pathname} - {texts.notfound}
    </h3>
    <h4>
      {texts.visit}
      <Link to={"/"}>{texts.homePage}</Link>
    </h4>
  </div>
);

PageNotFound.propTypes = {
  location: PropTypes.shape({ pathname: PropTypes.string.isRequired })
    .isRequired
};

export default PageNotFound;
