import React, { Component } from 'react';
import { API_AUTHENTICATION_PATH } from './Constants'

export class Api extends Component {

  constructor(props) {
    super(props);
    this.state = {
      user: {
        userName: 'hossam',
        password: '123'
      }
    }

  }
  async retrieveAuthentication() {
    console.log(this.state.user);
    console.log(JSON.stringify(this.state.user))
    const response = await fetch(API_AUTHENTICATION_PATH, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.state.user)
    });

    const json = await response.json();
    console.log("API: " + json.token);
    return json.token;
  }
}