import React from 'react';

const style = {
    gameWinner: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    }
};

const Winner = (props) => {
    return (
        <div style={style.gameWinner}>
            <h1>Сongratulations!</h1>
            <h2>{props.gameWinner} is winner!</h2>
            <button onClick={props.changeGameState}>New game</button>
        </div>
    );
};

export default Winner;