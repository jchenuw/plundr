import React from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';
import './UserList.js';

class App extends React.Component {

  render() {
    return(
      <div>
        <AppNav/>
        <UserList users={this.state.users}/>
      </div>
    )
  }
}

export default App;