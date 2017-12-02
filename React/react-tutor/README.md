[Хороший туториал](https://maxfarseer.gitbooks.io/react-course-ru/)

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

//о propTypes
//
React.createClass({
  propTypes: {
    // Вы можете указать, каким примитивом должно быть свойство
    optionalArray: React.PropTypes.array,
    optionalBool: React.PropTypes.bool,
    optionalFunc: React.PropTypes.func,
    optionalNumber: React.PropTypes.number,
    optionalObject: React.PropTypes.object,
    optionalString: React.PropTypes.string,

    // Вы может указать, что свойство можеть быть одни из ...
    optionalUnion: React.PropTypes.oneOfType([
      React.PropTypes.string,
      React.PropTypes.number,
      React.PropTypes.instanceOf(Message)
    ]),

    // Вы можете указать, конкретную структуру объекта свойства
    optionalObjectWithShape: React.PropTypes.shape({
      color: React.PropTypes.string,
      fontSize: React.PropTypes.number
    }),

    // Вы можете указать, что свойство ОБЯЗАТЕЛЬНО
    requiredFunc: React.PropTypes.func.isRequired,

    // Если нужно указать, что свойство просто обязательно, и может быть любым примитивом
    requiredAny: React.PropTypes.any.isRequired,

  }
});

this.props //используем только для чтения
this.state //состояние
// Для изменения состояния, нужно обязательно использовать метод setState, а не просто перезаписывать значение переменной.

//нельзя вызывать setState в render: реакт видит изменилось состояние - начинает перерисовывать компонент - видит что изменилось состояние - начинает перерисовывать компонент...

//Например, у нас есть состояние:
//...
getInitialState: function() {
    return {
      visible: false,
      rating: 0,
      eshe_odno_svoistvo: 'qweqwe'
    };
  }
//...
//Чтобы изменить рейтинг, нужно написать следующий setState:
this.setState({rating: 100500})
Чтобы изменить все три свойства:
this.setState({
    rating: 100500,
    visible: true,
    eshe_odno_svoistvo: 'привет'
})
//Так же у setState есть возможность указать callback функцию, которая будет вызвана после того, как новое состояние "установится".
//...
readmoreClick: function(e) {
    e.preventDefault();
    this.setState({visible: true}, function() {
      alert('Состояние изменилось');
    });
  },
//...
```
Lifecycle-methods

* componentWillMount - компонент будет примонтирован. В данный момент у нас нет возможности посмотреть DOM элементы.
* componentDidMount - компонент примонтировался. В данный момент у нас есть возможность использовать refs, а следовательно это то самое место, где мы хотели бы указать установку фокуса. Так же, таймауты, ajax-запросы и взаимодействие с другими библиотеками стоит обрабатывать здесь.
* componentWillReceiveProps - компонент получает новые props. Этод метод не вызывается в момент первого render'a. В официальной документации очень хороший пример, пожалуй скопирую его:
componentWillReceiveProps - компонент получает новые props. Этод метод не вызывается в момент первого render'a. В официальной документации очень хороший пример, пожалуй скопирую его:
```js
componentWillReceiveProps: function(nextProps) {
  this.setState({
    likesIncreasing: nextProps.likeCount > this.props.likeCount
  });
}
```
Обратите внимание: в этот момент, старые props доступны как this.props, а новые props доступны в виде nextProps аргумента функции.
Так же, если вы вызываете setState внутри этого метода - не будет вызван дополнительный render.
* shouldComponentUpdate - должен ли компонент обновиться? На самом деле, обычно реакт сам отлично разбирается. Но иногда ручное управление позволяет существенно ускорить работу в "узких местах". С этим методом нужно работать очень аккуратно.
* componentWillUpdate - вызывается прямо перед render, когда новые props и state получены. В этом методе нельзя вызывать setState.
* componentDidUpdate - вызывается сразу после render. Не вызывается в момент первого render'а компонента.
* componentWillUnmount - вызывается сразу перед тем, как компонент будет удален из DOM.

[Полный список](https://facebook.github.io/react/docs/component-specs.html#lifecycle-methods)
