package pe.edu.upc.tasks_service.tasks.interfaces.messaging.listeners;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.upc.tasks_service.tasks.domain.model.events.UserMemberCreatedEvent;
import pe.edu.upc.tasks_service.tasks.domain.services.MemberCommandService;
import pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform.CreateMemberCommandFromEventAssembler;

import static pe.edu.upc.tasks_service.shared.infrastructure.configuration.rabbitmq.RabbitMQConfiguration.USER_MEMBER_CREATED_QUEUE;

@Component
public class IamEventsListener {

  private final MemberCommandService memberCommandService;

  public IamEventsListener(
      MemberCommandService memberCommandService
  ) {

    this.memberCommandService = memberCommandService;
  }

  @RabbitListener(queues = USER_MEMBER_CREATED_QUEUE)
  public void handle(UserMemberCreatedEvent event) {

    var createMemberCommand = CreateMemberCommandFromEventAssembler.toCommandFromEvent(event);

    memberCommandService.handle(createMemberCommand);
  }
}