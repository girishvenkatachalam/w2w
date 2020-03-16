import React, { Component } from "react";
import { connect } from "react-redux";
import { viewDetails } from "../../store/action-creator";
import "./index.scss";

const HomePage = props => (
  <div className="homepage-container">
    <header className="App-header">
      <h1 className="page-title">Welcome to W2W</h1>
    </header>
  </div>
);

const mapStateToProps = state => ({
  ...state
});

const mapDispatchToProps = dispatch => ({
  viewDetails: () => dispatch(viewDetails())
});

export { HomePage };

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
