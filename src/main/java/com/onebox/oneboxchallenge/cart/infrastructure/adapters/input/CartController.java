package com.onebox.oneboxchallenge.cart.infrastructure.adapters.input;

import com.onebox.oneboxchallenge.cart.application.ports.input.CreateCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.DeleteCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.GetCartUseCase;
import com.onebox.oneboxchallenge.cart.application.ports.input.UpdateCartUseCase;
import com.onebox.oneboxchallenge.cart.domain.model.Cart;
import com.onebox.oneboxchallenge.product.domain.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CartsApi;
import org.openapitools.model.CartDTO;
import org.openapitools.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CartController implements CartsApi {

    private final CreateCartUseCase createCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final UpdateCartUseCase updateCartUseCase;
    private final DeleteCartUseCase deleteCartUseCase;

    @Override
    public ResponseEntity<CartDTO> createCart(@Valid @RequestBody CartDTO cartDTO) {
        Cart cart = mapToDomain(cartDTO);
        Cart createdCart = createCartUseCase.createCart(cart);
        CartDTO responseDTO = mapToDto(createdCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public ResponseEntity<CartDTO> getCartById(Long cartId) {
        Cart cart = getCartUseCase.getCartById(cartId);
        return ResponseEntity.ok(mapToDto(cart));
    }

    @Override
    public ResponseEntity<CartDTO> updateCart(Long id, CartDTO cartDTO) {
        Cart cart = mapToDomain(cartDTO);
        Cart updatedCart = updateCartUseCase.updateCart(id, cart);
        return ResponseEntity.ok(mapToDto(updatedCart));
    }

    @Override
    public ResponseEntity<Void> deleteCart(Long id) {
        deleteCartUseCase.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    private Cart mapToDomain(@Valid CartDTO cartDTO) {
        return new Cart(
                cartDTO.getId(),
                cartDTO.getProducts().stream()
                        .map(productDTO -> new Product(
                                productDTO.getId(),
                                productDTO.getDescription(),
                                productDTO.getAmount()))
                        .collect(Collectors.toList())
        );
    }

    private CartDTO mapToDto(Cart cartDomain) {
        return new CartDTO()
                .id(cartDomain.getId())
                .products(cartDomain.getProducts().stream()
                        .map(product -> new ProductDTO()
                                .id(product.getId())
                                .description(product.getDescription())
                                .amount(product.getAmount()))
                        .collect(Collectors.toList()));
    }
}
