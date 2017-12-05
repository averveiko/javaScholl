import React, { Component } from 'react';

import Header from "./Header";
import RegistrationForm from "./RegistrationForm";

import './App.css'

const menu = [
    {
        link: '/arcticles',
        label: 'Articles'
    },
    {
        link: '/contacts',
        label: 'Contacts'
    },
    {
        link: '/posts',
        label: 'Posts'
    }
];

class App extends Component {
    submit() {
        console.log("submit", this.testInput.value);
    }
    render() {
        return (
            <div className="container">
                <Header items={menu}/>

                <RegistrationForm/>

                <p>
                    <input
                        type="text"
                        placeholder="test"
                        // При рендере вызывается функция, первым параметром получает сам элемент,
                        // присваивает его в this.testInput, который теперь доступен в любом месте нашего класса
                        ref={(input) => this.testInput = input}
                    />
                    <button onClick={this.submit.bind(this)}>Submit</button>
                </p>

            </div>
        );
    }
}

export default App;