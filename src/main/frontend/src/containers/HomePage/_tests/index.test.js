import { shallow } from "enzyme";
import React from "react";
import { renderHook } from "@testing-library/react-hooks";
import { HomePage } from "../index";
import Loader from "../../../components/Loader";
import ListCard from "../../../components/ListCard";

describe("Home Page", () => {
  let wrapper;
  const movies = [
    {
      preferenceName: "Trending",
      movieList: []
    }
  ];
  const fetchMoviesMock = jest.fn();
  const upadateDetailMock = jest.fn();
  const props = {
    movies: [],
    promise: {
      isPending: false,
      isRejected: false,
      isFulfilled: false
    },
    fetchMovies: fetchMoviesMock,
    upadateDetail: upadateDetailMock,
    history: {
      push: jest.fn()
    }
  };

  it("should render correctly", () => {
    wrapper = shallow(<HomePage {...props} />);
    expect(wrapper.find(".homepage-container").exists()).toBeTruthy();
    expect(wrapper.find(ListCard).exists()).toBeFalsy();
  });

  it("should render loader when promise is not fulfilled", () => {
    const updatedProps = {
      ...props,
      promise: {
        isPending: true,
        isRejected: false,
        isFulfilled: false
      }
    };
    wrapper = shallow(<HomePage {...updatedProps} />);
    expect(wrapper.find(".homepage-container").exists()).toBeFalsy();
    expect(wrapper.find(Loader).exists()).toBeTruthy();
  });

  it("should render ListCard based on recommendations", () => {
    const updatedProps = {
      ...props,
      movies
    };
    wrapper = shallow(<HomePage {...updatedProps} />);
    expect(wrapper.find(ListCard).exists()).toBeTruthy();
    expect(wrapper.find(".no-preferences").exists()).toBeTruthy();
    expect(wrapper.find(Loader).exists()).toBeFalsy();
  });

  it("should not show message on recommendations for preference, if it is available", () => {
    const updatedProps = {
      ...props,
      movies: [...movies, { preferenceName: "Romantic", movieList: [] }]
    };
    wrapper = shallow(<HomePage {...updatedProps} />);
    expect(wrapper.find(ListCard).length).toBe(2);
    expect(wrapper.find(".no-preferences").exists()).toBeFalsy();
  });

  it("should fetch all movies reccomendations from backend on load", () => {
    const { waitForNextUpdate } = renderHook(() => HomePage(props));
    waitForNextUpdate();
    expect(fetchMoviesMock).toHaveBeenCalled();
  });
});
