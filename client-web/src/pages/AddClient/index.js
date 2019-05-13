import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import querySearch from "stringquery";
import classNames from "classnames";
import PropTypes from "prop-types";

import { Form, File } from "./styles";

import api from "../../services/api";

class AddClient extends Component {
    static propTypes = {
      location: PropTypes.shape({
        search: PropTypes.string
      }).isRequired,
      history: PropTypes.shape({
        push: PropTypes.func
      }).isRequired
    };
  
    state = {
      name: "",
      cpf: "",
      cep: "",
      error: ""
    };
  
    componentDidMount() {
      const params = querySearch(this.props.location.search);
      if (
        !params.hasOwnProperty("name") ||
        !params.hasOwnProperty("cpf")
      ) {
        alert("É necessário definir o nome e cpf.");
        this.props.history.push("/app");
      }
  
      this.setState({ ...params });
    }

    handleSubmit = async e => {
        e.preventDefault();
    
        try {
          const { name, cpf, cep } = this.state;
    
          if (!name || !cpf || !cep ) {
            this.setState({ error: "Preencha todos os campos" });
            return;
          }
    
          const { data: { id } } = await api.post("/api/clients/add", {
            name,
            cpf,
            cep
          });
        } catch (err) {
            this.setState({ error: "Ocorreu algum erro ao adicionar o imóvel" });
          }
    };

    handleCancel = e => {
        e.preventDefault();
      
        this.props.history.push("/app");
      };
    
    render() {
    return (
        <Form onSubmit={this.handleSubmit}>
        <h1>Adicionar Cliente</h1>
        <hr />
        {this.state.error && <p>{this.state.error}</p>}
        <input
            type="text"
            placeholder="Nome"
            onChange={e => this.setState({ name: e.target.value })}
        />
        <input
            type="text"
            placeholder="CPF"
            onChange={e => this.setState({ cpf: e.target.value })}
        />
        <input
            type="text"
            placeholder="CEP"
            onChange={e => this.setState({ cep: e.target.value })}
        />
        
        <div className="actions">
            <button type="submit">Adicionar</button>
            <button onClick={this.handleCancel} className="cancel">
            Cancelar
            </button>
        </div>
        </Form>
    );
 }
}

export default withRouter(AddClient);