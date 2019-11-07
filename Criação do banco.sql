CREATE DATABASE IF NOT EXISTS bar;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bar` DEFAULT CHARACTER SET utf8 ;
USE `bar` ;

CREATE TABLE IF NOT EXISTS `bar`.`Pessoa` (
  `cpf` VARCHAR(12) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `datanasc` VARCHAR(10) NOT NULL,
  `senha` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Funcionario` (
  `cpf` VARCHAR(12) NOT NULL,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC),
  PRIMARY KEY (`cpf`),
  CONSTRAINT `cpf1`
    FOREIGN KEY (`cpf`)
    REFERENCES `bar`.`Pessoa` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Cliente` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `CPF2`
    FOREIGN KEY (`CPF`)
    REFERENCES `bar`.`Pessoa` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Gerente` (
  `cpf` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`),
  CONSTRAINT `CPF3`
    FOREIGN KEY (`cpf`)
    REFERENCES `bar`.`Funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Supervisionado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Supervisionado` (
  `cpf` VARCHAR(12) NOT NULL,
  `cpf_supervisor` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `cpf_supervisor_idx` (`cpf_supervisor` ASC),
  CONSTRAINT `CPF4`
    FOREIGN KEY (`cpf`)
    REFERENCES `bar`.`Funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cpf_supervisor`
    FOREIGN KEY (`cpf_supervisor`)
    REFERENCES `bar`.`Gerente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Pedido` (
  `idPedido` INT NOT NULL auto_increment,
  `cpf_cliente` VARCHAR(12) NOT NULL,
  `forma_pagamento` VARCHAR(20) NOT NULL,
  `fecharPedido` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY (`cpf_cliente`),
  INDEX `cpf_cliente_idx` (`cpf_cliente` ASC),
  CONSTRAINT `cpf_cliente`
    FOREIGN KEY (`cpf_cliente`)
    REFERENCES `bar`.`Cliente` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Garçom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Garçom` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `cpf5`
    FOREIGN KEY (`CPF`)
    REFERENCES `bar`.`Supervisionado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cozinheiro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Cozinheiro` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `cpf6`
    FOREIGN KEY (`CPF`)
    REFERENCES `bar`.`Supervisionado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Estoque` (
  `idEstoque` INT NOT NULL,
  `CPF_gerente` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`idEstoque`),
  INDEX `cpf_gerente_idx` (`CPF_gerente` ASC),
  CONSTRAINT `cpf_gerente`
    FOREIGN KEY (`CPF_gerente`)
    REFERENCES `bar`.`Gerente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Itens_do_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Itens_do_Pedido` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `item` VARCHAR(50) NOT NULL,
  `IdPedido` INT NOT NULL,
  `preparado` TINYINT(1) NOT NULL,
  `entregue` TINYINT(1) NOT NULL,
  `quantidade` INT,
  #mudar para cpf do garçom
  `cpfgarcom_resp` VARCHAR(12) NOT NULL,
  `detalhes` VARCHAR(45) NULL,
  `id_estoque` INT NOT NULL,
  PRIMARY KEY (`idItem`),
  CONSTRAINT `idPedido`
    FOREIGN KEY (`IdPedido`)
    REFERENCES `bar`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_estoque11`
    FOREIGN KEY (id_estoque)
    REFERENCES `bar`.`Estoque` (idEstoque)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
     CONSTRAINT `cpfgarcom`
    FOREIGN KEY (cpfgarcom_resp)
    REFERENCES `bar`.`garçom` (cpf)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Itens_do_Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bar`.`Itens_do_Estoque` (
  `id_estoque` INT NOT NULL,
  `Item` VARCHAR(50) NOT NULL,
  `quantidade` INT(8),
  `valor` DOUBLE NOT NULL,
  CONSTRAINT `id_estoque1`
    FOREIGN KEY (`id_estoque`)
    REFERENCES `bar`.`Estoque` (`idEstoque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
