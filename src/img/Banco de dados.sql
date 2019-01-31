CREATE DATABASE ensalamentoAD;
USE ensalamentoAD;

CREATE TABLE IF NOT EXISTS curso (
	idcurso INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeCurso VARCHAR (50) NOT NULL,
    cargaHoraria DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS turma (
	idturma INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeTurma VARCHAR (45) NOT NULL,
    quantidadeALuno INT NOT NULL,
    idcurso INT NOT NULL,
    CONSTRAINT FK_turma_curso_1
    FOREIGN KEY (idcurso) REFERENCES curso (idcurso)
);

CREATE TABLE IF NOT EXISTS disponibilidade (
	iddisponibilidade INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    diadasemana VARCHAR (7) NOT NULL
);

CREATE TABLE IF NOT EXISTS sala (
	idsala INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (20) UNIQUE NOT NULL,
    lugar INT NOT NULL,
    computador INT NOT NULL
);

CREATE TABLE IF NOT EXISTS unidadecurricular (
	idunidadecurricular INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (50) UNIQUE NOT NULL,
    cargaHoraria INT NOT NULL
 );

CREATE TABLE IF NOT EXISTS professor (
	idprofessor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (45) NOT NULL,
    cpf CHAR (14) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS professor_uc (
	idprofessor_uc INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idunidadecurricular INT NOT NULL,
    idprofessor INT NOT NULL,
    CONSTRAINT FK_professor_uc_idunidadecurricular_1
    FOREIGN KEY (idunidadecurricular) REFERENCES unidadecurricular (idunidadecurricular),
    CONSTRAINT FK_professor_uc_professor_1
    FOREIGN KEY (idprofessor) REFERENCES professor (idprofessor)
);

CREATE TABLE IF NOT EXISTS curso_uc (
	idcurso_uc INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idunidadeCurricular INT NOT NULL,
    idcurso INT NOT NULL,
    CONSTRAINT FK_curso_uc_curso_1
    FOREIGN KEY (idcurso) REFERENCES curso (idcurso),
    CONSTRAINT FK_cuso_uc_unidadeCurricular_1
    FOREIGN KEY (idunidadeCurricular) REFERENCES unidadeCurricular (idunidadeCurricular)
    
    
);

CREATE TABLE IF NOT EXISTS professor_disp (
	idprofessor_disp INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idprofessor INT NOT NULL,
    iddisponibilidade INT NOT NULL,
    CONSTRAINT FK_prof_disp_disponibilidade_1
    FOREIGN KEY (iddisponibilidade) REFERENCES disponibilidade (iddisponibilidade),
    CONSTRAINT FK_prof_disp_professor_1
    FOREIGN KEY (idprofessor) REFERENCES professor (idprofessor)
);

CREATE TABLE IF NOT EXISTS preEnsalamento (
	idpreensalamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeEnsalamento VARCHAR (30) NOT NULL,
    idturma INT NOT NULL,
    idunidadecurricular INT NOT NULL,
    CONSTRAINT FK_preEnsalamento_turma_1
    FOREIGN KEY (idturma) REFERENCES turma (idturma),
    CONSTRAINT FK_preEnsalamento_unidadecurricular_1
    FOREIGN KEY (idunidadecurricular) REFERENCES unidadecurricular (idunidadecurricular)
);

INSERT INTO disponibilidade (diadasemana)
	VALUES ("segunda"),("terca"),("quarta"),("quinta"),("sexta" ),("alvo" );


DELIMITER $$
CREATE TRIGGER tr_deleta_professor
BEFORE DELETE ON professor
FOR EACH ROW
main:BEGIN
	
    DELETE FROM professor_disp WHERE idprofessor = OLD.idprofessor;
    DELETE FROM professor_uc WHERE idprofessor = OLD.idprofessor;
    
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tr_deleta_curso
BEFORE DELETE ON curso
FOR EACH ROW
main:BEGIN
	
    DELETE FROM curso_uc WHERE idcurso = OLD.idcurso;
    
END $$
DELIMITER ;

insert into sala (nome, lugar, computador) values ("G-22", 20, 15), ("E-12", 30, 30), ("F-15", 27, 0);
insert into unidadecurricular (nome, cargaHoraria)
	values ("Programação Orientada a Objetos", 120), ("Criação e Manipulação de Banco de Dados", 90), ("Métodos e Técnicas de Pesquisa", 50);
insert into professor (nome, cpf) values ("Fulano", "45483657705"), ("Ciclano", "86334177745"), ("Beltrano", "22842038843");
insert into professor_disp (idprofessor, iddisponibilidade) 
	values (1,1),(1,2),(1,6),(1,6),(1,5),(1,6),(2,6),(2,6),(2,6),(2,2),(2,5),(2,6),(3,1),(3,6),(3,3),(3,6),(3,5),(3,6);
insert into curso (nomecurso, cargaHoraria)
	values ("Analise de Sistemas", 260), ("Superior de Tecnologia em Redes de Computadores", 140), ("Superior de Tecnologia em Sistemas Embarcados", 170);
insert into curso_uc (idunidadeCurricular, idcurso)
	values (1,1),(2,1),(3,1),(2,2),(3,2),(1,1),(1,3),(3,3);
insert into professor_uc (idunidadecurricular, idprofessor)
	values (1,1),(1,2),(1,3),(2,1),(2,3),(3,2);
insert into turma (nomeTurma, quantidadeAluno, idcurso)
	values ("Turma 1", 25, 1), ("Turma 2", 20, 3), ("Turma 3", 40, 2);
insert into preensalamento (nomeEnsalamento, idturma, idunidadecurricular)
	values ("Ens 1", 2, 1), ("Ens 1", 2, 2), ("Ens 1", 2, 3),
			("Ens 2", 1, 1), ("Ens 2", 1, 3),
			("Ens 3", 3, 1);






