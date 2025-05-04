package com.fiap.tc4_srv_pedido_receiver.config;

import com.fiap.tc4_srv_pedido_receiver.consts.ExchangeConstants;
import com.fiap.tc4_srv_pedido_receiver.consts.FilaConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMqConnectionConfigTest {

    @Mock
    private AmqpAdmin amqpAdmin;

    @InjectMocks
    private RabbitMqConnectionConfig rabbitMqConnectionConfig;

    @Test
    void setUpQueuesAndExchanges_shouldDeclareQueueExchangeAndBinding() {
        // Act
        rabbitMqConnectionConfig.setUpQueuesAndExchanges();

        // Assert
        verify(amqpAdmin, times(1)).declareQueue(any(Queue.class));
        verify(amqpAdmin, times(1)).declareExchange(any(DirectExchange.class));
        verify(amqpAdmin, times(1)).declareBinding(any(Binding.class));
    }

    @Test
    void queue_shouldReturnPersistentNonExclusiveNonAutoDeleteQueue() {
        // Arrange
        String queueName = "testQueue";

        // Act
        Queue queue = rabbitMqConnectionConfig.queue(queueName);

        // Assert
        assertThat(queue.getName()).isEqualTo(queueName);
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isExclusive()).isFalse();
        assertThat(queue.isAutoDelete()).isFalse();
    }

    @Test
    void exchange_shouldReturnDirectExchange() {
        // Arrange
        String exchangeName = "testExchange";

        // Act
        DirectExchange exchange = rabbitMqConnectionConfig.exchange(exchangeName);

        // Assert
        assertThat(exchange.getName()).isEqualTo(exchangeName);
        assertThat(exchange.getType()).isEqualTo("direct");
    }

    @Test
    void binding_shouldReturnCorrectBinding() {
        // Arrange
        Queue queue = new Queue(FilaConstants.PEDIDO_QUEUE, true, false, false);
        DirectExchange exchange = new DirectExchange(ExchangeConstants.DIRECT);

        // Act
        Binding binding = rabbitMqConnectionConfig.binding(queue, exchange);

        // Assert
        assertThat(binding.getDestination()).isEqualTo(queue.getName());
        assertThat(binding.getDestinationType()).isEqualTo(Binding.DestinationType.QUEUE);
        assertThat(binding.getExchange()).isEqualTo(exchange.getName());
        assertThat(binding.getRoutingKey()).isEqualTo(queue.getName());
    }
}