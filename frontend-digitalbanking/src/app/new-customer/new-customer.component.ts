import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Customer} from "../model/customer.model";
import {CustomersService} from "../services/customerService/customers.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {
  newCustomerFormGroup! : FormGroup;
  constructor(private fb :FormBuilder,
              private customerService:CustomersService,
              private  router:Router) { }

  ngOnInit(): void {
    this.newCustomerFormGroup=this.fb.group(
      {
        nom : this.fb.control(null, [Validators.required, Validators.minLength(2)]),
        email : this.fb.control(null, [Validators.required,Validators.email]),
        dateNaissance : this.fb.control(null, [Validators.required])
      }
    )
  }

  handleSaveCustomer() {
    let customer:Customer=this.newCustomerFormGroup.value;
    this.customerService.createCustomer(customer).subscribe({
      next : data=>{
        alert("Customer has been successfully saved!");
        //this.newCustomerFormGroup.reset();
        this.router.navigateByUrl("/customers");
      }, error : err =>{
        console.log(err);
      }
    })
  }
}
