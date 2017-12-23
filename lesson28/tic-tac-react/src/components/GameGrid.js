import React, { Component } from 'react';

import Cell from './Cell';
import Winner from './Winner';

const style = {
    gameGrid: {
        display: 'flex',
        flexDirection: 'column'
    },

    gameRow: {
        display: 'flex',
        flexDirection: 'row'
    },

    h1: {
        display: 'flex',
        justifyContent: 'center',
    },

    message: {
        display: 'flex',
        justifyContent: 'center'
    },

    currentPlayer: {
        display: 'flex',
        justifyContent: 'center',

    }
};

const initState = {

    gameState: 'play',

    gameWinner: null,

    cellsState: [
        null, null, null,
        null, null, null,
        null, null, null
    ],

    currentPlayer: 'X',

    message: null
};

class GameGrid extends Component {
    constructor(props) {
        super(props);

        this.state = {...initState, cellsState: [...initState.cellsState]};

        this.changeGameState = this.changeGameState.bind(this);
    }

    changeGameState() {
        this.setState({...initState, cellsState: [...initState.cellsState]});
    }

    changePlayerState() {
        if (this.state.currentPlayer === 'X') {
            this.setState({ currentPlayer: 'O' });
        } else {
            this.setState({ currentPlayer: 'X' });
        }
    }

    changeCellState(index) {
        //Проверяем пустая ли ячейка
        if (this.state.cellsState[index] != null) {
            this.setState({ message: 'Select empty cell' });
            return;
        }
        this.setState({ message: null });

        const newCellsState = this.state.cellsState.slice();
        newCellsState[index] = this.state.currentPlayer;

        //Меняем знак в ячейке
        this.setState({ cellsState: newCellsState });

        //Проверяем на победу текущего игрока
        if (this.checkPlayerWin(this.state.currentPlayer, newCellsState)) {
            this.setState({
                gameState: 'winner',
                gameWinner: this.state.currentPlayer
            });
        }

        //Проверяем на ничью
        if(this.checkStandoff(newCellsState)) {
            this.setState({ message: 'Standoff. Pls refresh app :)' });
            return;
        }

        //Смена игрока после хода
        this.changePlayerState();
    }

    checkStandoff(newCellsState) {
        if (newCellsState.every((item) => item != null)) {
            return true;
        }

        return false;
    }

    checkPlayerWin(player, newCellsState) {
        //Выйгрышные комбинации
        const winsCells = [
            //вертикали
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            //горизонтали
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            //диагонали
            [0, 4, 8],
            [2, 4, 6]
        ];

        //Проверка комбинаций
        let win = false;
        winsCells.map((item) => {
            if (item.every((elem) => newCellsState[elem] === player)) {
                win = true;
            }
            if (win) return true;
        });

        return win;
    }

    renderCell(index) {
        return (
            <Cell
                value={this.state.cellsState[index]}
                onClick={() => this.changeCellState(index)}
            />
        );
    }
    
    render() {
        if (this.state.gameState === 'play') {
            return (
                <div>
                    <h1 style={style.h1}>Tic-tac-react</h1>
                    <p style={style.currentPlayer}>Current player is: {this.state.currentPlayer}</p>
                    <div style={style.gameGrid}>
                        {/* {cellsState.map((item, index) =>
                        <Cell state={item} key={index} />)} */}
                        <div style={style.gameRow}>
                            {this.renderCell(0)}
                            {this.renderCell(1)}
                            {this.renderCell(2)}
                        </div>
                        <div style={style.gameRow}>
                            {this.renderCell(3)}
                            {this.renderCell(4)}
                            {this.renderCell(5)}
                        </div>
                        <div style={style.gameRow}>
                            {this.renderCell(6)}
                            {this.renderCell(7)}
                            {this.renderCell(8)}
                        </div>
                    </div>
                    <p style={style.message}>{this.state.message}</p>
                </div>
            );
        } else if (this.state.gameState === 'winner') {
            return <Winner
                gameWinner={this.state.gameWinner}
                changeGameState={this.changeGameState}
            />;
        } else {
            return <p>Loading...</p>;
        }
    }
}

export default GameGrid;