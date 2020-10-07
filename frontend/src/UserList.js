import React from 'react';
import { ButtonGroup } from 'reactstrap';

class UserList extends React.Component {

    constructor(props) {
        super(props);
        this.isLoading = true;
        this.state = { users: [] };
    }

    async componentDidMount() {
        const response = await fetch('/api/users');
        const body = await response.json();
        this.setState({ users: body, isLoading: false })
    }

    async onDelete(id) {
        await fetch (`/api/users/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application.json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedUsers = [...this.state.users].filter(i => i.id !== id);
            this.setState({users: updatedUsers});
        });
    }

    render() {
        const users = this.props.users.map(user =>
            <User key={user.id} user={user} onDelete={this.props.onDelete} />
        );

        return (
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    {users}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.employee);
    }
    render() {
        return (
            <tr>
                <td>{this.props.user.firstName}</td>
                <td>{this.props.user.lastName}</td>
                <td>{this.props.user.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary">Edit</Button>
                        <Button size="sm" color="danger" onCLick={this.handleDelete}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    }
}

export default UserList;