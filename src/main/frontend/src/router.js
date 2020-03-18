import React, { Fragment } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
// import { Redirect } from "react-router";
import { Provider } from "react-redux";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";
import ProfilePage from "./containers/Profile";
import Header from "./components/Header";

const App = () => {
  return (
    <Provider store={configureStore()}>
      <Router>
        <Fragment>
          <Header />
          <Switch>
            <Route exact path="/" component={HomePage} />
            <Route exact path="/profile" component={ProfilePage} />
            <Route component={PageNotFound} />
          </Switch>
        </Fragment>
      </Router>
    </Provider>
  );
};

export default App;
