package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService service;

    @Autowired
    public BasketService(ProductBasket basket, StorageService service) {
        this.basket = basket;
        this.service = service;
    }

    public void addToIdBasket(UUID id) {
        service.getProductById(id).orElseThrow(() -> new NoSuchProductException("Товар с ID " + id + " не найден"));
        basket.addToBasket(id);
    }

    public UserBasket getUserBasket() {
        ArrayList<BasketItem> items = basket.getBasket().entrySet().stream()
                .map(m -> new BasketItem(service.getProductById(m.getKey())
                        .orElseThrow(() -> new NoSuchProductException("Product not found for ID: " + m.getKey())), m.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new UserBasket(items);
    }
}
