package com.fiap.tc4_srv_pedido_receiver.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class PedidoTest {

    @Test
    void constructor_withValidParameters_shouldCreatePedido() {
        // Arrange
        String validClienteId = "client123";
        DadosCartaoCliente validDadosCartao =
                new DadosCartaoCliente("1234567890123456"); // Assuming DadosCartaoCliente is valid
        List<ProdutoPedido> validProdutoPedidos = new ArrayList<>();
        validProdutoPedidos.add(mock(ProdutoPedido.class)); // Add at least one item

        // Act
        Pedido pedido = new Pedido(validClienteId, validDadosCartao, validProdutoPedidos);

        // Assert
        assertThat(pedido).isNotNull();
        assertThat(pedido.getClienteId()).isEqualTo(validClienteId);
        assertThat(pedido.getDadosCartao()).isEqualTo(validDadosCartao);
        assertThat(pedido.getProdutoPedidos()).isEqualTo(validProdutoPedidos);
    }

    @Test
    void constructor_withNullClienteId_shouldThrowNullPointerException() {
        // Arrange
        String nullClienteId = null;
        DadosCartaoCliente validDadosCartao = new DadosCartaoCliente("1234567890123456");
        List<ProdutoPedido> validProdutoPedidos = new ArrayList<>();
        validProdutoPedidos.add(mock(ProdutoPedido.class));

        // Act & Assert
        assertThatThrownBy(() -> new Pedido(nullClienteId, validDadosCartao, validProdutoPedidos))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Id do cliente não pode ser nulo");
    }

    @Test
    void constructor_withNullDadosCartao_shouldThrowNullPointerException() {
        // Arrange
        String validClienteId = "client123";
        DadosCartaoCliente nullDadosCartao = null;
        List<ProdutoPedido> validProdutoPedidos = new ArrayList<>();
        validProdutoPedidos.add(mock(ProdutoPedido.class));

        // Act & Assert
        assertThatThrownBy(() -> new Pedido(validClienteId, nullDadosCartao, validProdutoPedidos))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Dados cartao do cliente não pode ser nulo");
    }

    @Test
    void constructor_withNullProdutoPedidos_shouldThrowIllegalArgumentException() {
        // Arrange
        String validClienteId = "client123";
        DadosCartaoCliente validDadosCartao = new DadosCartaoCliente("1234567890123456");
        List<ProdutoPedido> nullProdutoPedidos = null;

        // Act & Assert
        assertThatThrownBy(() -> new Pedido(validClienteId, validDadosCartao, nullProdutoPedidos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lista de produtos do pedido não pode estar vazia");
    }

    @Test
    void constructor_withEmptyProdutoPedidos_shouldThrowIllegalArgumentException() {
        // Arrange
        String validClienteId = "client123";
        DadosCartaoCliente validDadosCartao = new DadosCartaoCliente("1234567890123456");
        List<ProdutoPedido> emptyProdutoPedidos = new ArrayList<>();

        // Act & Assert
        assertThatThrownBy(() -> new Pedido(validClienteId, validDadosCartao, emptyProdutoPedidos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lista de produtos do pedido não pode estar vazia");
    }
}
