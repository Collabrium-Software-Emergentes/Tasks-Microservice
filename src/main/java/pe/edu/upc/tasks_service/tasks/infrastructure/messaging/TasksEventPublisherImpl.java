package pe.edu.upc.tasks_service.tasks.infrastructure.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.messaging.TasksEventPublisher;
import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberCreatedEvent;
import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberLeftGroupEvent;

import static pe.edu.upc.tasks_service.shared.infrastructure.configuration.rabbitmq.RabbitMQConfiguration.*;

@Service
public class TasksEventPublisherImpl implements TasksEventPublisher {

  private final AmqpTemplate rabbitTemplate;

  public TasksEventPublisherImpl(AmqpTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void publishMemberCreated(MemberCreatedEvent event) {

    rabbitTemplate.convertAndSend(
        TASKS_EXCHANGE,
        MEMBER_CREATED_KEY,
        event
    );
  }

  @Override
  public void publishMemberLeftGroup(MemberLeftGroupEvent event) {

    rabbitTemplate.convertAndSend(
        TASKS_EXCHANGE,
        MEMBER_LEFT_GROUP_KEY,
        event
    );
  }
}