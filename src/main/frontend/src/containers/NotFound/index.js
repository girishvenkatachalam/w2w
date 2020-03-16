import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import texts from "../../texts.json";
import "./index.scss";

const PageNotFound = ({ location }) => (
  <div className="not-found-wrapper">
    <h2>
      <span className="pathname">{location.pathname}</span> - {texts.notfound}
    </h2>
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
