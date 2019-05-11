import React, { Component, Fragment } from "react";
import PropTypes from "prop-types";
import { Link, withRouter } from "react-router-dom";
import Logo from "../../assets/logo.svg";
import { logout } from "../../services/auth";
import Button from "./components/Button";
import { Form, Container, ButtonContainer } from "./styles";
import TableRow from './components/TableRow';
import api from "../../services/api";


class App extends Component {  

  constructor(props) {
      super(props);
      this.state = {clients: []};
    }

    componentDidMount(){
      this.loadClients();      
    }

    loadClients = async () => {
      
      try {
        const response = await api.get("/api/clients/all");
        this.setState({ clients: response.data });
      } catch (err) {
        console.log(err);
      }
    };
    tabRow(){
      return this.state.clients.map(function(object, i){
          return <TableRow obj={object} key={i} />;
      });
    }

  state = {
    login: "",
    email: "",
    password: "",
    error: ""
  };

  handleLogout = e => {
    logout();
    this.props.history.push("/");
  };

  renderActions() {
    return (
      <ButtonContainer>
        <Button color="#222" onClick={this.handleLogout}>
          <i className="fa fa-times" />
        </Button>
      </ButtonContainer>
    );
  }

 
  render() {
    return (
      <Fragment>
      <div>
          <h3 align="center">Clientes</h3>
          <table className="table table-striped" style={{ marginTop: 20 }}>
            <thead>
              <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>cpf</th>
                <th colSpan="2">Action</th>
              </tr>
            </thead>
            <tbody>
              { this.tabRow() }
            </tbody>
          </table>
        </div>
      {this.renderActions()}
      </Fragment>
    );
  }
}


export default App;