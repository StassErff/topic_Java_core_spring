package org.skypro.skyshop.model.basket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(ArrayList<BasketItem> basketItems) {
        this.basketItems = basketItems != null ? Collections.unmodifiableList(new ArrayList<>(basketItems)) : Collections.emptyList();
        this.total = this.basketItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getAmountProduct())
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }
}
