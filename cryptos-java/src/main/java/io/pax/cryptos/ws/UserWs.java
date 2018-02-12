package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */


@Path("users")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")//This is a PathParam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallets(userId);
    }

    @GET
    public List<Wallet> getWallet() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }



    @POST
        public SimpleUser createUser(SimpleUser user) {

        String userName = user.getName();

        if (user == null) {
            throw new NotAcceptableException("No user is sent");
        }

        if (user.getName().length() < 2) { //On valide les informations du bonhome 406
            throw new NotAcceptableException("406 : Wallet name must have at least 2 letters ");
        }
        try {
            int id = new UserDao().createUser(user.getName());

            //List <Wallet> wallets = new ArrayList<>();

            return new SimpleUser(id, userName);

        } catch (SQLException e) {
            //Breaks POLA (
            throw new ServerErrorException("Database error, sorry", 500); //On met un numero pour le serverErrorException
        }
    }

    }




