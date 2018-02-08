import {Injectable} from '@angular/core';
import {Coin} from "./model/coins";
import {HttpClient} from "@angular/common/http";
@Injectable()
export class PricingService {
  //Asynchroneng
  coins: Coin[];


  constructor(public http: HttpClient) {
  }

/*
  getColor(symbol) {
    let line = this.coins.find(coin => coin.symbol === symbol)
    if (line.symbol !== 'USD') {

      if (line.up === true) {
        return 'green';
      }
      else {
        return 'red';
      }
    }
    else {
      return 'black';
    }
  }
*/

  getColor(symbol) {
    let line=this.coins.find(coin => coin.symbol === symbol)
    if (line !== undefined) {
      if (line.up === true) {
        return 'green'
      }
      else {
        return 'red'
      }
    }
    else { return 'black'}
  }




  loadPrices() {
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';

    function mapper(coin) {


      let up = coin.percent_change_1h > 0;


      return {
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up: up,

      }
    }


    return this.http.get(url)
      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper))
      .then(joliCoins => {
        this.coins = joliCoins;
        return joliCoins;

      });


  }

  priceToDollar(quantity, symbol) {

    for (let i = 0; i < this.coins.length; i++) {

      let coin = this.coins[i];
      if (symbol === coin.symbol) {

        return quantity * coin.price;


      }


    }
    console.log('It doesnt exist');
  }
}
