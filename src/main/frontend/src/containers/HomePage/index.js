/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { fetchAllMovies } from "../../store/actions";
import "./index.scss";
import ListCard from "../../components/ListCard";
import Loader from "../../components/Loader";
import texts from "../../texts.json";

const HomePage = ({ movies = [], promise, fetchMovies }) => {
  useEffect(() => {
    fetchMovies();
  }, []);

  return promise && promise.isPending ? (
    <Loader />
  ) : (
    <div className="homepage-container">
      {movies.map((dictionary, index) => {
        const { preferenceName, movieList } = dictionary;
        return (
          <ListCard key={index} header={preferenceName} list={movieList} />
        );
      })}
      {movies.length === 1 && movies[0].preferenceName === "Trending" && (
        <div className="no-preferences">
          {texts.noPreferenceMessage}
          <Link to={"/profile"}>{texts.profilePage}</Link>.
        </div>
      )}
    </div>
  );
};

const mapStateToProps = ({ movies, promise }) => ({
  movies,
  promise
});

const mapDispatchToProps = dispatch => ({
  fetchMovies: () => dispatch(fetchAllMovies())
});

HomePage.propTypes = {
  movies: PropTypes.array
};

export { HomePage };

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
