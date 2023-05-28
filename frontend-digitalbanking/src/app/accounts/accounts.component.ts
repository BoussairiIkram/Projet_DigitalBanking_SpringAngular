import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accountService/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountOperation} from "../model/account.model";
import {Customer} from "../model/customer.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormGroup! : FormGroup;
  operationObservable! : Observable<Array<AccountOperation>>;
  accountId! : string;
  operationFormGroup! : FormGroup;

  constructor(private fb:FormBuilder,
              private accountService: AccountsService) { }

  ngOnInit(): void {
    this.accountFormGroup=this.fb.group({
      accountId:this.fb.control('')
    })

    this.operationFormGroup=this.fb.group({
      operationType : this.fb.control(null),
      amount : this.fb.control(0),
      description : this.fb.control(null),
      idBankAccountDestination :  this.fb.control(null)
    })


  }

  handleSearchAccount() {
    this.accountId=this.accountFormGroup.value.accountId;
    this.operationObservable = this.accountService.getOperationsAccount(this.accountId).pipe(
      catchError(err=>{
        return throwError(err);
      }))
  }

  handleAccountOperation() {
    let operationType=this.operationFormGroup.value.operationType;
    let operation=this.operationFormGroup.value;
    operation.idBankAccountSource= this.accountId;

    if(operationType=='debit'){
      this.accountService.debit(operation).subscribe(
        {
          next : data=>{
            alert("Debit Operation has been successfully saved!");
            this.operationFormGroup.reset();
            this.handleSearchAccount();
            //this.router.navigateByUrl("/customers");
          }, error : err =>{
            console.log(err);
        }}
      )
    }else if(operationType=='credit'){
      operation.idBankAccountDestination=operation.idBankAccountSource;
      this.accountService.credit(operation).subscribe(
        {
          next : data=>{
            alert("Debit Operation has been successfully saved!");
            this.operationFormGroup.reset();
            this.handleSearchAccount();
            //this.router.navigateByUrl("/customers");
          }, error : err =>{
            console.log(err);
          }}
      )

    }else if(operationType=='transfert'){
      this.accountService.transfert(operation).subscribe(
        {
          next : data=>{
            alert("Debit Operation has been successfully saved!");
            this.operationFormGroup.reset();
            this.handleSearchAccount();
            //this.router.navigateByUrl("/customers");
          }, error : err =>{
            console.log(err);
          }}
      )
    }


  }
}
