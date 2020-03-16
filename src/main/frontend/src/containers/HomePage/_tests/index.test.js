import { shallow } from "enzyme";
import React from "react";
import { HomePage } from "../index";

describe("Home Page", () => {
  let wrapper;

  beforeAll(() => {
    wrapper = shallow(<HomePage />);
  });

  it("should render correctly", () => {
    expect(wrapper.find(".homepage-container").exists()).toBeTruthy();
  });
});
