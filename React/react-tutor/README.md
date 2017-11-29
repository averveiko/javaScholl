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

//Компонент с данными
var my_news = [
    {
        author: 'Иван Иванович',
        text: 'Бесплатно, без смс! качайте на localhost:3000'
    },
    {
        author: 'Илон Маск',
        text: 'Вылет на марс 12.05.2018, спешите купить билеты'
    },
    {
        author: 'Гость',
        text: 'Курс биткоина пробил исторический максимум'
    }
];

var News = React.createClass({
    render: function() {
        var data = this.props.data;
        
        var newsTemplate = data.map(function(item, index) {
            return (
                <div key={index}>
                    <p className="news__author">{item.author}:</p>
                    <p className="news__author">{item.text}</p>
                </div>
            );
        });

        return (
            <div className="news">
                {newsTemplate}
            </div>
        );
    }
});

<News data={my_news}/>
```
