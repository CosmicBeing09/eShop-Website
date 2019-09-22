import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

const people = [
    {name :'Raihan'},
    {name :'Munif'},
    {name: 'Nayeem'}

]

const element = <ol>
    {people.map(person =>(<li> key = {person.name}>{person.name}</li>))}
</ol>
ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
