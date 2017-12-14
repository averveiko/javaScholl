import React, { Component } from 'react';

import GameGrid from './components/GameGrid'

const style = {
  game: {
    display: 'flex',
    justifyContent: 'center',
  }
}

class App extends Component {
  render() {
    return (
      <div style={style.game}>
        <GameGrid />
      </div>
    );
  }
}

export default App;
