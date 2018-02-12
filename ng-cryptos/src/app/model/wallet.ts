import {PricingService} from "../pricing.service";
import {User} from "./user";
export class Line {
  constructor(public quantity: number, public symbol: string) {

  }
}

export class Wallet {
  user?: User;
  name:String;
  lines: Line[] = [];
  pricingService: PricingService;
  //  private coins_1: any;

  deposit(dollars: number) {
    //On définit ici la ligne USD
    let usdLine = this.lines
      .find(line => line.symbol === 'USD');

    if (usdLine === undefined) {
      this.lines.push(new Line(dollars, 'USD',));
    } else {
      return usdLine.quantity += dollars;

    }

  }

  buy(quantity: number, symbol: string) {
    let usdLine = this.lines
      .find(line => line.symbol === 'USD');

    let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);
    usdLine.quantity = usdLine.quantity - symbolPrice;

    let symbolLine = this.lines
      .find(Line => Line.symbol === symbol);

    if (symbolLine === undefined) {
      this.lines.push(new Line(quantity, symbol));
    } else {
      symbolLine.quantity += quantity;

    }
  }


  //New amount
  // this.lines.push(new Line(symbol, quantity));


  //total amount en dollar


  totalDollarValue(): number {
    let total = 0;
    for (let i = 0; i < this.lines.length; i++) {
      let line = this.lines[i];
      if (line.symbol === 'USD') {
        total = total + line.quantity;
      } else {
        total = total + this.pricingService.priceToDollar(line.quantity, line.symbol)
      }
    }
    return total;


  }


//SELL CRYPTOS

  sell(quantity: number, symbol: string) {
    let cryptosLine = this.lines
      .find(line => line.symbol === symbol);
    cryptosLine.quantity = cryptosLine.quantity - quantity;


    /*  let usdLine = this.lines
     .find(line => line.symbol === 'USD');
     let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);
     cryptoLine.quantity = c.quantity + symbolPrice;*!/
     */

    let usdLine = this.lines
      .find(line => line.symbol === 'USD');
    let cryptosSymbolPrice = this.pricingService.priceToDollar(quantity, symbol);

    if (usdLine === undefined) {
      this.lines.push(new Line(cryptosSymbolPrice, 'USD'));
    } else {
      usdLine.quantity += cryptosSymbolPrice;
    }

  }


}
//        let line = this.lines.find(coin => coin.symbol === 'USD');
//    let dollarAmount = line.quantity;
//      let coinAmount = priceToDollar(quantity,symbol);
//new amount en USD
//  line.quantity = dollarAmount - coinAmount; //=newAmount

//New amound
// this.lines.push(new Line(symbol, quantity));


//const wallet = new Wallet();

//wallet.buy(30000, 'XRP');
//wallet.buy(2, 'BCH');
//console.log(wallet.lines);


//console.log(wallet.value());
///wallet.sell(1.2, 'BTC');


