import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import classNames from "classnames";
import PropTypes from "prop-types";
import {TextInputMask} from 'react-masked-text';

import { Form } from "./styles";

import api from "../../services/api";
import MaskedInput from 'react-text-mask'
import ViaCep from 'react-via-cep';

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

    getCep = e => {      
      e.preventDefault();
      const { cep, logradouro } = this.state;
      const url = `https://viacep.com.br/ws/${cep}/json`;      
      try {
       fetch(url)
        .then(response => response.json())
        .then(json => {
          console.log(json.logradouro);    
          this.setState({ logradouro: json.logradouro,
            bairro: json.bairro,
            complemento: json.complemento,
            cidade: json.localidade,
            uf: json.uf
         });                      
        }); 
      
      } catch (err) {
          console.log(err);          
            this.setState({ error: "Ocorreu algum erro ao buscar o cep" });                    
      }
    }    

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
        <MaskedInput
          mask={[/[1-9]/, /\d/, /\d/,'.',/[1-9]/, /\d/, /\d/,'.',/[1-9]/, /\d/, /\d/,'-',/\d/, /\d/]}
          className="form-control"
          placeholder="CPF"
          guide={false}          
          onBlur={this.getCep}
          onChange={e => this.setState({ cpf: e.target.value })}
        />        
        
        <MaskedInput
          mask={[/[1-9]/, /\d/, /\d/, /\d/, /\d/, '-',/[1-9]/, /\d/, /\d/]}
          className="form-control"
          placeholder="CEP"
          guide={false}          
          onBlur={ this.getCep.bind(this) }
          onChange={e => this.setState({ cep: e.target.value })}
        />             

        <input
            type="text"
            placeholder="Logradouro"
            value={ this.state.logradouro}
            onChange={e => this.setState({ logradouro: e.target.value })}
        />
        <input
            type="text"
            placeholder="Bairro"
            value={ this.state.bairro}
            onChange={e => this.setState({ bairro: e.target.value })}
        />
        <input
            type="text"
            placeholder="Cidade"
            value={ this.state.cidade}
            onChange={e => this.setState({ cidade: e.target.value })}
        />
        <input
            type="text"
            placeholder="UF"
            value={ this.state.uf}
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