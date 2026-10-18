
-- PRODUTOS
CREATE TABLE IF NOT EXISTS produto (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       nome TEXT NOT NULL,
                                       descricao TEXT NOT NULL,
                                       preco REAL NOT NULL,
                                       categoria TEXT NOT NULL,
                                       estoque INTEGER NOT NULL,
                                       imagem TEXT
);

-- CLIENTES
CREATE TABLE IF NOT EXISTS cliente (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       nome TEXT NOT NULL,
                                       email TEXT NOT NULL UNIQUE,
                                       senha_hash TEXT NOT NULL,
                                       cargo TEXT NOT NULL DEFAULT 'CLIENTE'
);
