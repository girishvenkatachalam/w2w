import React from "react";
import { shallow } from "enzyme";
import ListCard from "../index";

describe("ListCard Component", () => {
  let wrapper;
  const props = { header: "Trending", list: [], upadateMovieDetail: jest.fn() };

  it("should render correctly", () => {
    wrapper = shallow(<ListCard {...props} />);
    expect(wrapper.exists()).toBeTruthy();
    expect(wrapper.find("header").text()).toBe("Trending");
    expect(wrapper.find(".movie-card").length).toBe(0);
  });

  it("should render movie cards", () => {
    const updatedProps = {
      ...props,
      list: [
        { id: "123", image: "", title: "dummy" },
        { id: "567", image: "", title: "dummy 2" }
      ]
    };
    wrapper = shallow(<ListCard {...updatedProps} />);
    expect(wrapper.find(".movie-card").length).toBe(2);
  });
});
