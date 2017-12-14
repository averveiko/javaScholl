import React from 'react';

const style = {
    cell: {
        display: 'flex',
        margin: 5,
        width: 100,
        height: 100,
        border: 1,
        backgroundColor: 'lightgrey',
        borderRadius: '10%',
        fontSize: 60,
        alignItems: 'center',
        justifyContent: 'center',
    }
}

const Cell = (props) => {
    return (
        <div style={style.cell} onClick={props.onClick}>
            {props.value}
        </div>
    );
}

// class Cell extends Component {
//   render() {
//     return (
//       <div style={style.cell} onClick={this.props.onClick}>
//         {this.props.value}
//       </div>
//     );
//   }
// }

export default Cell;