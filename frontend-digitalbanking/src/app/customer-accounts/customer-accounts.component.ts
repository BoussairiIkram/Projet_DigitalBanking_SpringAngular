import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {catchError, Observable, throwError} from "rxjs";
import {BankAccount} from "../model/account.model";
import {AccountsService} from "../services/accountService/accounts.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {
  customerId! : number;
  customer! : Customer;
  accounts! : Observable<Array<BankAccount>>;
  constructor(private route:ActivatedRoute,
              private router: Router,
              private  accoutsService: AccountsService) {
    this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.customerId=this.route.snapshot.params['id'];

    this.accounts=this.accoutsService.getAccountsCustomer(this.customerId).pipe(
      catchError(err=>{
        return throwError(err);
      })
    )
  }

}
