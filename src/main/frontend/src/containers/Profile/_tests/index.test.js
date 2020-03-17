import { shallow } from "enzyme";
import React from "react";
import { ProfilePage } from "../index";

describe("Profile Page", () => {
  let wrapper;

  beforeAll(() => {
    wrapper = shallow(<ProfilePage />);
  });

  it("should render correctly", () => {
    expect(wrapper.find(".profilepage-container").exists()).toBeTruthy();
  });
});
