import React from "react";
import { shallow } from "enzyme";
import { renderHook } from "@testing-library/react-hooks";
import { Header } from "../index";

describe("Header Component", () => {
  let wrapper;
  const fetchUserDataMock = jest.fn();
  const props = { fetchUserData: fetchUserDataMock };

  it("should render correctly", () => {
    wrapper = shallow(<Header {...props} />);
    expect(wrapper.exists()).toBeTruthy();
    expect(wrapper.find(".logout-btn").exists()).toBeTruthy();
    expect(wrapper.find(".logo-wrapper").exists()).toBeTruthy();
    expect(wrapper.find(".profile-icon").exists()).toBeTruthy();
  });

  it("should fetch user data from backend on load", () => {
    const { waitForNextUpdate } = renderHook(() => Header(props));
    waitForNextUpdate();
    expect(fetchUserDataMock).toHaveBeenCalled();
  });
});
