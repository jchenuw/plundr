import React from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.isLoading = true;
    this.state = {users: []};
  }

  async componentDidMount() {
    const response = await fetch('/api/users');
    const body = await response.json();
    this.setState({users: body, isLoading: false})
  }

  render() {
    return(
      <UserList users={this.state.users}/>
    )
  }
}

class UserList extends React.Component {
  render() {
    const users = this.props.users.map(user =>
      <User key={user.id} user={user}/>
    );

    return(
      <table>
        <tbody>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            {users}
          </tr>
        </tbody>
      </table>
    )
  }
}

class User extends React.Component {
  render() {
    return(
      <tr>
        <td>{this.props.user.firstName}</td>
        <td>{this.props.user.lastName}</td>
        <td>{this.props.user.email}</td>
      </tr>
    )
  }
}

export default App;