# CollaboratorsManagement


Sistema de Cadastro de colaboradores

Software utilizados:
spring-jdbc
apache-maven-3.6.3-bin.zip
apache-tomcat-9.0.38-windows-x64
H2 1.3.165
jdk-11.0.8
springframework 3.2.9

---------------------------------------------------------------------------
Tabelas utilizadas no banco de dados H2:
	
COLLABORATOR - 
	Tipo "R" para representar um colaborador Regular/Comum.
	Tipo "M" para representar um Gestor/Manager.
COLLAB_PROJECT	- 
	Relacionamento entre as tabelas COLLABORATOR e PROJECT, para tipo "R"
	Constraint para limitar um Projeto por Colaborador.

MANAGER_PROJECT	-
	Relacionamento entre as tabelas COLLABORATOR e PROJECT, para tipo "M"
	Constraint para limitar um Gestor por Projeto.
PROJECT - Projeto associado a colaborador.
DEPARTMENT	 - Departamento associado a colaborador.


------------------------------------------------------------------------------
Script de Criação do Banco de Dados:



DROP TABLE IF EXISTS DEPARTMENT;
CREATE TABLE DEPARTMENT(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(50)
);

DROP TABLE IF EXISTS PROJECT;
CREATE TABLE PROJECT(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(50)
);

DROP TABLE IF EXISTS COLLABORATOR;
CREATE TABLE COLLABORATOR(
  id INTEGER auto_increment PRIMARY KEY,
  departmentId INTEGER,
  name VARCHAR(50),
  type VARCHAR(1),
CONSTRAINT FK_COLLAB_DEPT FOREIGN KEY (departmentId) REFERENCES DEPARTMENT(id)
);

DROP TABLE IF EXISTS COLLAB_PROJECT;
CREATE TABLE COLLAB_PROJECT(
  collaboratorId INTEGER PRIMARY KEY,
  projectId INTEGER,
  CONSTRAINT FK_COLLAB_PROJECT FOREIGN KEY (collaboratorId) REFERENCES COLLABORATOR(id)
);

DROP TABLE IF EXISTS MANAGER_PROJECT;
CREATE TABLE MANAGER_PROJECT(
  collaboratorId INTEGER,
  projectId INTEGER PRIMARY KEY,
  CONSTRAINT FK_MANAGER_PROJECT FOREIGN KEY (projectId) REFERENCES PROJECT(id),
  CONSTRAINT FK_MANAGER_PROJECT2 FOREIGN KEY (collaboratorId) REFERENCES COLLABORATOR(id)
);


-------------------------------------------------------------------------------

Script que popula o Banco de Dados.


INSERT INTO DEPARTMENT VALUES (DEFAULT, 'SALES');
INSERT INTO DEPARTMENT VALUES (DEFAULT, 'HUMAN RESOURCES');
INSERT INTO DEPARTMENT VALUES (DEFAULT, 'MARKETING');
INSERT INTO DEPARTMENT VALUES (DEFAULT, 'IT');
INSERT INTO DEPARTMENT VALUES (DEFAULT, 'RESEARCH');

INSERT INTO PROJECT VALUES (DEFAULT, 'BRADESCO');
INSERT INTO PROJECT VALUES (DEFAULT, 'RAIZEN');
INSERT INTO PROJECT VALUES (DEFAULT, 'SANOFI');
INSERT INTO PROJECT VALUES (DEFAULT, 'MAPFRE');
INSERT INTO PROJECT VALUES (DEFAULT, 'SULAMERICA');
INSERT INTO PROJECT VALUES (DEFAULT, 'SANTANDER');
INSERT INTO PROJECT VALUES (DEFAULT, 'ZURICH');
INSERT INTO PROJECT VALUES (DEFAULT, 'SODEXO');
INSERT INTO PROJECT VALUES (DEFAULT, 'SCHNEIDER');
INSERT INTO PROJECT VALUES (DEFAULT, 'COCA-COLA');

INSERT INTO COLLABORATOR VALUES (DEFAULT, 1, 'ED FIRINO', 'M');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 2, 'JACOB ALYSON', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 3, 'ANTHONY HOPKINS', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 4, 'THALES OF MILETUS', 'M');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 5, 'LEONARDO DA VINCI', 'M');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 3, 'ZEUS', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 4, 'NEO', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 2, 'SAURON', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 1, 'GANDALF', 'R');
INSERT INTO COLLABORATOR VALUES (DEFAULT, 5, 'EZIO AUDITORE DA FIRENZE', 'R');

INSERT INTO COLLAB_PROJECT VALUES (2,1);
INSERT INTO COLLAB_PROJECT VALUES (3,2);
INSERT INTO COLLAB_PROJECT VALUES (6,7);
INSERT INTO COLLAB_PROJECT VALUES (7,7);
INSERT INTO COLLAB_PROJECT VALUES (8,5);
INSERT INTO COLLAB_PROJECT VALUES (9,8);
INSERT INTO COLLAB_PROJECT VALUES (10,10);

INSERT INTO MANAGER_PROJECT VALUES (1,1);
INSERT INTO MANAGER_PROJECT VALUES (1,2);
INSERT INTO MANAGER_PROJECT VALUES (1,3);
INSERT INTO MANAGER_PROJECT VALUES (1,4);
INSERT INTO MANAGER_PROJECT VALUES (4,5);
INSERT INTO MANAGER_PROJECT VALUES (4,6);
INSERT INTO MANAGER_PROJECT VALUES (5,7);
INSERT INTO MANAGER_PROJECT VALUES (5,8);
INSERT INTO MANAGER_PROJECT VALUES (5,9);
INSERT INTO MANAGER_PROJECT VALUES (4,10);


----------------------------------------------------------------------------------




