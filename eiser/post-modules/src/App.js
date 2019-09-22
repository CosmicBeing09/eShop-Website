import React, {Component} from 'react';
import logo, { ReactComponent } from './logo.svg';
import './App.css';
import ListContact from './ListContact';



class App extends Component {

  state = {
    contacts : [
      {
        "id" : "111",
        'name' : "Raihan",
        "Age" : "19",
        "Height" : "6"
      },
      {
        "id" : "112",
        'name' : "Nazia",
        "Age" : "20",
        "Height" : "5"
      },
      {
        "id" : "113",
        'name' : "Nishat",
        "Age" : "21",
        "Height" : "5"
      },
      {
        "id" : "114",
        'name' : "Munif",
        "Age" : "22",
        "Height" : "4"
      }
    ]
  }

  removeContact = (contact) => {
    this.setState((state)=> ({
    contacts:state.contacts.filter((c)=> c.id !== contact.id)
    }))
  }

  render(){
  return (
    <div className="App">
     <ListContact
      onDeleteContact={this.removeContact}
      contacts={this.state.contacts}/>
    </div>
  );
}
}

export default App;