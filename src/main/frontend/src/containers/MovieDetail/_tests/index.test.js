import React from "react";
import { shallow } from "enzyme";
import { renderHook } from "@testing-library/react-hooks";
import { MovieDetail } from "../index";

describe("Login Page", () => {
  let wrapper;
  const fetchMovieDetailMock = jest.fn();
  const upadateMovieDetailMock = jest.fn();
  const movieDetail = {
    id: "123",
    title: "Avatar",
    image: "",
    releaseDate: "",
    homepage: "",
    genre: [],
    productionCompany: [],
    overview: "description"
  };
  const props = {
    movieDetail,
    location: { pathname: "/movie/1234" },
    fetchMovieDetail: fetchMovieDetailMock,
    upadateMovieDetail: upadateMovieDetailMock
  };

  it("should render correctly", () => {
    wrapper = shallow(<MovieDetail {...props} />);
    expect(wrapper.exists()).toBeTruthy();
    expect(wrapper.find(".back-btn").exists()).toBeTruthy();
    expect(wrapper.find("header").exists()).toBeTruthy();
    expect(wrapper.find(".movie-info").length).toBe(3);
    expect(wrapper.find(".movie-overview").exists()).toBeTruthy();
  });

  it("should not render details if movie detail is empty", () => {
    const updatedProps = { ...props, movieDetail: {} };
    wrapper = shallow(<MovieDetail {...updatedProps} />);
    expect(wrapper.exists()).toBeTruthy();
    expect(wrapper.find(".movie-detail-container").exists()).toBeFalsy();
  });

  it("should not fetch moviedetails from backend if movie details are available", () => {
    const { waitForNextUpdate } = renderHook(() => MovieDetail(props));
    waitForNextUpdate();
    expect(fetchMovieDetailMock).toHaveBeenCalledTimes(0);
  });

  it("should fetch moviedetails from backend on reload", () => {
    const updatedProps = { ...props, movieDetail: {} };
    const { waitForNextUpdate } = renderHook(() => MovieDetail(updatedProps));
    waitForNextUpdate();
    expect(fetchMovieDetailMock).toHaveBeenCalled();
  });
});
