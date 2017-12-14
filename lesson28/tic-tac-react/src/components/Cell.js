import React, { Component } from 'react';

const style = {
    cell: {
        display: 'flex',
        margin: 5,
        width: 100,
        height: 100,
        border: 1,
        backgroundColor: 'lightgrey',
        borderRadius: '10%',
        fontSize:58,
        alignItems: 'center',
        justifyContent: 'center',
    }
}


class Cell extends Component {
  render() {
    return (
      <div style={style.cell} onClick={this.props.onClick}>
        {this.props.value}
      </div>
    );
  }
}

export default Cell;