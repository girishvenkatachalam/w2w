/* eslint-disable react-hooks/exhaustive-deps */
import React, { Fragment, useEffect } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { fetchUserDetails } from "../../store/actions";
import texts from "../../texts.json";
import "./index.scss";

const Header = ({ fetchUserData }) => {
  useEffect(() => {
    // fetchUserData();
  }, []);

  return (
    <Fragment>
      <div className="header-buffer-space"></div>
      <header className="page-header">
        <div className="logo-wrapper">
          <Link to={"/"}>
            <img src="images/logo.png" alt="W2W Logo" />
          </Link>
          <div>{texts.pagetitle}</div>
        </div>
        <div className="profile-icon-wrapper">
          <Link to={"/profile"}>
            <img src="images/profile-icon.png" alt="profile icon" />
          </Link>
        </div>
      </header>
    </Fragment>
  );
};

const mapStateToProps = ({ movies }) => ({
  movies
});

const mapDispatchToProps = dispatch => ({
  fetchUserData: () => dispatch(fetchUserDetails())
});

export { Header };

export default connect(mapStateToProps, mapDispatchToProps)(Header);
