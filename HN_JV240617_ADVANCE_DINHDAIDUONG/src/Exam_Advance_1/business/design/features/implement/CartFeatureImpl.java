package Exam_Advance_1.business.design.features.implement;

import Exam_Advance_1.business.design.entity.CartItem;
import Exam_Advance_1.business.design.features.ICartFeature;

import java.util.ArrayList;
import java.util.List;

public class CartFeatureImpl implements ICartFeature {
    public static List<CartItem> cartItems = new ArrayList<>();
    @Override
    public void save(CartItem cartItem) {
        int index = findIndexById(cartItem.getCartItemId());
        if (index == -1) {
            cartItems.add(cartItem);
        }else {
            cartItems.set(index, cartItem);
        }
    }

    @Override
    public void remove(Integer id) {
        remove(findIndexById(id));
    }

    @Override
    public int findIndexById(Integer id) {
        return cartItems.stream().map(CartItem::getCartItemId).toList().indexOf(id);
    }

    @Override
    public List<CartItem> getAll() {
        return cartItems;
    }
}
