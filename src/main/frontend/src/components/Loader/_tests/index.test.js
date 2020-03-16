import { shallow } from "enzyme";
import React from "react";
import Loader from "../index";

describe("Loader Component", () => {
  it("should render correctly", () => {
    const wrapper = shallow(<Loader />);
    expect(wrapper.find(".site-loader").exists()).toBeTruthy();
  });
});
