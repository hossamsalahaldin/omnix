import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AddUser from './AddUser';

class App extends Component {
  render() {
     return (
       <Router>
         <Switch>
           <Route path='/' exact={true} component={AddUser}/>
         </Switch>
       </Router>
     )
   }
}

export default App;
