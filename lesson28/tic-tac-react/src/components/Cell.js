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
        fontWeight: 'bold',
        alignItems: 'center',
        justifyContent: 'center',
    }
};

const Cell = (props) => {
    return (
        <div style={style.cell} onClick={props.onClick}>
            {props.value}
        </div>
    );
};

export default Cell;