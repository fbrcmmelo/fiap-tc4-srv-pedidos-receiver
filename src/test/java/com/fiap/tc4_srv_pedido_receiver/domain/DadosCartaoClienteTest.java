package com.fiap.tc4_srv_pedido_receiver.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DadosCartaoClienteTest {

    @Test
    void constructor_withValidNumero_shouldCreateRecord() {
        // Arrange
        String validNumero = "1234567890123456";

        // Act
        DadosCartaoCliente dadosCartaoCliente = new DadosCartaoCliente(validNumero);

        // Assert
        assertThat(dadosCartaoCliente).isNotNull();
        assertThat(dadosCartaoCliente.numero()).isEqualTo(validNumero);
    }

    @Test
    void constructor_withNullNumero_shouldThrowIllegalArgumentException() {
        // Arrange
        String nullNumero = null;

        // Act & Assert
        assertThatThrownBy(() -> new DadosCartaoCliente(nullNumero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Número do cartão inválido");
    }

    @Test
    void constructor_withEmptyNumero_shouldThrowIllegalArgumentException() {
        // Arrange
        String emptyNumero = "";

        // Act & Assert
        assertThatThrownBy(() -> new DadosCartaoCliente(emptyNumero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Número do cartão inválido");
    }

    @Test
    void constructor_withBlankNumero_shouldThrowIllegalArgumentException() {
        // Arrange
        String blankNumero = "   ";

        // Act & Assert
        assertThatThrownBy(() -> new DadosCartaoCliente(blankNumero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Número do cartão inválido");
    }
}
