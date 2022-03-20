CREATE TABLE User(
   login_user VARCHAR(30) NOT NULL, 
   pwd VARCHAR(30) NOT NULL,
   isBibliothecaire boolean NOT NULL,
   data_user VARCHAR(255), 
   PRIMARY KEY(login_user),
   CHECK (LENGTH(login_user) >= 3 AND LENGTH(login_user)<= 16)
);

CREATE TABLE Document(
   id_doc INT,
   title VARCHAR(30) NOT NULL, 
   author VARCHAR(30) NOT NULL,
   type_doc VARCHAR(30) NOT NULL,
   disponible BOOLEAN,
   PRIMARY KEY(id_doc),
   CONSTRAINT U_Document UNIQUE (title, author),
   CHECK (LENGTH(author) >= 3)
);
