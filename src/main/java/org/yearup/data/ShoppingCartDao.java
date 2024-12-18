package org.yearup.data;

import org.springframework.stereotype.Repository;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface ShoppingCartDao {

    Map<Integer, ShoppingCart> shoppingCarts = new HashMap<>();

    default ShoppingCart getByUserId(int userId) {
        return shoppingCarts.getOrDefault(userId, new ShoppingCart());
    }

    default void addProductToCart(int userId, int productId) {
        ShoppingCart cart = shoppingCarts.getOrDefault(userId, new ShoppingCart());
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProductId(productId); // This line assumes you have a way to set the product using product ID
        item.setQuantity(1);
        cart.add(item);
        shoppingCarts.put(userId, cart);
    }

    default void updateProductInCart(int userId, int productId, int quantity) {
        ShoppingCart cart = shoppingCarts.getOrDefault(userId, new ShoppingCart());
        cart.update(productId, quantity);
        shoppingCarts.put(userId, cart);
    }

    default void clearCart(int userId) {
        shoppingCarts.remove(userId);
    }
}



    /*
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    void addProductToCart(int userId, int productId);
    void updateProductInCart(int userId, int productId, int quantity);
    void clearCart(int userId);
} */
