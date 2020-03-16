import React from "react";
import { render } from "react-dom";
import { mount } from "enzyme";
import { MemoryRouter } from "react-router";
import App from "../index";
import PageNotFound from "../../NotFound";
import HomePage from "../../HomePage";

// test("Invalid path should redirect to PageNotFound", () => {
//   const wrapper = mount(
//     <MemoryRouter initialEntries={["/random"]}>
//       <App />
//     </MemoryRouter>
//   );
//   expect(wrapper.find(PageNotFound).exists()).toBeTruthy();
//   expect(wrapper.find(HomePage).exists()).toBeFalsy();
// });

test("Should redirect to home page", () => {
  const wrapper = mount(
    <MemoryRouter initialEntries={["/"]}>
      <App />
    </MemoryRouter>
  );
  expect(wrapper.find(HomePage).exists()).toBeTruthy();
  expect(wrapper.find(PageNotFound).exists()).toBeFalsy();
});
