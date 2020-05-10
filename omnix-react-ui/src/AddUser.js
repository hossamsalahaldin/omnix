import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';


class AddUser extends Component {

  token='';

  userData= {
    userName:'hossam',
    password:'1237'
  }

  emptyItem = {
    name: '',
    age: 0,
    email: '',
    phoneNumber: 0,
    jobTitle: '',
    jobDescription: '',
    token:''
  };

  constructor(props) {
    super(props);
    this.state = {
      token: this.token,
      item: this.emptyItem,
      user: this.userData
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.retrieveAuthentication = this.retrieveAuthentication.bind(this);
this.retrieveAuthentication();
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async retrieveAuthentication(event) {
    const user = this.state.user;
    const response = await fetch('/token/authUser', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user),
    }).then(response => response.json())
      .then(data => this.setState({token: data.token}));
      console.log(this.state.token);
  }

  async handleSubmit(event) {
    event.preventDefault();
    this.state.item.token = this.state.token;
    console.log(this.state.item);
    const item = this.state.item;

    await fetch('/api/addUser', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token': this.state.token
      },
      body: JSON.stringify(item),
    });
  }


  render() {

    const {item} = this.state;
    const title = <h2>Add User</h2>;

    return <div>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name} onChange={this.handleChange} required="true"/>
          </FormGroup>
          <FormGroup>
            <Label for="age">Age</Label>
            <Input type="number" minlength="1" maxlength="2" name="age" id="age" value={item.age} onChange={this.handleChange}
                  required="true"  />
          </FormGroup>
          <FormGroup>
            <Label for="email">Email</Label>
            <Input type="email" name="email" id="email" value={item.email} onChange={this.handleChange}
                  required="true" />
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="phoneNumber">Phone Number</Label>
              <Input type="number" minlength="8" maxlength="11" name="phoneNumber" id="phoneNumber" value={item.phoneNumber} onChange={this.handleChange}
                  required="true"  />
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="jobTitle">Job Title</Label>
              <Input type="text" name="jobTitle" id="jobTitle" value={item.jobTitle} onChange={this.handleChange}
                    required="true"  />
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="jobDescription">Job Description</Label>
              <Input type="text" name="jobDescription" id="jobDescription" value={item.jobDescription} onChange={this.handleChange}
                    required="true" />
            </FormGroup>
          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}

          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(AddUser);
