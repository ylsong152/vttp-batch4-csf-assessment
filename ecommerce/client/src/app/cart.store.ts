
// TODO Task 2
// Use the following class to implement your store

import { Injectable } from "@angular/core";
import { ComponentStore } from "@ngrx/component-store";
import { Cart, LineItem } from "./models";

@Injectable()
export class CartStore extends ComponentStore<Cart>{
    constructor() {
        super({ lineItems: [] })
    }

    // readonly getCart$ = this.select<LineItem[]>(
    //     (slice: Cart) => {
    //         console.log(slice.lineItems)
    //       return slice.lineItems
    //     }
    // )

    readonly getCart$ = this.select((state) => state.lineItems);

    readonly addItemToCart = this.updater<LineItem>(
        (slice: Cart, lineItem: LineItem) => {
            return {
                lineItems: [...slice.lineItems, lineItem]
            }
        }
    )

    readonly addItem = this.updater((state, newItem: LineItem) => ({
        lineItems: [...state.lineItems, newItem],
      }));
}
