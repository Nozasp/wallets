import { Component, OnInit } from '@angular/core';
import {BurgerService} from "../burger.service";

@Component({
  selector: 'app-attributes',
  templateUrl: './attributes.component.html',
  styleUrls: ['./attributes.component.css']
})
export class AttributesComponent implements OnInit {
  isDisabled = true; //Attribute de la class component
  height = 0;
  constructor(public burgerService: BurgerService) {

    burgerService.bunService.getHeight()
      .then(result => this.height = result)

  }

  ngOnInit() {
   // var b = document.querySelector('button');
   // b.disabled = true;
  }

  toggle()
  {
    this.isDisabled = !this.isDisabled;
  }
  nop(){

  }
}
