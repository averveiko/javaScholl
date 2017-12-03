
window.ee = new EventEmitter();

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

var Add = React.createClass({
    getInitialState: function() {
        return {
            agreeNotChecked: true,
            authorIsEmpty:true,
            textIsEmpty: true
        }
    },
    componentDidMount: function() {
        ReactDOM.findDOMNode(this.refs.author).focus();
    },
    onCheckRuleClick : function(e) {
        //ReactDOM.findDOMNode(this.refs.alert_button).disabled = !e.target.checked;
        this.setState({agreeNotChecked: !this.state.agreeNotChecked});
    },
    onBtnClickHandler: function(e) {
        e.preventDefault
        var textEl = ReactDOM.findDOMNode(this.refs.text);
        
        var author = this.refs.author.value;
        var text = this.refs.text.value;
        
        var item = [{
            author: author,
            text: text,
            bigText: '...'
        }];

        window.ee.emit('News.add', item);

        textEl.value = '';
        this.setState({textIsEmpty: true});
    },
    onAuthorChange: function(e) {
        if (e.target.value.trim().length > 0) {
            this.setState({authorIsEmpty: false})
          } else {
            this.setState({authorIsEmpty: true})
          }
    },
    onTextChange: function(e) {
        if (e.target.value.trim().length > 0) {
            this.setState({textIsEmpty: false})
          } else {
            this.setState({textIsEmpty: true})
          }
    },
    render: function() {
        var agreeNotChecked = this.state.agreeNotChecked,
            authorIsEmpty = this.state.authorIsEmpty,
            textIsEmpty = this.state.textIsEmpty;

        return (
            <form className='add cf'>
                <input
                    type='text'
                    className='add__author'
                    defaultValue=''
                    placeholder='Ваше имя'
                    ref='author'
                    onChange={this.onAuthorChange}
                />
                <textarea 
                    className='add__text'
                    defaultValue=''
                    placeholder='Текст новости'
                    ref='text'
                    onChange={this.onTextChange}
                ></textarea>
                <label className='add__checkrule'>
                    <input
                    type='checkbox'
                    defaultChecked={false}
                    ref='checkrule'
                    onChange={this.onCheckRuleClick}
                    />
                    Я согласен с правилами
                </label>
                <button
                    className='add_btn'
                    onClick={this.onBtnClickHandler}
                    ref='alert_button'
                    disabled={agreeNotChecked || authorIsEmpty || textIsEmpty}>
                    Добавить новость
                    </button>
            </form>
        );
    }
});

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
    minimizeClick: function(e) {
        e.preventDefault();
        this.setState({visible: false});
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
                <a href="#"
                    onClick={this.readmoreClick}
                    className={'news__readmore ' + (visible ? 'none' : '')}>
                    Подробнее
                </a>
                <p className={'news__big-text ' + (visible ? '' : 'none')}>
                    {bigText}
                </p>
                <a href="#"
                    onClick={this.minimizeClick}
                    className={'news__minimize ' + (visible ? '' : 'none')}>
                    Меньше
                </a>
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
                    <strong
                        className='news__count'>
                        Всего новостей: {data.length}
                    </strong>
                </p>
            </div>
        );
    }
});

var App = React.createClass({
    getInitialState: function() {
        return {
            news: my_news
        }
    },
    componentDidMount: function() {
        var self = this;

        window.ee.addListener('News.add', function(item) {
            var nextNews = item.concat(self.state.news);
            self.setState({news: nextNews});
        });
    },
    componentWillUnmount: function() {
        window.ee.removeListener('News.add');
    },
    render: function() {
        return (
            <div className="app">
                <h3>Новости</h3>
                <Add />
                <News data={this.state.news}/>
                {/* <Comments /> */}
            </div>
        );
    }
});

ReactDOM.render(
    <App />,
    document.getElementById('root')
);