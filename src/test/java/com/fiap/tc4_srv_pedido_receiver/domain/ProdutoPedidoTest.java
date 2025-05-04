package com.fiap.tc4_srv_pedido_receiver.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProdutoPedidoTest {

    @Test
    void constructor_withValidParameters_shouldCreateRecord() {
        // Arrange
        String validSku = "SKU123";
        Long validQuantidade = 5L;

        // Act
        ProdutoPedido produtoPedido = new ProdutoPedido(validSku, validQuantidade);

        // Assert
        assertThat(produtoPedido).isNotNull();
        assertThat(produtoPedido.sku()).isEqualTo(validSku);
        assertThat(produtoPedido.quantidade()).isEqualTo(validQuantidade);
    }

    @Test
    void constructor_withNullSku_shouldThrowNullPointerException() {
        // Arrange
        String nullSku = null;
        Long validQuantidade = 5L;

        // Act & Assert
        assertThatThrownBy(() -> new ProdutoPedido(nullSku, validQuantidade))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Sku produto não pode ser nulo");
    }

    @Test
    void constructor_withNullQuantidade_shouldThrowIllegalArgumentException() {
        // Arrange
        String validSku = "SKU123";
        Long nullQuantidade = null;

        // Act & Assert
        assertThatThrownBy(() -> new ProdutoPedido(validSku, nullQuantidade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantidade de produto inválida");
    }

    @Test
    void constructor_withZeroQuantidade_shouldThrowIllegalArgumentException() {
        // Arrange
        String validSku = "SKU123";
        Long zeroQuantidade = 0L;

        // Act & Assert
        assertThatThrownBy(() -> new ProdutoPedido(validSku, zeroQuantidade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantidade de produto inválida");
    }

    @Test
    void constructor_withNegativeQuantidade_shouldThrowIllegalArgumentException() {
        // Arrange
        String validSku = "SKU123";
        Long negativeQuantidade = -2L;

        // Act & Assert
        assertThatThrownBy(() -> new ProdutoPedido(validSku, negativeQuantidade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantidade de produto inválida");
    }
}
