/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import texts from "../../texts.json";
import { fetchMovieDetail } from "../../store/actions";
import "./index.scss";

const MovieDetail = ({ movieDetail, match, fetchMovieDetail }) => {
  useEffect(() => {
    const { movieId } = match.params;
    fetchMovieDetail(movieId);
  }, []);

  return movieDetail.id ? (
    <section className="movie-detail-container">
      <Link to={"/dashboard"}>
        <button type="button" className="btn primary back-btn">
          {texts.backToHomeMessage}
        </button>
      </Link>
      <header>{movieDetail.title}</header>
      <article className="movie-info-wrapper">
        <img
          src={movieDetail.image || "../images/default-poster.png"}
          alt={movieDetail.title}
        />
        <div className="movie-basic-info">
          {movieDetail.releaseDate && (
            <div className="movie-info">
              <span className="label">
                {texts.release}
                {": "}
              </span>
              {movieDetail.releaseDate}
            </div>
          )}
          <div className="movie-info">
            <span className="label">
              {texts.language}
              {": "}
            </span>
            {movieDetail.language}
          </div>
          <div className="movie-info">
            <span className="label">
              {texts.genre}
              {": "}
            </span>
            {movieDetail.genre.map(gen => (
              <span key={gen.id} className="tag">
                {gen.name}
              </span>
            ))}
          </div>
          <div className="movie-info">
            <span className="label">
              {texts.productionCompanies}
              {": "}
            </span>
            {movieDetail.productionCompany.map(gen => (
              <span key={gen.id} className="tag">
                {gen.name}
              </span>
            ))}
          </div>
        </div>
      </article>
      <article className="movie-overview">{movieDetail.overview}</article>
    </section>
  ) : null;
};

const mapStateToProps = ({ movieDetail }) => ({ movieDetail });

const mapDispatchToProps = dispatch => ({
  fetchMovieDetail: movieId => dispatch(fetchMovieDetail(movieId))
});

MovieDetail.defaultProps = {
  movieDetail: {},
  match: {}
};

MovieDetail.propTypes = {
  movieDetail: PropTypes.object.isRequired,
  match: PropTypes.object.isRequired
};

export { MovieDetail };

export default connect(mapStateToProps, mapDispatchToProps)(MovieDetail);
