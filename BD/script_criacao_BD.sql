-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cartao
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cartao
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cartao` DEFAULT CHARACTER SET utf8 ;
USE `cartao` ;

-- -----------------------------------------------------
-- Table `cartao`.`RamoAtividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cartao`.`RamoAtividade` (
  `idRamoAtividade` INT NOT NULL AUTO_INCREMENT,
  `nomeAtividade` VARCHAR(50) NULL,
  `taxaMinimaCredito` DECIMAL(4,2) NULL,
  `taxaMinimaDebito` DECIMAL(4,2) NULL,
  PRIMARY KEY (`idRamoAtividade`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cartao`.`Concorrente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cartao`.`Concorrente` (
  `idConcorrente` INT NOT NULL AUTO_INCREMENT,
  `nomeConcorrente` VARCHAR(45) NULL,
  `taxaCredito` DECIMAL(4,2) NULL,
  `taxaDebito` DECIMAL(4,2) NULL,
  PRIMARY KEY (`idConcorrente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cartao`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cartao`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nomeCliente` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `idRamoAtividade` INT NOT NULL,
  `idConcorrente` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Cliente_RamoAtividade_idx` (`idRamoAtividade` ASC),
  INDEX `fk_Cliente_Concorrente1_idx` (`idConcorrente` ASC),
  CONSTRAINT `fk_Cliente_RamoAtividade`
    FOREIGN KEY (`idRamoAtividade`)
    REFERENCES `cartao`.`RamoAtividade` (`idRamoAtividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Concorrente1`
    FOREIGN KEY (`idConcorrente`)
    REFERENCES `cartao`.`Concorrente` (`idConcorrente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cartao`.`Proposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cartao`.`Proposta` (
  `idSimulacao` INT NOT NULL AUTO_INCREMENT,
  `dataSimulacao` DATE NULL,
  `descontoCredito` DECIMAL(4,2) NULL,
  `descontoDebito` DECIMAL(4,2) NULL,
  `situacao` TINYINT(1) NULL,
  `taxaFinalCredito` DECIMAL(4,2) NULL,
  `taxaFinalDebito` DECIMAL(4,2) NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idSimulacao`),
  INDEX `fk_Simulacao_Cliente1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Simulacao_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `cartao`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
