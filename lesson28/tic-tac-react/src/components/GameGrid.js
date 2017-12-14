import React, { Component } from 'react';

import Cell from './Cell';

const style = {
  gameGrid: {
    display: 'flex',
    flexDirection: 'column'
  },

  gameRow: {
    display: 'flex',
    flexDirection: 'row'
  }
}

class GameGrid extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cellsState: [
        null, null, null,
        null, null, null,
        null, null, null
      ],
      
      currentPlayer: 'X'
    };
  }

  changePlayerState() {
      if (this.state.currentPlayer === 'X') {
          this.setState({currentPlayer: 'O'});
      } else {
        this.setState({currentPlayer: 'X'});
      }
  }

  changeCellState(index) {
      //console.log('change', index);
      //console.log(this.state.currentPlayer);

      const newCellsState = this.state.cellsState.slice();
      newCellsState[index] = this.state.currentPlayer;

      //Меняем знак в ячейке
      this.setState({cellsState: newCellsState});
      //Смена игрока после хода
      this.changePlayerState();
      

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
    return (
      <div>
        <h1>Tic-tac-react</h1>
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
      </div>
    );
  }
}

export default GameGrid;