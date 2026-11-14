package org.skypro.skyshop.model.servise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void addNonExistentProductThrowsException() {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addToIdBasket(invalidId));
    }

    @Test
    public void addExistingProductCallsAddProductOnBasket() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Хлеб", 50);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addToIdBasket(productId);

        verify(productBasket).addToBasket(productId);
    }

    @Test
    public void getUserBasketReturnsEmptyBasketWhenProductBasketIsEmpty() {
        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertThat(result.getBasketItems()).isEmpty();
        assertThat(result.getTotal()).isZero();
    }

    @Test
    public void getUserBasketReturnsPopulatedBasketWhenProductsExist() {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Молоко", 100);

        when(productBasket.getBasket()).thenReturn(Map.of(productId, 2));
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        assertThat(result.getBasketItems()).hasSize(1);
        assertThat(result.getBasketItems().get(0).getProduct()).isEqualTo(product);
        assertThat(result.getBasketItems().get(0).getAmountProduct()).isEqualTo(2);
        assertThat(result.getTotal()).isEqualTo(200);
    }
}
