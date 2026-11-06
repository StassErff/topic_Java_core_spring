package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();

        addTestingData();
    }

    public Collection<Product> allProduct() {
        return productMap.values();
    }

    public Collection<Article> allArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchable() {
        Collection<Searchable> allSearchable = new ArrayList<>();

        allSearchable.addAll(productMap.values());
        allSearchable.addAll(articleMap.values());

        return allSearchable;
    }

    private void addTestingData() {
        UUID cookieId = UUID.randomUUID();
        SimpleProduct cookie = new SimpleProduct(cookieId, "печенье", 60);
        productMap.put(cookieId, cookie);

        UUID wafflesId = UUID.randomUUID();
        SimpleProduct waffles = new SimpleProduct(wafflesId, "вафли", 70);
        productMap.put(wafflesId, waffles);

        UUID marshmallowId = UUID.randomUUID();
        DiscountedProduct marshmallow = new DiscountedProduct(marshmallowId, "зефир", 105, 15);
        productMap.put(marshmallowId, marshmallow);

        UUID toffeesId = UUID.randomUUID();
        DiscountedProduct toffees = new DiscountedProduct(toffeesId, "ириски", 50, 40);
        productMap.put(toffeesId, toffees);

        UUID lollipopId = UUID.randomUUID();
        SimpleProduct lollipop = new SimpleProduct(lollipopId, "леденец", 15);
        productMap.put(lollipopId, lollipop);

        UUID cakeId = UUID.randomUUID();
        FixPriceProduct cake = new FixPriceProduct(cakeId, "пироженое");
        productMap.put(cakeId, cake);

        UUID artCookieId = UUID.randomUUID();
        Article articleCookie = new Article(artCookieId, "Cookie", "Crispy oatmeal cookies go perfectly with morning coffee");
        articleMap.put(artCookieId, articleCookie);

        UUID artWafflesId = UUID.randomUUID();
        Article articleWaffles = new Article(artWafflesId, "Waffles", "It is customary to drizzle hot Viennese waffles with sweet maple syrup");
        articleMap.put(artWafflesId, articleWaffles);

        UUID artToffeesId = UUID.randomUUID();
        Article articleToffees = new Article(artToffeesId, "Tofees", "Chewy creamy toffees stick very strongly to one's teeth");
        articleMap.put(artToffeesId, articleToffees);

        UUID artCakeId = UUID.randomUUID();
        Article articleCake = new Article(artCakeId, "Sponge Cake", "The delicate sponge cakes were decorated with fresh raspberries");
        articleMap.put(artCakeId, articleCake);

        UUID artCakesId = UUID.randomUUID();
        Article articleCakes = new Article(artCakesId, "Cake", "Cakes are small masterpieces of the confectioner’s art");
        articleMap.put(artCakesId, articleCakes);
    }
}
