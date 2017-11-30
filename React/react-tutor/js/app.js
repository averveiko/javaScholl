
var my_news = [
    {
        author: 'Иван Иванович',
        text: 'Бесплатно, без смс! качайте на localhost:3000',
        bigText: 'Писатель Виктор Пелевин стал лауреатом премии Андрея Белого в области прозы. Его наградили за роман 2017 года «iPhuck 10», сообщает телеканал «Культура».'
        
    },
    {
        author: 'Илон Маск',
        text: 'Вылет на марс 12.05.2018, спешите купить билеты',
        bigText: 'В номинации «Поэзия» победителем стал Станислав Львовский с книгой «Стихи из книги и другие стихи».'
    },
    {
        author: 'Гость',
        text: 'Курс биткоина пробил исторический максимум',
        bigText: 'Премия Андрея Белого позиционирует себя как премия, ориентированная на инновации в литературе. Она также отмечает заслуги тех, кто внес «особый вклад» в развитие российской литературы. Премия появилась в 1978 году — ее присуждала редакция самиздатовского журнала «Часы».'
    }
];

//var my_news;

var Article = React.createClass({
    propTypes: {
        data: React.PropTypes.shape({
            author: React.PropTypes.string.isRequired,
            text: React.PropTypes.string.isRequired
        })
    },
    getInitialState: function() {
        return {
            visible: false
        };
    },
    readmoreClick: function(e) {
        e.preventDefault();
        this.setState({visible: true});
    },
    render: function() {
        var author = this.props.data.author,
            text = this.props.data.text,
            bigText = this.props.data.bigText;

        var visible = this.state.visible;

        
        return (
            <div className='article'>
                <p className='news__author'>{author}:</p>
                <p className='news__text'>{text}</p>
                <a href="#" onClick={this.readmoreClick} className={'news__readmore ' + (visible ? 'none' : '')}>Подробнее</a>
                <p className={'news__big-text ' + (visible ? '' : 'none')}>{bigText}</p>
            </div>
        );
    }
});

var News = React.createClass({
    propTypes: {
        data: React.PropTypes.array.isRequired
    },
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