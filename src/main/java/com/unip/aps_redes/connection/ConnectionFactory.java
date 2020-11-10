package com.unip.aps_redes.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_chat?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static Connection getConnection(String ip) {

        try {
            Class.forName(DRIVER);
            System.out.println("Conectado com sucesso");
            String url = "jdbc:mysql://" + ip + ":3306/db_chat?useTimezone=true&serverTimezone=UTC&user=root&password=root";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar conexão", ex);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement statement) {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }

        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement statement, ResultSet result) {

        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }

        closeConnection(con, statement);
    }
}
