import React from "react";
import "./index.scss";

const Loader = () => (
  <div className="site-loader">
    <div className="loader-img">
      <svg x="0px" y="0px" viewBox="0 0 100 100" enableBackground="new 0 0 0 0">
        <circle fill="#1b8dff" stroke="none" cx="6" cy="50" r="6">
          <animateTransform
            attributeName="transform"
            dur="1s"
            type="translate"
            values="0 25 ; 0 -25; 0 25"
            repeatCount="indefinite"
            begin="0.1"
          />
        </circle>
        <circle fill="#ffd800" stroke="none" cx="34" cy="50" r="6">
          <animateTransform
            attributeName="transform"
            dur="1s"
            type="translate"
            values="0 20 ; 0 -20; 0 20"
            repeatCount="indefinite"
            begin="0.2"
          />
        </circle>
        <circle fill="#08bd08" stroke="none" cx="64" cy="50" r="6">
          <animateTransform
            attributeName="transform"
            dur="1s"
            type="translate"
            values="0 15 ; 0 -15; 0 15"
            repeatCount="indefinite"
            begin="0.3"
          />
        </circle>
        <circle fill="#ff0000" stroke="none" cx="94" cy="50" r="6">
          <animateTransform
            attributeName="transform"
            dur="1s"
            type="translate"
            values="0 10 ; 0 -10; 0 10"
            repeatCount="indefinite"
            begin="0.4"
          />
        </circle>
      </svg>
    </div>
    <div className="loader-text">...Loading</div>
  </div>
);

export default Loader;
