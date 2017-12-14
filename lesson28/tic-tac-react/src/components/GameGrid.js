import React, { Component } from 'react';

import Cell from './Cell';

const cellsState = [
    'X', 'O', 'O',
    'O', 'X', 'X',
    'O', '', 'X'
];

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
  render() {
    return (
      <div>
        <h1>Tic-tac-react</h1>
        <div style={style.gameGrid}>
          {/* {cellsState.map((item, index) =>
            <Cell state={item} key={index} />)} */}
            <div style={style.gameRow}>
                <Cell state={cellsState[0]} />
                <Cell state={cellsState[1]} />
                <Cell state={cellsState[2]} />
            </div>
            <div style={style.gameRow}>
                <Cell state={cellsState[3]} />
                <Cell state={cellsState[4]} />
                <Cell state={cellsState[5]} />
            </div>
            <div style={style.gameRow}>
                <Cell state={cellsState[6]} />
                <Cell state={cellsState[7]} />
                <Cell state={cellsState[8]} />
            </div>
        </div>
      </div>
    );
  }
}

export default GameGrid;