package pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.messaging;


import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberCreatedEvent;
import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberLeftGroupEvent;

public interface TasksEventPublisher {

  void publishMemberCreated(MemberCreatedEvent event);

  void publishMemberLeftGroup(MemberLeftGroupEvent event);
}