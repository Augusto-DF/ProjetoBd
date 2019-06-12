CREATE DATABASE IF NOT EXISTS restaurante;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restaurante` DEFAULT CHARACTER SET utf8 ;
USE `restaurante` ;

-- -----------------------------------------------------
-- Table `mydb`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Pessoa` (
  `cpf` VARCHAR(12) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `datanasc` DATE NOT NULL,
  `senha` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Funcionario` (
  `cpf` VARCHAR(12) NOT NULL,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC),
  PRIMARY KEY (`cpf`),
  CONSTRAINT `cpf1`
    FOREIGN KEY (`cpf`)
    REFERENCES `restaurante`.`Pessoa` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Cliente` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `CPF2`
    FOREIGN KEY (`CPF`)
    REFERENCES `restaurante`.`Pessoa` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Gerente` (
  `cpf` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`),
  CONSTRAINT `CPF3`
    FOREIGN KEY (`cpf`)
    REFERENCES `restaurante`.`Funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Supervisionado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Supervisionado` (
  `cpf` VARCHAR(12) NOT NULL,
  `cpf_supervisor` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `cpf_supervisor_idx` (`cpf_supervisor` ASC),
  CONSTRAINT `CPF4`
    FOREIGN KEY (`cpf`)
    REFERENCES `restaurante`.`Funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cpf_supervisor`
    FOREIGN KEY (`cpf_supervisor`)
    REFERENCES `restaurante`.`Gerente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Pedido` (
  `idPedido` INT NOT NULL,
  `cpf_cliente` VARCHAR(12) NOT NULL,
  `forma_pagamento` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `cpf_cliente_idx` (`cpf_cliente` ASC),
  CONSTRAINT `cpf_cliente`
    FOREIGN KEY (`cpf_cliente`)
    REFERENCES `restaurante`.`Cliente` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Garçom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Garçom` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `cpf5`
    FOREIGN KEY (`CPF`)
    REFERENCES `restaurante`.`Supervisionado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cozinheiro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Cozinheiro` (
  `CPF` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT `cpf6`
    FOREIGN KEY (`CPF`)
    REFERENCES `restaurante`.`Supervisionado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Estoque` (
  `idEstoque` INT NOT NULL,
  `CPF_gerente` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`idEstoque`),
  INDEX `cpf_gerente_idx` (`CPF_gerente` ASC),
  CONSTRAINT `cpf_gerente`
    FOREIGN KEY (`CPF_gerente`)
    REFERENCES `restaurante`.`Gerente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Itens_do_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Itens_do_Pedido` (
  `item` VARCHAR(50) NOT NULL,
  `IdPedido` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `preparado` TINYINT(1) NOT NULL,
  `entregue` TINYINT(1) NOT NULL,
  #mudar para cpf do garçom
  `idgarcom_resp` INT NOT NULL,
  `detalhes` VARCHAR(45) NULL,
  `id_estoque` INT NOT NULL,
  PRIMARY KEY (`IdPedido`),
  CONSTRAINT `idPedido`
    FOREIGN KEY (`IdPedido`)
    REFERENCES `restaurante`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_estoque`
    FOREIGN KEY (id_estoque)
    REFERENCES `restaurante`.`Estoque` (idEstoque)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Itens_do_Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`Itens_do_Estoque` (
  `id_estoque` INT NOT NULL,
  `Item` VARCHAR(50) NOT NULL,
  `quantidade` INT(8),
  CONSTRAINT `id_estoque1`
    FOREIGN KEY (`id_estoque`)
    REFERENCES `restaurante`.`Estoque` (`idEstoque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
