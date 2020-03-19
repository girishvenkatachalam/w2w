export const getCookie = key => {
  const allCookies = document.cookie;
  const cName = `${key}=`;
  let cVal = "";
  const tmp = allCookies.split(";").filter(el => el.indexOf(cName) >= 0);
  if (tmp.length > 0) {
    cVal = tmp[0].substr(tmp[0].indexOf("=") + 1);
  }
  return cVal;
};
