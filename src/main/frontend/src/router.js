import React, { Fragment } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";
import ProfilePage from "./containers/Profile";
import Header from "./components/Header";
import LoginPage from "./containers/LoginPage";

const LoggedInRoute = ({ component: Component, ...otherProps }) => (
  <Fragment>
    <Header />
    <Route render={otherProps => <Component {...otherProps} />} />
  </Fragment>
);

const App = () => {
  return (
    <Provider store={configureStore()}>
      <Router>
        <Fragment>
          <Switch>
            <LoggedInRoute exact path="/" component={HomePage} />
            <LoggedInRoute exact path="/profile" component={ProfilePage} />
            <Route exact path="/signin" component={LoginPage} />
            <LoggedInRoute component={PageNotFound} />
          </Switch>
        </Fragment>
      </Router>
    </Provider>
  );
};

export default App;
