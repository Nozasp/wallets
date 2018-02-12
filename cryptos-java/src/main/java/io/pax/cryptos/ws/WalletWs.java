package io.pax.cryptos.ws;

import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.FullWallet;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */

@Path("wallets") //chemin relatif

@Produces(MediaType.APPLICATION_JSON)  //Prend notre objet et le met en json comme ca pas besoin de faire d html pour afficher notre page
@Consumes(MediaType.APPLICATION_JSON)


public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    //JaxRS annotations
    @POST
    /* returns future wallet with an id */
    public FullWallet createWallet(FullWallet wallet) { /* sent wallet has no id */
        User user = wallet.getUser(); // On decrit comme optional plutot que nul
        if (user == null) {
            throw new NotAcceptableException("No user is sent");
        }

        if (wallet.getName().length() < 2) { //On valide les informations du bonhome 406
            throw new NotAcceptableException("406 : Wallet name must have at least 2 letters ");
        }

        try {
            int id = new WalletDao().createWallet(user.getId(), wallet.getName());

            User boundUser = wallet.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return new FullWallet(id, wallet.getName(),simpleUser);

        } catch (SQLException e) {
        //Breaks POLA (
            throw new ServerErrorException("Database error, sorry", 500); //On met un numero pour le serverErrorException
        }

    }
}