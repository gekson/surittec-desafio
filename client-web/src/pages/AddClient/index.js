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
      logradouro: "",
      bairro: "",
      cidade: "",
      uf: "",
      complemento: "",
      error: ""
    };
  
    componentDidMount() {
      
    }

    handleSubmit = async e => {
        e.preventDefault();
    
        try {
          const { name, cpf, cep, logradouro, bairro, cidade, uf, complemento } = this.state;
    
          if (!name || !cpf || !cep || !logradouro || !bairro || !cidade || !uf) {
            this.setState({ error: "Preencha todos os campos" });
            return;
          }
    
          const { data: { id } } = await api.post("/api/clients/add", {
            name,
            cpf,
            cep,
            logradouro,
            bairro,
            cidade,
            uf,
            complemento
          });

          this.props.history.push("/app");
        } catch (err) {
          console.log(err);
          if (err.response.status === 403) {
              this.setState({ error: "Usuário sem permissão para adicionar Cliente." });
          }else{
            this.setState({ error: "Ocorreu algum erro ao adicionar o cliente" });          
          }
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

        <input
            type="text"
            placeholder="Logradouro"
            onChange={e => this.setState({ logradouro: e.target.value })}
        />
        <input
            type="text"
            placeholder="Bairro"
            onChange={e => this.setState({ bairro: e.target.value })}
        />
        <input
            type="text"
            placeholder="Cidade"
            onChange={e => this.setState({ cidade: e.target.value })}
        />
        <input
            type="text"
            placeholder="UF"
            onChange={e => this.setState({ uf: e.target.value })}
        />
        <input
            type="text"
            placeholder="Complemento"
            onChange={e => this.setState({ complemento: e.target.value })}
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