INSERT INTO User (login_user, pwd , isBibliothecaire, data_user) VALUES
    ('Alonso', '', False , null),
    ('Brette', '', True, null),
    ('Ouziri', '', True, null),
    ('Bettayeb', '', False, null);


INSERT INTO Document (id_doc, title, author, type_doc, disponible) VALUES
    (1, 'La communauté de l\'anneau', 'J. R. R. Tolkien', 'LIVRE', true),
    (2, 'Les deux tours', 'J. R. R. Tolkien', 'LIVRE', true),
    (3, 'Le retour du Roi', 'J. R. R. Tolkien', 'LIVRE', true),
    (4, 'Le Parrain', 'Francis Ford Coppola', 'DVD', true),
    (5, 'Un espion ordinaire', 'Dominic Cooke', 'DVD', true),
    (6, 'Le Dernier duel', 'Ridley Scott', 'DVD', true),
    (7, 'Thriller', 'Michael Jackson', 'CD', true),
    (8, 'Metallica', 'Metallica', 'CD', true);
