import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "./store";
import HomePage from "./containers/HomePage";
import PageNotFound from "./containers/NotFound";

const App = () => {
  return (
    <Provider store={configureStore()}>
      <Router>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route component={PageNotFound} />
        </Switch>
      </Router>
    </Provider>
  );
};

export default App;
