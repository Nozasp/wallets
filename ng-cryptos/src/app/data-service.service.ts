import { Injectable } from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";

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

}
