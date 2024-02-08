package com.abalmas.GlovoDeliveryAPI;

import com.abalmas.GlovoDeliveryAPI.Converter.OrderConverter;
import com.abalmas.GlovoDeliveryAPI.Converter.ProductConverter;
import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import com.abalmas.GlovoDeliveryAPI.Entity.OrderEntity;
import com.abalmas.GlovoDeliveryAPI.Entity.ProductEntity;
import com.abalmas.GlovoDeliveryAPI.Repository.OrderRepository;
import com.abalmas.GlovoDeliveryAPI.Repository.ProductRepository;
import com.abalmas.GlovoDeliveryAPI.Service.OrderServiceDataBaseImpl;
import com.abalmas.GlovoDeliveryAPI.Utils.Exception.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceDataBaseImplTest {
    @InjectMocks
    private OrderServiceDataBaseImpl orderService;
    @Mock
    public OrderRepository orderRepository;
    @Mock
    public ProductRepository productRepository;
    @Mock
    public OrderConverter orderConverter;
    @Mock
    public ProductConverter productConverter;

    @Test
    void findAllTest() {
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(new OrderEntity());
        orders.add(new OrderEntity());
        List<OrderDTO> expected = orders.stream()
                .map(orderConverter::toOrderDTO)
                .collect(Collectors.toList());

        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDTO> result = orderService.findAll();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void findByIdTest() {
        OrderEntity input = new OrderEntity().builder().id(1).customerName("Petro").totalPrice(0).additionDate(LocalDate.now()).build();
        OrderDTO expected = new OrderDTO().builder().customerName("Petro").totalPrice(0).additionDate(LocalDate.now()).build();

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(input));
        when(orderConverter.toOrderDTO(input)).thenReturn(expected);

        OrderDTO result = orderService.findById(anyInt());

        assertThat(expected).isEqualTo(result);
    }

    @Test
    void findByIdNotFoundExceptionTest() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.findById(anyInt()));
    }


    @Test
    void addTest() {
        OrderDTO orderDTO = new OrderDTO();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProducts(new ArrayList<>());
        OrderDTO expected = new OrderDTO();

        when(orderConverter.toOrderEntity(orderDTO)).thenReturn(orderEntity);
        when(orderConverter.toOrderDTO(orderEntity)).thenReturn(expected);
        when(orderRepository.save(eq(orderEntity))).thenReturn(orderEntity);

        OrderDTO result = orderService.add(orderDTO);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateTest() {
        OrderDTO orderDTO = new OrderDTO();
        OrderEntity existOrderEntity = new OrderEntity();
        OrderEntity updOrderEntity = new OrderEntity();
        OrderDTO expected = new OrderDTO();

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(existOrderEntity));
        when(orderRepository.save(eq(existOrderEntity))).thenReturn(updOrderEntity);
        when(orderConverter.toOrderDTO(updOrderEntity)).thenReturn(expected);
        OrderDTO result = orderService.update(anyInt(), orderDTO);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateOrderNotFoundTest() {
        OrderDTO orderDTO = new OrderDTO();

        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        OrderDTO result = orderService.update(anyInt(), orderDTO);

        assertThat(result).isNull();
    }


    @Test
    void addProductTest() {
        ProductDTO productDTO = new ProductDTO();
        OrderEntity existingOrderEntity = new OrderEntity();
        existingOrderEntity.setProducts(new ArrayList<>());
        ProductEntity productEntity = new ProductEntity();
        OrderEntity updatedOrderEntity = new OrderEntity();
        updatedOrderEntity.setProducts(List.of(productEntity));
        OrderDTO expected = new OrderDTO();
        expected.setProducts(Collections.singletonList(productDTO));

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(existingOrderEntity));
        when(productConverter.toProductEntity(productDTO)).thenReturn(productEntity);
        when(orderRepository.save(eq(updatedOrderEntity))).thenReturn(updatedOrderEntity);
        when(orderConverter.toOrderDTO(updatedOrderEntity)).thenReturn(expected);
        Optional<OrderDTO> result = orderService.addProduct(anyInt(), productDTO);

        assertThat(result).isPresent();
        assertThat(result).isEqualTo(Optional.of(expected));

    }

    @Test
    void deleteProductTest() {
        int numberOfInvocationsMethod = 1;
        int id = 1;
        int idProductFirst = 1;
        int idProductSecond = 2;
        ProductEntity productEntity1 = new ProductEntity().builder().id(idProductFirst).build();
        ProductEntity productEntity2 = new ProductEntity().builder().id(idProductSecond).build();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProducts(new ArrayList<>(Arrays.asList(productEntity1, productEntity2)));

        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));
        orderService.deleteProduct(id, idProductFirst);

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
