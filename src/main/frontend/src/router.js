import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Redirect } from "react-router";
import { Provider } from "react-redux";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";
import LoginPage from "./containers/LoginPage";
import ProfilePage from "./containers/Profile";

const isUserLoggedIn = () => {
  // TO DO: check for authentication through auth-token in cookie/storage
  // Remove the below hardcoded return
  return true;
};

const PrivateRoute = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        isUserLoggedIn() ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{ pathname: "/login", state: { from: props.location } }}
          />
        )
      }
    />
  );
};

const PublicRoute = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        isUserLoggedIn() ? (
          <Redirect to={{ pathname: "/", state: { from: props.location } }} />
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
        <Switch>
          <PrivateRoute exact path="/" component={HomePage} />
          <PrivateRoute exact path="/profile" component={ProfilePage} />
          <PublicRoute exact path="/login" component={LoginPage} />
          <Route component={PageNotFound} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
