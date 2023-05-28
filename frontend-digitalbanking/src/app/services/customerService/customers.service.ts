import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../../model/customer.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  constructor(private http:HttpClient) { }

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/api/customers/all")
  }
  public searchCustomers(keyword : string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/api/customers/search?keyword="+keyword);
  }
  public createCustomer(customer : Customer):Observable<Customer>{
    return this.http.post<Customer>(environment.backendHost+"/api/customers/add", customer);
  }
  public deleteCustomer(id : number){
    return this.http.delete(environment.backendHost+"/api/customers/"+id);
  }
}
