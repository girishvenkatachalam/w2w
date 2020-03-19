/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { fetchAllMovies, upadateMovieDetail } from "../../store/actions";
import "./index.scss";
import ListCard from "../../components/ListCard";
import Loader from "../../components/Loader";
import texts from "../../texts.json";

const HomePage = ({
  movies = [],
  promise,
  fetchMovies,
  upadateDetail,
  history
}) => {
  useEffect(() => {
    fetchMovies();
  }, []);

  const upadateMovieDetail = movie => {
    upadateDetail(movie);
    history.push(`/movie/${movie.id}`);
  };

  return promise && promise.isPending ? (
    <Loader />
  ) : (
    <div className="homepage-container">
      {movies.map((dictionary, index) => {
        const { preferenceName, movieList } = dictionary;
        return (
          <ListCard
            key={index}
            header={preferenceName}
            list={movieList}
            upadateMovieDetail={upadateMovieDetail}
          />
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
  fetchMovies: () => dispatch(fetchAllMovies()),
  upadateDetail: movie => dispatch(upadateMovieDetail(movie))
});

HomePage.propTypes = {
  movies: PropTypes.array,
  promise: PropTypes.object,
  fetchMovies: PropTypes.func,
  upadateDetail: PropTypes.func,
  history: PropTypes.object
};

export { HomePage };

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
