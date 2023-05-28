import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accountService/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountOperation} from "../model/account.model";

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
  currentPage:number=0;
  pageSize:number=5;
  totalPages!:number;

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

    this.operationObservable = this.accountService.getOperationsAccount(this.accountId, this.currentPage, this.pageSize).pipe(
      catchError(err=>{
        return throwError(err);
      }))

    this.operationObservable.subscribe((operationObservable: AccountOperation[])=>{
      if(operationObservable.length>0){
        this.totalPages = operationObservable[0].totalPages;
      }
    })

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

  gotoPage(page:number) {
    this.currentPage=page;
    this.handleSearchAccount();
  }
}
