import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Redirect } from "react-router";
import { Provider } from "react-redux";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";
import LoginPage from "./containers/LoginPage";

const PrivateRoute = ({ component: Component, authed, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        authed ? (
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

const PublicRoute = ({ component: Component, authed, ...rest }) => {
  return (
    <Route
      {...rest}
      render={props =>
        authed ? (
          <Redirect to={{ pathname: "/", state: { from: props.location } }} />
        ) : (
          <Component {...props} />
        )
      }
    />
  );
};

const App = () => {
  const [authenticated, setAuthenticated] = useState(false);
  useEffect(() => {
    // TO DO: check for authentication through auth-token in cookie/storage
    setAuthenticated(true);
  }, []);

  return (
    <Provider store={configureStore()}>
      <Router>
        <Switch>
          <PrivateRoute
            authed={authenticated}
            exact
            path="/"
            component={HomePage}
          />
          <PublicRoute
            authed={authenticated}
            exact
            path="/login"
            component={LoginPage}
          />
          <Route component={PageNotFound} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
