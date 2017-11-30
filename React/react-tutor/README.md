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
