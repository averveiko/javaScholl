import React, { Component } from 'react';

class RegistrationForm extends Component {

    constructor(props) {
        super(props)
        this.state = {
            email: ''
        };

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault(); // Отключает перезагрузку страницы
        console.log('form is submitted. email value is', this.state.email);
    }

    handleEmailChange(event) {
        console.log('email was changed', this);
        this.setState({email: event.target.value});
    }
    
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input
                    type="text"
                    placeholder="E-mail"
                    value={this.state.email}
                    onChange={this.handleEmailChange}
                />
            </form>

        );
    }
}

export default RegistrationForm;