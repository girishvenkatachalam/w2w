import React, { Fragment } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Provider } from "react-redux";
import { Redirect } from "react-router";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";
import ProfilePage from "./containers/Profile";
import Header from "./components/Header";
import LoginPage from "./containers/LoginPage";
import MovieDetail from "./containers/MovieDetail";
import { getCookie } from "./common/utils";

const LoggedInRoute = ({ component: Component, ...otherProps }) => (
  <Fragment>
    <Header />
    <Route render={otherProps => <Component {...otherProps} />} />
  </Fragment>
);

const PublicRoute = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        getCookie("emailId") ? (
          <Redirect
            to={{ pathname: "/dashboard", state: { from: props.location } }}
          />
        ) : (
          <Component {...props} />
        )
      }
    />
  );
};

const App = () => {
  return (
    <Provider store={configureStore()}>
      <Router>
        <Fragment>
          <Switch>
            <LoggedInRoute exact path="/dashboard" component={HomePage} />
            <LoggedInRoute exact path="/profile" component={ProfilePage} />
            <LoggedInRoute path="/movie/:movieId" component={MovieDetail} />
            <PublicRoute exact path="/" component={LoginPage} />
            <LoggedInRoute component={PageNotFound} />
          </Switch>
        </Fragment>
      </Router>
    </Provider>
  );
};

export default App;
