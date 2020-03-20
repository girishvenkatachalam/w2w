import React from "react";
import { shallow } from "enzyme";
import { renderHook } from "@testing-library/react-hooks";
import { ProfilePage } from "../index";
import { WithContext as ReactTags } from "react-tag-input";

describe("Profile Page", () => {
  let wrapper;
  const fetchAllGenres = jest.fn();
  const fetchAllLanguages = jest.fn();
  const fetchAllProductionCompanies = jest.fn();
  const user = {
    id: "123",
    name: "dummy",
    email: "dummy@gmail.com",
    picture: "",
    preferences: {
      genre: [],
      language: [],
      company: []
    }
  };
  const suggestions = {
    genre: [],
    language: [],
    company: []
  };
  const props = {
    user,
    suggestions,
    promise: {
      isPending: false,
      isRejected: false,
      isFulfilled: false
    },
    fetchAllGenres,
    fetchAllLanguages,
    fetchAllProductionCompanies,
    addGenrePreference: jest.fn(),
    addLanguagePreference: jest.fn(),
    addCompanyPreference: jest.fn(),
    deleteGenrePreference: jest.fn(),
    deleteLanguagePreference: jest.fn(),
    deleteCompanyPreference: jest.fn()
  };

  it("should render correctly", () => {
    wrapper = shallow(<ProfilePage {...props} />);
    expect(wrapper.exists()).toBeTruthy();
    expect(wrapper.find(".back-btn").exists()).toBeTruthy();
    expect(wrapper.find(".user-name").exists()).toBeTruthy();
    expect(wrapper.find(ReactTags).length).toBe(3);
  });

  it("should fetch moviedetails from backend on reload", () => {
    const { waitForNextUpdate } = renderHook(() => ProfilePage(props));
    waitForNextUpdate();
    expect(fetchAllGenres).toHaveBeenCalled();
    expect(fetchAllLanguages).toHaveBeenCalled();
    expect(fetchAllProductionCompanies).toHaveBeenCalled();
  });
});
