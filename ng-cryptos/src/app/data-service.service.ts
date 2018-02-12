import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./model/wallet";

@Injectable()
export class DataService {

  constructor(public http:HttpClient) { }

  fetchUsers(): Promise<User[]>{
    return this.http
      .get('http://localhost:8080/cryptos/api/users')
      .toPromise()
      .then(data => {
        console.log(data);
        return data as User[]
      }) //attend que la requete soit finit pour envoyer le json
      }

  fetchUserWithWallets (user:User):Promise<User>{

    let url = ('http://localhost:8080/cryptos/api/users/' + user.id);
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with waller : ', data);
        return data as User
      })
  }
  createWallet(wallet: Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets';

    let dto = { //Data transfer object. aux petits oignons pour Jax-B
      name:wallet.name,
      user:wallet.user
    };
    console.log('Sending wallet:' , dto);
    //When  posting, we send DATA to the url
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success : )' , data))
    // .catch(e => console.log('Fail : (', e))

  }



  createUser(user: User)
    {
      let url = 'http://localhost:8080/cryptos/api/users';

      let dto = { //Data transfer object. aux petits oignons pour Jax-B
        name:user.name,
      };


     console.log('Sending User:' , dto);
      //When  posting, we send DATA to the url



      return this
        .http
        .post(url, dto)
        .toPromise()
        .then(data => console.log('Success : )' , data))

    }




}
