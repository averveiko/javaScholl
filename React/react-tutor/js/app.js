
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

var Article = React.createClass({
    render: function() {
        var author = this.props.data.author;
        var text = this.props.data.text;
        
        return (
            <div className='article'>
                <p className='news__author'>{author}:</p>
                <p className='news__text'>{text}</p>
            </div>
        );
    }
});

var News = React.createClass({
    render: function() {
        var data = this.props.data;
        var newsTemplate;

        if(data.length > 0) {
            newsTemplate = data.map(function(item, index){
                return (
                    <div key={index}>
                        <Article data={item} />
                    </div>
                );
            });
        } else {
            newsTemplate = <p>Нет новых новостей</p>;
        }

        return (
            <div className="news">
                {newsTemplate}
                <p className={data.length > 0 ? '':'none'}>
                    <strong className='news__count'>
                        Всего новостей: {data.length}
                    </strong>
                </p>
            </div>
        );
    }
});

var App = React.createClass({
    render: function() {
        return (
            <div className="app">
                <h3>Новости</h3>
                <News data={my_news}/>
                {/* <Comments /> */}
            </div>
        );
    }
});

ReactDOM.render(
    <App />,
    document.getElementById('root')
);