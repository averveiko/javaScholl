
var Comments = React.createClass({
    render: function() {
        return (
            <div className="comments">
                Нет новостей - комментировать нечего.
            </div>
        );
    }
});

var News = React.createClass({
    render: function() {
        return (
            <div className="news">
                Пока новостей нет.
            </div>
        );
    }
});

var App = React.createClass({
    render: function() {
        return (
            <div className="app">
                Это компонент App! Я умею отображать новости.
                <News />
                <Comments />
            </div>
        );
    }
});

ReactDOM.render(
    <App />,
    document.getElementById('root')
);