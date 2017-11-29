### Необходимые библиотеки
* React <script src="js/react/react.js"></script>
* React-dom <script src="js/react/react-dom.js"></script>
* Babel <script src="js/react/browser.min.js"></script>

```js
//Простой компонент
ReactDOM.render(
    <h1>Привет Александр!</h1>,
    document.getElementById('root')
);

//Или
var App = React.createClass({
    render: function() {
        return (
            <div className="app">
                Это компонент App!
            </div>
        );
    }
});

ReactDOM.render(
    <App />,
    document.getElementById('root')
);
```
