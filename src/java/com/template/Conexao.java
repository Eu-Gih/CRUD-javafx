package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.template.LivroDAO.logger;

// Configuração e abertura da conexão com o banco PostgreSQL
public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/Livros";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao conectar ao banco", e);
        }
        return null;
    }
}