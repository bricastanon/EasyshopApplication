package org.yearup.data;

import org.springframework.stereotype.Component;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShoppingCartDaoImp implements ShoppingCartDao {

    private final Map<Integer, ShoppingCart> shoppingCarts = new HashMap<>();

    @Override
    public ShoppingCart getByUserId(int userId) {
        return shoppingCarts.getOrDefault(userId, new ShoppingCart());
    }

    @Override
    public void addProductToCart(int userId, int productId) {
        ShoppingCart cart = shoppingCarts.getOrDefault(userId, new ShoppingCart());
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProductId(productId);  // Ensure this is correctly set
        item.setQuantity(1);
        cart.add(item);
        shoppingCarts.put(userId, cart);
    }

    @Override
    public void updateProductInCart(int userId, int productId, int quantity) {
        ShoppingCart cart = shoppingCarts.getOrDefault(userId, new ShoppingCart());
        cart.update(productId, quantity);
        shoppingCarts.put(userId, cart);
    }

    @Override
    public void clearCart(int userId) {
        shoppingCarts.remove(userId);
    }
}
