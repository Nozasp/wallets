//Controller

import {Component, OnInit} from '@angular/core';
import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";


import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";


@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  //money = 1000;
  wallet = new Wallet();
  initDollar = '20000';


  constructor(public pricingService: PricingService) {
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()
      .then(data => console.log('>>>>', data))

     .then( () => this.initWallet())
     }
  //     ^^^ function without param ; prices are now loaded




  initWallet(){

    this.deposit(this.initDollar)
    this.buy('1', 'BTC' );

    //this.initwallet.deposit(parseFloat(100));
    //console.log(this.initwallet.lines);                      // todo
  }



  ngOnInit() {


  }


  deposit(value: string) {
    // return this.money += parseFloat(value);
    this.wallet.deposit(parseFloat(value));
    console.log(this.wallet.lines);

    //console.log('Deposition value:', value);
    //  let money = parseFloat(value)

    // if (money > 0) {
    //  this.wallet.deposit();
    //}

  }

  buy(quantity: string, symbol: string) {
    this.wallet.buy(parseFloat(quantity), symbol);
    console.log(this.wallet.lines);
  }



  sell(quantity: string, symbol: string) {
    this.wallet.sell(parseFloat(quantity), symbol);
    console.log(this.wallet.lines);
  }



getColor(symbol){
    return this.pricingService.getColor(symbol);
}

}


//pay(){
//  return this.wallet -=  100;
//}
//}
