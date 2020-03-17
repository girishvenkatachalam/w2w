import React from "react";
import PropTypes from "prop-types";
import texts from "../../texts.json";
import "./index.scss";

const ListCard = ({ header, list = [] }) => {
  return (
    <section className="listcard-wrapper">
      <header>
        {header}
        <button type="button" className="btn primary" onClick={() => {}}>
          {texts.viewall}
        </button>
      </header>
      <ul className="movie-list">
        {list.map((movie, index) => (
          <li key={index} className="movie-card">
            <figure>
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
  viewCompleteList: PropTypes.func
};

export default ListCard;
