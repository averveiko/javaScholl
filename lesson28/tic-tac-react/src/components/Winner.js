import React, { Component } from 'react';

import GameGrid from './GameGrid';

const style = {
    gameWinner: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    }
}

class Winner extends Component {
    constructor(props) {
        super(props);
        this.state = {
            newGame: false
        };

        this.buttonHandler = this.buttonHandler.bind(this);
    }

    buttonHandler() {
        this.setState({ newGame: true });
    }

    render() {
        if (this.state.newGame) {
            return <GameGrid />;
        }
        return (
            <div style={style.gameWinner}>
                <h1>Сongratulations!</h1>
                <h2>{this.props.gameWinner} is winner!</h2>
                <button onClick={this.buttonHandler}>New game</button>
            </div>
        );
    }
}

// const Winner = (props) => {
//     return (
//         <div style={style.gameWinner}>
//             <h1>Сongratulations!</h1>
//             <h2>{props.gameWinner} is winner!</h2>
//             <button onClick={props.onClick}>New game</button>
//         </div>
//     );
// }

export default Winner;