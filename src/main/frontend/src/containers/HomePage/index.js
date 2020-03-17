import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { viewDetails } from "../../store/actions";
import "./index.scss";
import ListCard from "../../components/ListCard";

const HomePage = ({ movies = [] }) => (
  <div className="homepage-container">
    {movies.map((dictionary, index) => {
      const { name, list } = dictionary;
      return <ListCard key={index} header={name} list={list} />;
    })}
  </div>
);

const mapStateToProps = ({ movies }) => ({
  movies
});

const mapDispatchToProps = dispatch => ({
  viewDetails: () => dispatch(viewDetails())
});

HomePage.propTypes = {
  movies: PropTypes.array
};

export { HomePage };

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
