package com.example.services.customer.cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.example.dto.AddProductInCartDto;
import com.example.dto.CartItemsDto;
import com.example.dto.OrderDto;
import com.example.dto.PlaceOrderDto;
import com.example.entity.CartItems;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.enums.OrderStatus;
import com.example.repository.CartItemsRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService{

    private final SecurityFilterChain securityFilterChain;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private ProductRepository productRepository;

    CartServiceImpl(SecurityFilterChain securityFilterChain) {
        this.securityFilterChain = securityFilterChain;
    }
	


	
	
	

    
    
    

    
    
    
//    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
//        // 1️⃣ Find existing pending order
//        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
//            addProductInCartDto.getUserId(),
//            OrderStatus.Pending
//        );
//
//        // 2️⃣ If none exists, create a new pending order
//        if (activeOrder == null) {
//            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
//            if (optionalUser.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//            }
//
//            activeOrder = new Order();
//            activeOrder.setAmount(0L);
//            activeOrder.setTotalAmount(0L);
//            activeOrder.setDiscount(0L);
//            activeOrder.setOrderStatus(OrderStatus.Pending);
//            activeOrder.setUser(optionalUser.get());
//            activeOrder.setCartItems(new ArrayList<>()); // 💡 Always initialize list
//            activeOrder = orderRepository.save(activeOrder);
//        }
//
//        // 3️⃣ Check if the product is already in the cart
//        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
//            addProductInCartDto.getProductId(),
//            activeOrder.getId(),
//            addProductInCartDto.getUserId()
//        );
//
//        if (optionalCartItems.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already in cart.");
//        } else {
//            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
//            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
//
//            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
//                CartItems cart = new CartItems();
//                cart.setProduct(optionalProduct.get());
//                cart.setPrice(optionalProduct.get().getPrice());
//                cart.setQuantity(1L);
//                cart.setUser(optionalUser.get());
//                cart.setOrder(activeOrder);
//
//                cartItemsRepository.save(cart);
//
//                // ✅ Recalculate total
//                List<CartItems> allCartItems = cartItemsRepository.findByOrderId(activeOrder.getId());
//                long total = allCartItems.stream()
//                    .mapToLong(c -> c.getPrice() * c.getQuantity())
//                    .sum();
//
//                activeOrder.setTotalAmount(total);
//                activeOrder.setAmount(total);
//
//                orderRepository.save(activeOrder);
//
//                CartItemsDto dto = toDto(cart);
//                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found.");
//            }
//        }
//    }
    
    
    
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        // 1️⃣ Find existing pending order
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
            addProductInCartDto.getUserId(),
            OrderStatus.Pending
        );

        // 2️⃣ If none exists, create a new pending order
        if (activeOrder == null) {
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            activeOrder = new Order();
            activeOrder.setAmount(0L);
            activeOrder.setTotalAmount(0L);
            activeOrder.setDiscount(0L);
            activeOrder.setOrderStatus(OrderStatus.Pending);
            activeOrder.setUser(optionalUser.get());
            activeOrder = orderRepository.save(activeOrder);
        }

        // 3️⃣ Check if the product is already in the cart
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
            addProductInCartDto.getProductId(),
            activeOrder.getId(),
            addProductInCartDto.getUserId()
        );

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already in cart.");
        }

        // 4️⃣ Fetch product and user
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

        if (optionalProduct.isEmpty() || optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found.");
        }

        // 5️⃣ Create new cart item
        CartItems cart = new CartItems();
        cart.setProduct(optionalProduct.get());
        cart.setPrice(optionalProduct.get().getPrice());
        cart.setQuantity(1L);
        cart.setUser(optionalUser.get());
        cart.setOrder(activeOrder);

        // 6️⃣ Save cart item
        CartItems savedCart = cartItemsRepository.save(cart);

        // 7️⃣ Recalculate total amount from all cart items (INCLUDING the one we just added)
        List<CartItems> allCartItems = cartItemsRepository.findByOrderId(activeOrder.getId());
        Long newTotalAmount = allCartItems.stream()
            .mapToLong(CartItems::getPrice)
            .sum();

        activeOrder.setTotalAmount(newTotalAmount);
        activeOrder.setAmount(newTotalAmount);

        // 8️⃣ Ensure cartItems list is initialized
        if (activeOrder.getCartItems() == null) {
            activeOrder.setCartItems(new ArrayList<>());
        }
        activeOrder.getCartItems().add(savedCart);

        // 9️⃣ Save updated order
        orderRepository.save(activeOrder);

        // 🔟 Return response
        CartItemsDto dto = toDto(savedCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }




	
	
	
	private CartItemsDto toDto(CartItems cart) {
        CartItemsDto dto = new CartItemsDto();
        dto.setId(cart.getId());
        dto.setPrice(cart.getPrice());
        dto.setQuantity(cart.getQuantity());
        dto.setProductId(cart.getProduct().getId());
        dto.setProductName(cart.getProduct().getName());
        return dto;
    }
	
	public OrderDto getCartByUserId(Long userId)
	{
		
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
		
		
		if (activeOrder == null) {
	        // ✅ Return empty DTO instead of null
	        OrderDto empty = new OrderDto();
	        empty.setCartItems(List.of()); // empty list
	        empty.setAmount(0L);
	        empty.setTotalAmount(0L);
	        empty.setDiscount(0L);
	        empty.setOrderStatus(OrderStatus.Pending);
	        return empty;
	    }
		
		List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
		
		OrderDto orderDto = new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		
		
		
		orderDto.setCartItems(cartItemsDtoList);;
		
		return orderDto;
	}
	
	
	

	
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto) {
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
	        addProductInCartDto.getUserId(),
	        OrderStatus.Pending
	    );

	    if (activeOrder == null) {
	        throw new RuntimeException("No active order found for the user.");
	    }

	    Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
	    Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
	        addProductInCartDto.getProductId(),
	        activeOrder.getId(),
	        addProductInCartDto.getUserId()
	    );

	    if (optionalProduct.isPresent() && optionalCartItems.isPresent()) {
	        CartItems cartItem = optionalCartItems.get();
	        
	        // Increase quantity
	        cartItem.setQuantity(cartItem.getQuantity() + 1);
	        cartItemsRepository.save(cartItem);

	        // 🔄 Recalculate totals instead of incrementing manually
	        recalculateOrderTotals(activeOrder);
	        orderRepository.save(activeOrder);

	        return activeOrder.getOrderDto();
	    }

	    return null;
	}

	
	
//	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto) {
//		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
//				addProductInCartDto.getUserId(),
//				OrderStatus.Pending
//				);
//		
//		if (activeOrder == null) {
//			throw new RuntimeException("No active order found for the user.");
//		}
//		
//		
//		Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
//		
//		Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());
//		
//		if(optionalProduct.isPresent() && optionalCartItems.isPresent())
//		{
//			CartItems cartItem = optionalCartItems.get();
//			Product product = optionalProduct.get();
//			
//			if (cartItem.getQuantity() <= 1) {
//	            // Remove the cart item completely
//	            cartItemsRepository.delete(cartItem);
//	        } else {
//	            // Decrease quantity
//	            cartItem.setQuantity(cartItem.getQuantity() - 1);
//	            cartItemsRepository.save(cartItem);
//	        }
//			
//			activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
//			activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
//			
//			
//			
//			cartItemsRepository.save(cartItem);
//			orderRepository.save(activeOrder);
//			return activeOrder.getOrderDto();
//			
//		}
//		return null;
//	}
	
	
	public OrderDto decreaseProductQuantity(AddProductInCartDto dto) {
	    // 1️⃣ Find the active order
	    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
	            dto.getUserId(),
	            OrderStatus.Pending
	    );

	    if (activeOrder == null) {
	        throw new RuntimeException("No active order found for the user.");
	    }

	    // 2️⃣ Find the cart item
	    Optional<CartItems> optionalCartItem = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
	            dto.getProductId(),
	            activeOrder.getId(),
	            dto.getUserId()
	    );

	    if (optionalCartItem.isEmpty()) {
	        throw new RuntimeException("Product not found in cart.");
	    }

	    CartItems cartItem = optionalCartItem.get();

	    if (cartItem.getQuantity() <= 1) {
	        // Quantity is 1, remove the item
	        cartItemsRepository.delete(cartItem);
	    } else {
	        // Quantity > 1, decrement
	        cartItem.setQuantity(cartItem.getQuantity() - 1);
	        cartItemsRepository.save(cartItem);
	    }

	    // 🔄 Recalculate totals
	    List<CartItems> remainingItems = cartItemsRepository.findByOrderId(activeOrder.getId());

	    if (remainingItems.isEmpty()) {
	        activeOrder.setTotalAmount(0L);
	        activeOrder.setAmount(0L);
	    } else {
	        long total = remainingItems.stream()
	                .mapToLong(item -> item.getPrice() * item.getQuantity())
	                .sum();
	        activeOrder.setTotalAmount(total);
	        activeOrder.setAmount(total);
	    }

	    orderRepository.save(activeOrder);

	    return activeOrder.getOrderDto();
	}



	
	
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
		
		 if (placeOrderDto.getUserId() == null) {
		        throw new IllegalArgumentException("User ID cannot be null");
		    }
		
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(
				placeOrderDto.getUserId(),
				OrderStatus.Pending
				);
		
		Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
		
		if(optionalUser.isPresent())
		{
			activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
			activeOrder.setAddress(placeOrderDto.getAddress());
			activeOrder.setDate(new Date());
			activeOrder.setOrderStatus(OrderStatus.Placed);
			activeOrder.setTrackingId(UUID.randomUUID());
			
			orderRepository.save(activeOrder);
			
			
			Order order = new Order();
			order.setAmount(0L);
			order.setTotalAmount(0L);
			order.setDiscount(0L);
			order.setUser(optionalUser.get());
			order.setOrderStatus(OrderStatus.Pending);
			orderRepository.save(order);
			

			return activeOrder.getOrderDto();
		}
		return null;
	}
	
	private void recalculateOrderTotals(Order order) {
	    List<CartItems> items = cartItemsRepository.findByOrderId(order.getId());

	    if (items.isEmpty()) {
	        order.setAmount(0L);
	        order.setTotalAmount(0L);
	    } else {
	        long total = items.stream()
	            .mapToLong(i -> i.getPrice() * i.getQuantity())
	            .sum();
	        order.setAmount(total);
	        order.setTotalAmount(total);
	    }
	}
	
	
	public List<OrderDto> getMyPlacedOrders(Long userId){
		return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)).stream().map(Order :: getOrderDto).collect(Collectors.toList());
	}
	
	
	
	public OrderDto searchOrderByTrackingId(UUID trackingId) {
		Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);
		if(optionalOrder.isPresent())
		{
			return optionalOrder.get().getOrderDto();
		}
		return null;
	}



}



