import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Provider } from "react-redux";
import configureStore from "../../store";
import HomePage from "../HomePage";
import PageNotFound from "../NotFound";

function App() {
  return (
    <Provider store={configureStore()}>
      <Router>
        <div>
          <Switch>
            <Route exact path="/" component={HomePage} />
            <Route path="*" component={PageNotFound} />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
