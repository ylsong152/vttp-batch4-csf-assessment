import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CartService } from '../cart.service';
import { Cart, LineItem, Order } from '../models';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit{

  // TODO Task 3
  checkoutForm!: FormGroup
  private formBuilder = inject(FormBuilder)
  private cartService = inject(CartService)
  private productService = inject(ProductService)

  currentCart!: LineItem[]
  cartTotal: number = 0;
  cart: Cart = {
    lineItems: this.currentCart
  };

  ngOnInit(): void {
    this.checkoutForm = this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required]),
      address: this.formBuilder.control('', [Validators.required, Validators.min(3)]),
      priority: this.formBuilder.control(false),
      comments: this.formBuilder.control('')
    }) 
    this.currentCart = this.cartService.getCart()

    for (let i = 0; i < this.currentCart.length; i++) {
      this.cartTotal += (this.currentCart[i].quantity * this.currentCart[i].price)
    }

    this.cart = {
      lineItems: this.currentCart
    };

    // this.checkoutForm.valueChanges.subscribe((values) => console.log(values));
  }

  onSubmit() {
    let formValue = this.checkoutForm.value
    
    let order: Order = {
      name: formValue.name,
      address: formValue.address,
      priority: formValue.priority,
      comments: formValue.comments,
      cart: this.cart
     }

    this.productService.checkout(order)
  }
}
