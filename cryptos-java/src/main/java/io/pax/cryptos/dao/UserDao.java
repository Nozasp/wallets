package io.pax.cryptos.dao;

import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

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


 /*  public void deleteUser(int userid) throws SQLException {
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
*/

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
      dao.createUser( "yo");





      // dao.deleteUser( 25);
        //Cree un wallet que si la variable id est utilisé plus tard


        //System.out.println(dao.findByName("Yo"));
        //dao.updateWallet(2, "serieux");

        //dao.deleteAll(6);
        //  dao.deleteByName("Yellow");



    }



    }
