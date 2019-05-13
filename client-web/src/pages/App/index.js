import React, { Component, Fragment } from "react";
import PropTypes from "prop-types";
import { Link, withRouter } from "react-router-dom";
import Logo from "../../assets/logo.svg";
import { logout } from "../../services/auth";
import Button from "./components/Button";
import { Form, Container, ButtonContainer, NewButtonContainer, PointReference } from "./styles";
import TableRow from './components/TableRow';
import api from "../../services/api";
import { ModalRoute } from "react-router-modal";
import AddClient from "../AddClient";



class App extends Component {  

  // constructor(props) {
  //     super(props);
  //     this.state = {clients: []};
  //   }

    componentDidMount(){
      this.loadClients();      
    }

    loadClients = async () => {
      
      try {
        const response = await api.get("/api/clients/all");
        this.setState({ clients: response.data.content });
        console.log(this.state.clients);
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
    name: "",	
    cpf: "",	
	  cep: "",	
	  logradouro: "",	
	  bairro: "",	
	  cidade: "",	
	  uf: "",	
	  complemento: "",	
    error: "",
    clients: [],
    properties: []
  };

  handleLogout = e => {
    logout();
    this.props.history.push("/");
  };

  renderNewButton() {
    return (      
      <NewButtonContainer onPress={this.handleNewRealtyPress}>
        <Button color="#222" >
          <i className="fa fa-plus-circle" />
        </Button>
      </NewButtonContainer>
    );
  }

  // renderActions() {
  //   return (      
  //     <ButtonContainer>
  //       <Button color="#222" onClick={this.handleLogout}>
  //         <i className="fa fa-times" />
  //       </Button>
  //     </ButtonContainer>
  //   );
  // }

  renderActions() {
    return (
      <ButtonContainer>
        <Button
          color="#fc6963"
          onClick={() => this.setState({ addActivate: true })}
        >
          <i className="fa fa-plus" />
        </Button>
        <Button color="#222" onClick={this.handleLogout}>
          <i className="fa fa-times" />
        </Button>
      </ButtonContainer>
    );
  }

  renderButtonAdd() {
    return (
      this.state.addActivate && (
        <PointReference>
          <i className="fa fa-map-marker" />
          <div>
            <button onClick={this.handleAddProperty} type="button">
              Adicionar
            </button>
            <button
              onClick={() => this.setState({ addActivate: false })}
              className="cancel"
            >
              Cancelar
            </button>
          </div>
        </PointReference>
      )
    );
  }

  handleAddProperty = () => {
    const { match, history } = this.props;
    history.push(
      `${match.url}/clients/add`
    );
    
    this.setState({ addActivate: false });
  };

 
  render() {
    const { containerWidth: width, containerHeight: height, match } = this.props;
    const { properties, addActivate } = this.state;
    return (
      <Fragment>
      <div>
          <h3 align="center">Clientes</h3>
          <table className="table table-striped" style={{ marginTop: 20 }}>
            <thead>
              <tr>
                <th>Nome</th>
                <th>CPF</th>
                <th>CEP</th>
                <th colSpan="2">Action</th>
              </tr>
            </thead>
            <tbody>
              { this.tabRow() }
            </tbody>
          </table>
        </div>
        {!addActivate && <Properties match={match} properties={properties} />}
      {this.renderNewButton()}
      {this.renderActions()}      
      {this.renderButtonAdd()}
      <ModalRoute
        path={`${match.url}/clients/add`}
        parentPath={match.url}
        component={AddClient}
      />
      </Fragment>
    );
  }
}


export default App;