import React, { Fragment } from "react";
import texts from "../../texts.json";
import "./index.scss";

const Header = ({ auth }) => {
  return auth ? (
    <Fragment>
      <div className="header-buffer-space"></div>
      <header className="page-header">
        <div className="logo-wrapper">
          <img src="images/logo.png" alt="W2W Logo" />
          <div>{texts.pagetitle}</div>
        </div>
        <div className="profile-icon-wrapper">
          <img src="images/profile-icon.png" alt="profile icon" />
        </div>
      </header>
    </Fragment>
  ) : null;
};

export default Header;
