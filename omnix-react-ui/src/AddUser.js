import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import { API_USER_FORM_PATH } from './Constants';
import { Api } from './Api';

class AddUser extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: {
        name: '',
        age: 0,
        email: '',
        phoneNumber: 0,
        jobTitle: '',
        jobDescription: '',
        token: ''
      },
      token: '',
      errors: {},
      api: new Api()

    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    let userToken = await this.state.api.retrieveAuthentication();
    console.log(userToken);
    this.setState({ token: userToken });
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = { ...this.state.item };
    item[name] = value;
    this.setState({ item });
  }



  async handleSubmit(event) {
    event.preventDefault();
    const responce = await fetch(API_USER_FORM_PATH, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token': this.state.token
      },
      body: JSON.stringify(this.state.item),
    });

    if(!responce.ok){
      alert("Internal Server Error: Data Didn't Submit");
    }

  }

  render() {

    const { item } = this.state;
    const title = <h2>Add User</h2>;

    return <div>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name} onChange={this.handleChange} required="true" />
          </FormGroup>
          <FormGroup>
            <Label for="age">Age</Label>
            <Input type="number" min="1" minLength="1" maxLength="2" name="age" id="age" value={item.age} onChange={this.handleChange}
              required="true" />
          </FormGroup>
          <FormGroup>
            <Label for="email">Email</Label>
            <Input type="email" name="email" id="email" value={item.email} onChange={this.handleChange}
              required="true" />
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="phoneNumber">Phone Number</Label>
              <Input type="number" min="1" minlength="8" maxlength="11" name="phoneNumber" id="phoneNumber" value={item.phoneNumber} onChange={this.handleChange}
                required="true" />
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="jobTitle">Job Title</Label>
              <Input type="text" name="jobTitle" id="jobTitle" value={item.jobTitle} onChange={this.handleChange}
                required="true" />
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
