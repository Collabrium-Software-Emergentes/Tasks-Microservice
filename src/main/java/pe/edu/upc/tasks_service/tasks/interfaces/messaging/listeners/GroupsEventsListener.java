package pe.edu.upc.tasks_service.tasks.interfaces.messaging.listeners;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.upc.tasks_service.tasks.domain.model.events.GroupDeletedEvent;
import pe.edu.upc.tasks_service.tasks.domain.model.events.InvitationAcceptedEvent;
import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberRemovedFromGroupEvent;
import pe.edu.upc.tasks_service.tasks.domain.services.MemberCommandService;
import pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform.AssignMemberToGroupCommandFromEventAssembler;
import pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform.DeleteGroupDataCommandFromEventAssembler;
import pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform.RemoveMemberFromGroupCommandFromEventAssembler;

import static pe.edu.upc.tasks_service.shared.infrastructure.configuration.rabbitmq.RabbitMQConfiguration.*;

@Component
public class GroupsEventsListener {

  private final MemberCommandService memberCommandService;

  public GroupsEventsListener(
      MemberCommandService memberCommandService
  ) {

    this.memberCommandService = memberCommandService;
  }

  @RabbitListener(queues = INVITATION_ACCEPTED_QUEUE)
  public void handleInvitationAccepted(
      InvitationAcceptedEvent event
  ) {

    var command =
        AssignMemberToGroupCommandFromEventAssembler
            .toCommandFromEvent(event);

    memberCommandService.handle(command);
  }

  @RabbitListener(queues = MEMBER_REMOVED_FROM_GROUP_QUEUE)
  public void handleRemovedMemberFromGroup(
      MemberRemovedFromGroupEvent event
  ) {

    var command =
        RemoveMemberFromGroupCommandFromEventAssembler
            .toCommandFromEvent(event);

    memberCommandService.handle(command);
  }

  @RabbitListener(queues = GROUP_DELETED_QUEUE)
  public void handleGroupDeleted(
      GroupDeletedEvent event
  ) {

    var command =
        DeleteGroupDataCommandFromEventAssembler
            .toCommandFromEvent(event);

    memberCommandService.handle(command);
  }
}