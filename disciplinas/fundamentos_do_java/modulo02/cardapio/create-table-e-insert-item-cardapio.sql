create table item_cardapio (
    id                bigint primary key auto_increment,
    nome              varchar(100) not null,
    descricao         varchar(1000),
    categoria         enum('ENTRADAS', 'PRATOS_PRINCIPAIS', 'BEBIDAS', 'SOBREMESA') not null,
    preco             decimal(9, 2) not null,
    preco_promocional decimal(9, 2)
);

insert into item_cardapio (nome, descricao, categoria, preco, preco_promocional)
values ('Refresco do Chaves', 'Suco de limão que parece de tamarindo e tem gosto de groselha.', 'BEBIDAS', 2.99, null),
       ('Sanduíche de Presunto do Chaves', 'Sanduíche de presunto simples, mas feito com muito amor.', 'PRATOS_PRINCIPAIS', 3.50, 2.99),
       ('Torta de Frango da Dona Florinda', 'Torta de frango com recheio cremoso e massa crocante.', 'PRATOS_PRINCIPAIS', 12.99, 10.99),
       ('Pipoca do Quico', 'Balde de pipoca preparado com carinho pelo Quico.', 'PRATOS_PRINCIPAIS', 4.99, 3.99),
       ('Água de Jamaica', 'Água aromatizada com hibisco e toque de açúcar.', 'BEBIDAS', 2.50, 2.00),
       ('Café da Dona Florinda', 'Café forte para começar o dia com energia.', 'BEBIDAS', 1.99, 1.50),
       ('Churros do Chaves', 'Churros recheados com doce de leite, clássicos e irresistíveis.', 'SOBREMESA', 4.99, 3.99),
       ('Gelatina Colorida do Nhonho', 'Gelatina de várias cores, a sobremesa favorita do Nhonho.', 'SOBREMESA', 3.50, 2.99),
       ('Bolo de Chocolate da Dona Clotilde', 'Bolo de chocolate com cobertura de brigadeiro.', 'SOBREMESA', 5.99, 4.99);