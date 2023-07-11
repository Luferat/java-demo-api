-- Insere dados fake na tabela 'users'.
INSERT INTO users (udate, uname, uemail, upassword, uphoto, ubio, ubirth, utype, ustatus) VALUES
('2023-06-01 10:11:12', 'Joca da Silva', 'joca@silva.com', 'senha123', 'https://randomuser.me/api/portraits/men/52.jpg', 'Computador de rosquinhas.', '2000-10-11', 'author', 'on'),
('2023-06-02 20:21:22', 'Setembrino Trecatapas', 'set@brinocom', 'senha123', 'https://randomuser.me/api/portraits/men/53.jpg', 'Catador de latinhas de cerveja cheias.', '1080-12-14', 'moderator', 'on');

-- Insere artigos fake na tabela 'articles'.
INSERT INTO articles ( adate, aauthor, atitle, athumbnail, aresume, acontent, aviews, astatus ) VALUES 
( '2023-06-01 10:11:12', '1', 'Primeiro artigo da parada', 'https://picsum.photos/200', 'Este é o primeiro artigo do nosso blog.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas a ultrices leo. Vivamus in suscipit quam. Sed posuere erat non massa vehicula laoreet.', '0', 'on' ),
( '2023-06-08 23:24:25', '1', 'Mais um artigo para o blog', 'https://picsum.photos/199', 'Resumo do artigo que va aparecer no blog', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas a ultrices leo. Vivamus in suscipit quam. Sed posuere erat non massa vehicula laoreet.', '0', 'on' ),
( '2023-06-10 12:44:55', '1', 'Próximo artigo para do blog', 'https://picsum.photos/198', 'Esse é só mais um artigo fake', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas a ultrices leo. Vivamus in suscipit quam. Sed posuere erat non massa vehicula laoreet.', '0', 'on' ),
( '2023-06-05 15:16:17', '1', 'Segundo artigo da parada', 'https://picsum.photos/201', 'Este é o segundo artigo do nosso blog.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas a ultrices leo. Vivamus in suscipit quam. Sed posuere erat non massa vehicula laoreet.', '0', 'on' );

-- Insere dados fake na tabela 'social'.
INSERT INTO social (uid, name, link, status) VALUES
('1', 'Facebook', 'https://facebook.com', 'on'),
('1', 'Instagram', 'https://instagram.com', 'on'),
('2', 'Facebook', 'https://facebook.com', 'on'),
('2', 'Instagram', 'https://instagram.com', 'on');

-- Insere dados fake na tabela 'comments'.
INSERT INTO comments (cdate, fbname, fbphoto, fbemail, fbuid, carticle, ccomment, cstatus) VALUES
('2023-06-02 10:10:10', 'Ermenilda', 'https://randomuser.me/api/portraits/women/80.jpg', 'erme@nilcda.com', 'q1w2e3r4t5y5', '3', 'Somente mais um comentário.', 'on'),
('2023-06-02 10:20:30', 'Serenézio', 'https://randomuser.me/api/portraits/men/80.jpg', 'sere@nezio.com', 'w34r5rt6tyy7y', '3', 'Somente mais outro comentário.', 'on'),
('2023-06-09 10:10:10', 'Ermenilda', 'https://randomuser.me/api/portraits/women/80.jpg', 'erme@nilcda.com', 'q1w2e3r4t5y5', '2', 'Somente mais um comentário.', 'on'),
('2023-06-10 10:10:10', 'Genezita', 'https://randomuser.me/api/portraits/women/81.jpg', 'gene@zita.com', 'f5yn8t487t83jtg', '2', 'Somente mais outro comentário.', 'on');