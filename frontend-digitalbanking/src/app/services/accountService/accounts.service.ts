import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {AccountOperation, BankAccount, OperationRequest} from "../../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http:HttpClient) { }

  public getOperationsAccount(accountId:String):Observable<Array<AccountOperation>>{
    return this.http.get<Array<AccountOperation>>(environment.backendHost+"/api/accounts/operation/all/"+accountId);
  }

  public debit(operationRequest:OperationRequest):Observable<OperationRequest>{
    return this.http.post<OperationRequest>(environment.backendHost+"/api/accounts/operation/debit",operationRequest);
  }
  public credit(operationRequest:OperationRequest):Observable<OperationRequest>{
    return this.http.post<OperationRequest>(environment.backendHost+"/api/accounts/operation/credit",operationRequest);
  }
  public transfert(operationRequest:OperationRequest):Observable<OperationRequest>{
    return this.http.post<OperationRequest>(environment.backendHost+"/api/accounts/operation/transfert",operationRequest);
  }

  public getAccountsCustomer(customerId:number):Observable<Array<BankAccount>>{
    return this.http.get<Array<BankAccount>>(environment.backendHost+"/api/accounts/customer/"+customerId);
  }


}
