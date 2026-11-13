package org.skypro.skyshop.model.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException e) {
        ShopError m = new ShopError("NO_PRODUCT", "Такого товара нет");
        return new ResponseEntity<>(m, HttpStatusCode.valueOf(404));
    }
}
