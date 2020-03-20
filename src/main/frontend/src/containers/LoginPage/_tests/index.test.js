import React from "react";
import { shallow } from "enzyme";
import LoginPage from "../index";

describe("Login Page", () => {
  let wrapper;
  beforeAll(() => {
    wrapper = shallow(<LoginPage />);
  });

  it("should render correctly", () => {
    expect(wrapper.find(".login-container").exists()).toBeTruthy();
    expect(wrapper.find(".google-button").exists()).toBeTruthy();
  });
});
