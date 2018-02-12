import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../model/user";
import {Wallet} from "../model/wallet";


@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];
  selectedUser : User = new User();
  createdWallet : Wallet = new Wallet();
  createdUser : User = new User();

  constructor(public dataService: DataService) {

    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users: ', users))

  }

  ngOnInit() {
  }

  details(user: User) {

    //Created model associated to the form

    this.selectedUser = user;
    this.createdWallet = new Wallet();
    this.createdWallet.user = user;
    this.createdWallet.name = user.name + "s wallet";
  this.createdUser = new User();
  this.createdUser.name = "you created : " + user.name ;
    console.log('You selected', user);
    console.log('You created', user.name);

    this.dataService
      .fetchUserWithWallets(user)
      .then(fullUser => this.selectedUser = fullUser)
      .then(console.log); //what?
  }

  createWallet(){
    this.dataService.createWallet(this.createdWallet)
      .then(() => this.selectedUser.wallets.push(
        Object.assign({}, this.createdWallet)
      ))
      .catch (e => alert(e));
     }

  createUser(){
    this.dataService.createUser(this.createdUser)
      .then(() => this.users.push(
        Object.assign({}, this.createdUser)
      ))
      .catch (e => alert(e._messages));
  }

}
