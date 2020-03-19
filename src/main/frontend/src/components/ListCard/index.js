import React from "react";
import PropTypes from "prop-types";
import "./index.scss";

const ListCard = ({ header, list = [], upadateMovieDetail }) => {
  const showMovieDetail = movie => {
    upadateMovieDetail(movie);
  };
  return (
    <section className="listcard-wrapper">
      <header>{header}</header>
      <ul className="movie-list">
        {list.map(movie => (
          <li key={movie.id} className="movie-card">
            <figure
              className="movie-figure"
              onClick={() => showMovieDetail(movie)}
            >
              <img
                className="movie-poster"
                src={movie.image || "images/default-poster.png"}
                alt={movie.title}
              />
              <figcaption className="movie-title">{movie.title}</figcaption>
            </figure>
          </li>
        ))}
      </ul>
    </section>
  );
};

ListCard.propTypes = {
  header: PropTypes.string.isRequired,
  list: PropTypes.array,
  upadateMovieDetail: PropTypes.func
};

export default ListCard;
