package com.fiap.tc4_srv_pedido_receiver.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RabbitMqServiceAdapterTest {

    @Mock
    private RabbitTemplate service;
    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private RabbitMqServiceAdapter adapter;

    @Test
    void sendMessage_success()
            throws JsonProcessingException {
        // Arrange
        String message = "some message";
        String queueName = "myQueue";

        when(mapper.writeValueAsString(any())).thenReturn(message);

        // Act
        adapter.send(queueName, message);

        // Assert
        verify(service).convertAndSend(queueName, message);
    }

    @Test
    void sendMessage_failure()
            throws JsonProcessingException {
        // Arrange
        String message = "some message";
        String queueName = "myQueue";

        when(mapper.writeValueAsString(any())).thenReturn(message);
        doThrow(new AmqpException("Failed to send message")).when(service).convertAndSend(queueName, message);

        // Act & Assert
        assertThatThrownBy(() -> adapter.send(queueName, message))
                .isInstanceOf(IllegalCallerException.class);

        verify(service).convertAndSend(queueName, message);
    }
}
