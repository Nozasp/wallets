import {Injectable} from '@angular/core';
import {BunService} from "./bun.service";
import {SteakService} from "./steak.service";

@Injectable()
export class BurgerService {

  //on crée les attributs bunService et steakService qui sont exportables et publics
  constructor(public bunService:BunService, public steakService: SteakService) {
  }

  getPrice() {
    return 10;
  }

}
