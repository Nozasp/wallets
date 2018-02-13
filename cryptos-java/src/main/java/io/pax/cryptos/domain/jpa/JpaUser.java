package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity /** Entity = objet en base de donnéee*/
public class JpaUser implements User {

    @Id /** Primary Key*/
        @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;

    @OneToMany //Permet de gérer tout seul les jointures
    List<JpaWallet> wallets = new ArrayList<>();

    //unwritten default empty constructor

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<JpaWallet> getWallets() {
        return this.wallets; //this.wallets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public void setWallets(List<Wallet> wallets) {
        // this.wallets = wallets; On met les wallets de côté pour le moment
    }
*/
    @Override
    public String toString(){
        return this.getName();
    }

}
