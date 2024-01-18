package com.abalmas.glovodeliveryapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abalmas.glovodeliveryapi.dto.OrderDto;
import com.abalmas.glovodeliveryapi.dto.ProductDto;
import com.abalmas.glovodeliveryapi.entity.OrderEntity;
import com.abalmas.glovodeliveryapi.entity.ProductEntity;
import com.abalmas.glovodeliveryapi.mapper.OrderMapper;
import com.abalmas.glovodeliveryapi.mapper.ProductMapper;
import com.abalmas.glovodeliveryapi.repository.OrderRepository;
import com.abalmas.glovodeliveryapi.repository.ProductRepository;
import com.abalmas.glovodeliveryapi.service.OrderServiceDataBaseImpl;
import com.abalmas.glovodeliveryapi.utils.exception.OrderNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * The type Order service database impl test.
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceDataBaseImplTest {

  @InjectMocks
  private OrderServiceDataBaseImpl orderService;
  @Mock
  public OrderRepository orderRepository;
  @Mock
  public ProductRepository productRepository;
  @Mock
  public OrderMapper orderMapper;
  @Mock
  public ProductMapper productMapper;

  @Test
  void findAllTest() {
    List<OrderEntity> orders = new ArrayList<>();
    orders.add(new OrderEntity());
    orders.add(new OrderEntity());
    List<OrderDto> expected = orders.stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());

    when(orderRepository.findAll()).thenReturn(orders);

    List<OrderDto> result = orderService.findAll();

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void findByIdTest() {
    OrderEntity input = new OrderEntity().builder().id(1).customerName("Petro").totalPrice(0)
        .additionDate(LocalDate.now()).build();
    OrderDto expected = new OrderDto().builder().customerName("Petro").totalPrice(0)
        .additionDate(LocalDate.now()).build();

    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(input));
    when(orderMapper.toDto(input)).thenReturn(expected);

    OrderDto result = orderService.findById(anyInt());

    assertThat(expected).isEqualTo(result);
  }

  @Test
  void findByIdNotFoundExceptionTest() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(OrderNotFoundException.class, () -> orderService.findById(anyInt()));
  }


  @Test
  void addTest() {
    OrderDto orderDto = new OrderDto();
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setProducts(new ArrayList<>());
    OrderDto expected = new OrderDto();

    when(orderMapper.toEntity(orderDto)).thenReturn(orderEntity);
    when(orderMapper.toDto(orderEntity)).thenReturn(expected);
    when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);

    OrderDto result = orderService.add(orderDto);

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void updateTest() {
    OrderDto orderDto = new OrderDto();
    OrderEntity existOrderEntity = new OrderEntity();
    OrderEntity updOrderEntity = new OrderEntity();
    OrderDto expected = new OrderDto();

    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(existOrderEntity));
    when(orderRepository.save(eq(existOrderEntity))).thenReturn(updOrderEntity);
    when(orderMapper.toDto(updOrderEntity)).thenReturn(expected);
    OrderDto result = orderService.update(anyInt(), orderDto);

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void updateOrderNotFoundTest() {
    OrderDto orderDto = new OrderDto();

    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

    OrderDto result = orderService.update(anyInt(), orderDto);

    assertThat(result).isNull();
  }


  @Test
  void addProductTest() {
    ProductDto productDto = new ProductDto();
    OrderEntity existingOrderEntity = new OrderEntity();
    existingOrderEntity.setProducts(new ArrayList<>());
    ProductEntity productEntity = new ProductEntity();
    OrderEntity updatedOrderEntity = new OrderEntity();
    updatedOrderEntity.setProducts(List.of(productEntity));
    OrderDto expected = new OrderDto();
    expected.setProducts(Collections.singletonList(productDto));

    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(existingOrderEntity));
    when(productMapper.toEntity(productDto)).thenReturn(productEntity);
    when(orderRepository.save(eq(updatedOrderEntity))).thenReturn(updatedOrderEntity);
    when(orderMapper.toDto(updatedOrderEntity)).thenReturn(expected);
    Optional<OrderDto> result = orderService.addProduct(anyInt(), productDto);

    assertThat(result).isPresent();
    assertThat(result).isEqualTo(Optional.of(expected));

  }

  @Test
  void deleteProductTest() {
    int id = 1;
    int idProductFirst = 1;
    int idProductSecond = 2;
    ProductEntity productEntity1 = new ProductEntity().builder().id(idProductFirst).build();
    ProductEntity productEntity2 = new ProductEntity().builder().id(idProductSecond).build();
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setProducts(new ArrayList<>(Arrays.asList(productEntity1, productEntity2)));

    when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));
    orderService.deleteProduct(id, idProductFirst);
    int numberOfInvocationsMethod = 1;
    verify(orderRepository, Mockito.times(numberOfInvocationsMethod)).findById(id);
    verify(orderRepository, Mockito.times(numberOfInvocationsMethod)).save(orderEntity);
    verify(productRepository, Mockito.times(numberOfInvocationsMethod)).deleteById(idProductFirst);
    assertThat(orderEntity.getProducts()).containsExactlyInAnyOrder(productEntity2);

  }


  @Test
  void deleteTest() {
    int numberOfInvocationsMethod = 1;

    orderService.delete(anyInt());

    verify(orderRepository).deleteById(anyInt());
    verify(orderRepository, Mockito.times(numberOfInvocationsMethod)).deleteById(anyInt());
  }

}
