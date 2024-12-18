package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart
{
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, ShoppingCartItem> items) {
        this.items = items;
    }

    public boolean contains(int productId) {
        return items.containsKey(productId);
    }

    public void add(ShoppingCartItem item) {
        if (contains(item.getProductId())) {
            ShoppingCartItem existingItem = get(item.getProductId());
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // Add new item to the cart
             items.put(item.getProductId(), item);
        }
    }
    public void update(int productId, int quantity) {
        if (contains(productId)) {
            ShoppingCartItem existingItem = get(productId);
            existingItem.setQuantity(quantity);
        }
    }
    public void remove(int productId) {
        items.remove(productId);
    }

    public ShoppingCartItem get(int productId) {
        return items.get(productId);
    }
    public void clear() {
        items.clear();
    }

    public BigDecimal getTotal() {
        return items.values()
                .stream()
                .map(ShoppingCartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
