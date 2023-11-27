package com.moulamanager.api.services.cartItem;

import com.moulamanager.api.dto.cartItem.result.CartItemResultDTO;
import com.moulamanager.api.dto.cartItem.request.UpdateCartItemQuantityDTO;

import java.util.List;

public interface ICartItemService {

    List<CartItemResultDTO> findAll();

    CartItemResultDTO findById(long id);

    CartItemResultDTO findByCartId(long cartId);

    CartItemResultDTO findByProductId(long productId);

    CartItemResultDTO findByCartIdAndProductId(long cartId, long productId);

    CartItemResultDTO addProductToCart(long productId, String token);

    CartItemResultDTO updateProductQuantity(long productId, UpdateCartItemQuantityDTO quantity, String token);

    void removeProductFromCart(long productId, String token);

    void deleteAllProductsFromCart(String token);

}
