package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void addToBasket(UUID id) {
        basket.computeIfAbsent(id, k -> basket.merge(id,1, Integer::sum));
        System.out.println("Продукт добавлен в вашу корзину");
    }

    public Map<UUID, Integer> printBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
