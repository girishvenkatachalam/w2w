import { getCookie } from "../index";

beforeAll(() => {
  Object.defineProperty(window.document, "cookie", {
    writable: true,
    value: "my-cookie=dummy"
  });
});

describe("Common Utilities", () => {
  it("should return cokkie value", () => {
    const cookieValue = getCookie("my-cookie");
    expect(cookieValue).toBe("dummy");
  });

  it("should return empty string for invalid cookie", () => {
    const cookieValue = getCookie("abcd");
    expect(cookieValue).toBe("");
  });
});
