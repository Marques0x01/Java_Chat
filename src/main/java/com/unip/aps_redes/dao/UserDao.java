 package com.unip.aps_redes.dao;

import com.unip.aps_redes.connection.ConnectionFactory;
import com.unip.aps_redes.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    
    private Connection con = null;
    private String ipServer;

    public UserDao(String ip){
        this.ipServer = ip;
        con = ConnectionFactory.getConnection(ip);
    }

    public void saveUser(User user) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = con.prepareStatement("INSERT INTO tb_user(username, pass) values (?, ?)");

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            System.out.println( "Salvo com sucessso");
        } catch (SQLException ex) {
            System.err.println("Não foi possivel salvar usuario");
        } finally {
            ConnectionFactory.closeConnection(con, statement);
        }
    }

    public boolean checkLogin(String username, String password) {

        Connection con = ConnectionFactory.getConnection(ipServer);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM tb_user WHERE username = ? and pass = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.err.println("Não foi possivel completar a ação. " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }
    
        public boolean isRegister(String username) {

        Connection con = ConnectionFactory.getConnection(ipServer);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM tb_user WHERE username = ?");
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.err.println("Não foi possivel completar a ação. " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }
}
