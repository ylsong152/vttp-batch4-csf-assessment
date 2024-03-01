import { Component, OnInit, inject } from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import { CartService } from './cart.service';
import { Cart } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  // NOTE: you are free to modify this component
  
  constructor(cartService: CartService) {
    this.cartService.changeEmitted$.subscribe(cart => {
      this.currentCart = cart
      if (cart.length == 1) {
        this.itemCount = 1
      } else {
        this.itemCount += 1
      }
    })
  }
  
  private router = inject(Router)
  private cartService = inject(CartService)

  itemCount!: number;
  currentCart!: Cart

  ngOnInit(): void {
  }

  checkout(): void {
    if (this.itemCount == undefined) {
      alert("No items in cart, cannot checkout")
    } else {
      this.router.navigate([ '/checkout' ])
    }
  }
}
