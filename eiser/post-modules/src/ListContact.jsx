import React, {Component,ReactPropTypes} from 'react'
import PropTypes from 'prop-types'
import escapeRegExp from 'escape-string-regexp'
import sortBy from 'sort-by'

class ListContact extends Component{
    state = {
        query:''
    }
    updateQuery = (query) => {
        this.setState({ query: query.trim() })
    }
    render(){
        let showingContact 
        if (this.state.query) {
            const match = new RegExp(escapeRegExp(this.state.query),'i')
            showingContact = this.props.contacts.filter((contact) => match.test(contact.name))
        }
        else{
            showingContact = this.props.contacts
        }
        console.log('Props',this.props)
        showingContact.sort(sortBy('name'))
        return(
            <div className='List-Contact'>
                {JSON.stringify(this.state)}
                <div className = 'List-Contact-Top'>
                  <input
                  className='Search-Contact'
                  type = 'text'
                  placeholder='Search Contact'
                  value={this.state.query}
                  onChange={(event) => this.updateQuery(event.target.value)} 
                  >

                  </input>
                </div>
            <ol className='contact-list'>{
                    showingContact.map((contact) => (
                        <li key={contact.id}>
                            <div className='contact-details'>
                                <p>{contact.name}</p>
                                <p>{contact.Height}</p>
                            </div>
                            <button onClick={() => this.props.onDeleteContact(contact)} className='remove'>Remove</button>
                        </li>
                    ))}
            </ol>
            </div>
        )
    }
}
ListContact.propTypes = {
    contacts:PropTypes.array.isRequired,
    onDeleteContact:PropTypes.func.isRequired
}
export default ListContact;