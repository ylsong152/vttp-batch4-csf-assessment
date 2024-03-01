import { Injectable } from '@angular/core';
import { LineItem } from './models';
import { isNgTemplate } from '@angular/compiler';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  constructor() { }

  cart: LineItem[] = []

  emitCartChange: Subject<any> = new Subject<any>();
  changeEmitted$ = this.emitCartChange.asObservable();

  getCart() {
    return this.cart
  }

  addItem(newItem: LineItem) {
    this.cart.push(newItem)
    this.emitCartChange.next(this.cart)
  }


}
