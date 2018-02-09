//Into the model

import {Wallet} from "./wallet";
export class User{
  name:string;
  id:number;
  //Protip: always better to initiate an array
  wallets:Wallet[] = []

}
