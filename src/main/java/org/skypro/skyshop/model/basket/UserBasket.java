package org.skypro.skyshop.model.basket;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

public class UserBasket {
    private final ArrayList<BasketItem> basketItems;
    private int total;

    public UserBasket(ArrayList<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public ArrayList<BasketItem> getBasketItems() {
        return basketItems;
    }

    @GetMapping
    public int getTotal(int amount) {
        this.total += total * amount;
        // Надо ли написать sout "Стоимость вашей корзины"?
        return this.total;
    }
}
