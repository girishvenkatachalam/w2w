import { shallow } from "enzyme";
import React from "react";
import PageNotFound from "../index";

describe("Page Not Found", () => {
  let wrapper;

  beforeAll(() => {
    const location = {
      pathname: ""
    };
    wrapper = shallow(<PageNotFound location={location} />);
  });

  it("should render correctly", () => {
    expect(wrapper.find(".not-found-wrapper").exists()).toBeTruthy();
  });
});
