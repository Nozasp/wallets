package io.pax.cryptos.dao;

import io.pax.cryptos.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class UserDao {

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *FROM user");

        //On parcours notre liste de résult et on run
        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            users.add(new SimpleUser(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();
        return users;
    }

    public User findUserWithWallets(int userId) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "SELECT * FROM wallet w RIGHT JOIN user u ON w.user_id=u.id WHERE u.id =?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);

        ResultSet rs = statement.executeQuery();

        User user = null;
        //pro tip : always init lists
        List<Wallet> wallets = new ArrayList<>();

        while (rs.next()){  //On rempli toute les lignes

            String userName = rs.getString("u.name");
            System.out.println("userName:" + userName);
            user = new FullUser(userId, userName, wallets);

            int walletId = rs.getInt("w.id");
            String walletName = rs.getString("w.name");

            if (walletId > 0) {
                Wallet wallet = new SimpleWallet(walletId, walletName);
                wallets.add(wallet);
            }

        }
        rs.close();
        statement.close();
        connection.close();

        return user;
    }


    public int createUser(String name) throws SQLException {

        //MOST important stuff of your life: NEVER EVER String concatenation in JDBC
        String query = "INSERT INTO user (name) VALUES (?)";

        //query = "INSERT INTO wallet (name, user_id) VALUES ('test', 2)";


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();

        int id = keys.getInt(1);


        statement.close();
        conn.close();

    return id;
    }





 public void deleteUser(int userid) throws SQLException {
        String query = "DELETE FROM user WHERE id = ?";

        //query = "INSERT INTO wallet (name, user_id) VALUES ('test', 2)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, userid);
        statement.executeUpdate();

        statement.close();
        conn.close();


    }



    public List<User> findByName(String extract) throws SQLException {
        List<User> findByName = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        String query = "SELECT * FROM user WHERE name LIKE ?";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, extract + "%");
        ResultSet rs = statement.executeQuery();


        //On parcours not re liste de résult et on run
        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            findByName.add(new SimpleUser(id, name));
        }

        rs.close();
        statement.close();
        conn.close();
        return findByName ;
    }





    //On rajoute ici une list de user ?
   /* public List<Wallet> findByName(String extract) throws SQLException {
        List<Wallet> findByName = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        String query = "SELECT * FROM wallet WHERE name LIKE ?";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, extract + "%");
        ResultSet rs = statement.executeQuery();


        //On parcours not re liste de résult et on run
        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            findByName.add(new SimpleWallet(id, name));

        }}*/








    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
         dao.createUser( "yo");

   //dao.deleteUser( 46);
//        System.out.println(dao.findByName("Wo"));
        System.out.println(dao.findUserWithWallets(2));

        //Cree un wallet que si la variable id est utilisé plus tard


        //System.out.println(dao.findByName("Yo"));
        //dao.updateWallet(2, "serieux");

        //dao.deleteAll(6);
        // dao.deleteByName("Yellow");



    }



    }
