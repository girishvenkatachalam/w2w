/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import texts from "../../texts.json";
import { fetchMovieDetail, upadateMovieDetail } from "../../store/actions";
import "./index.scss";

const MovieDetail = ({
  movieDetail,
  location,
  fetchMovieDetail,
  upadateMovieDetail
}) => {
  useEffect(() => {
    const params = location.pathname.split("/movie/");
    if (params && params[1] && !movieDetail.id) fetchMovieDetail(params[1]);
    return () => {
      upadateMovieDetail({});
    };
  }, []);

  const getLanguage = () => {
    const hasLanguage =
      movieDetail.spokenLanguage && movieDetail.spokenLanguage.length;
    return hasLanguage
      ? movieDetail.spokenLanguage.map(lang => lang.languageName).join(", ")
      : movieDetail.language;
  };

  const releaseDate = movieDetail.releaseDate? new Date(movieDetail.releaseDate.substr(0, 10)) : null;
  const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

  return movieDetail.id ? (
    <section className="movie-detail-container">
      <Link to={"/dashboard"}>
        <button type="button" className="btn primary back-btn">
          {texts.backToHomeMessage}
        </button>
      </Link>
      <header>{movieDetail.title} {releaseDate? "(" + releaseDate.getFullYear() + ")" : ""}</header>
      <article className="movie-info-wrapper">
        <img
          src={movieDetail.image || "../images/default-poster.png"}
          alt={movieDetail.title}
        />
        <div className="movie-basic-info">
          {releaseDate && (
            <div className="movie-info">
              <span className="label">
                {texts.release}
                {": "}
              </span>
              {releaseDate.toLocaleDateString("en-US", options)}
            </div>
          )}
          <div className="movie-info">
            <span className="label">
              {texts.language}
              {": "}
            </span>
            {getLanguage()}
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
          {movieDetail.homepage && (
            <div className="movie-info">
              <a
                href={movieDetail.homepage}
                target="_blank noopener noreferrer"
              >
                {texts.moreDetails}
              </a>
            </div>
          )}
        </div>
      </article>
      <article className="movie-overview">{movieDetail.overview}</article>
    </section>
  ) : null;
};

const mapStateToProps = ({ movieDetail }) => ({ movieDetail });

const mapDispatchToProps = dispatch => ({
  fetchMovieDetail: movieId => dispatch(fetchMovieDetail(movieId)),
  upadateMovieDetail: payload => dispatch(upadateMovieDetail(payload))
});

MovieDetail.defaultProps = {
  movieDetail: {},
  location: {}
};

MovieDetail.propTypes = {
  movieDetail: PropTypes.object.isRequired,
  location: PropTypes.object.isRequired,
  fetchMovieDetail: PropTypes.func,
  upadateMovieDetail: PropTypes.func
};

export { MovieDetail };

export default connect(mapStateToProps, mapDispatchToProps)(MovieDetail);
