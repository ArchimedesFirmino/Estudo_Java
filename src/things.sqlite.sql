DROP TABLE IF EXISTES things;

CREATE TABLE things (
    id INT PRIMARY KEY,
    name VARCHAR(63),
    description VARCHAR(127)
);

INSERT INTO things (name, description) VALUES
('Pelo de iguana', 'Pelinhos bem pequenos e amarelados.'),
('Perna de cobra', 'Grande e forte, permitindo correr muito.'),
('Orelha de pernilongo', 'Não escuta bem. Só tem zumbido.'),
('Bigode de sapo', 'Chique, fashion e másculo.'),
('Olhos de cobra cega', 'Ainda não vimos um melhor.');