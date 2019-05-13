INSERT INTO permits(name) VALUES('PERMISSAO_ADMIN');
INSERT INTO permits(name) VALUES('PERMISSAO_COMUM');

--password: 123456
INSERT INTO users(login, password, CREATED_AT, UPDATED_AT) VALUES('admin', '$2a$10$1zhxT2mb6bnNsz9i1Vh5veKBAMAxBfjBhXYEYQjhDXzGWGWyIo70i', '2019-05-10', '2019-05-10');
INSERT INTO users(login, password, CREATED_AT, UPDATED_AT) VALUES('comum', '$2a$10$1zhxT2mb6bnNsz9i1Vh5veKBAMAxBfjBhXYEYQjhDXzGWGWyIo70i', '2019-05-10', '2019-05-10');

INSERT INTO user_permit(user_id, permit_id) VALUES(1,1);
INSERT INTO user_permit(user_id, permit_id) VALUES(2,2);

INSERT INTO clients(nome,cpf,cep,logradouro,bairro,cidade,uf, CREATED_AT, UPDATED_AT, CREATED_BY, UPDATED_BY) VALUES('Teste', '12345678911', '12345-78', 'logradouro', 'bairro', 'cidade', 'DF','2019-03-20', '2019-03-20', 1, null );