package com.template.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Configuração e abertura da conexão com o banco PostgreSQL
public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/Livros";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }

    }
}