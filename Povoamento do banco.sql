#Cpf de 1 digito so pra facilitar na hora de logar (versao inicial)

insert into pessoa (cpf, nome, datanasc, senha) values ("0", "Cliente M", "05/02/1995", "...");
insert into pessoa (cpf, nome, datanasc, senha) values ("1", "Gerente A", "05/02/1995", "...");
insert into pessoa (cpf, nome, datanasc, senha) values ("2", "Garçom Zé", "05/02/1995", "...");
insert into pessoa (cpf, nome, datanasc, senha) values ("3", "Garçom Tonho", "05/02/1995", "...");
insert into pessoa (cpf, nome, datanasc, senha) values ("4", "Cozinheiro Serju", "05/02/1995", "...");
insert into pessoa (cpf, nome, datanasc, senha) values ("5", "Cliente N", "05/02/1995", "...");

#Cliente 
insert into cliente values("0");
insert into cliente values("5");

#Funcionários
insert into funcionario (cpf) values ("1");
insert into funcionario (cpf) values ("2");
insert into funcionario (cpf) values ("3");
insert into funcionario (cpf) values ("4");

#Gerentes
insert into gerente (cpf) values ("1");

#Supervisionados
insert into supervisionado values ("2", "1");
insert into supervisionado values ("3", "1");
insert into supervisionado values ("4", "1");

#Cozinheiros
insert into cozinheiro values("4");

#Garçons
insert into garçom values("2");
insert into garçom values("3");

#Estoque
insert into estoque (idEstoque, CPF_gerente) values(123, "1");

#Itens Do estoque
insert into itens_do_estoque values(123, "Cerveja - Da qualidade", 20, 1.99);
insert into itens_do_estoque values(123, "Caipiroska - Da qualidade", 20, 1.99);
insert into itens_do_estoque values(123, "Tequila - Da qualidade", 20, 1.99);
insert into itens_do_estoque values(123, "Vinho - Da qualidade", 20, 1.99);
insert into itens_do_estoque values(123, "Corote - É corote né...", 20, 2.50);

#É isto
