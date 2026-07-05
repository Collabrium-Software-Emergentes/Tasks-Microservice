package pe.edu.upc.tasks_service.tasks.internal.outboundservices.messaging;

import com.collabrium.tasks.management.domain.model.events.MemberCreatedEvent;
import com.collabrium.tasks.management.domain.model.events.MemberLeftGroupEvent;

public interface TasksEventPublisher {

  void publishMemberCreated(MemberCreatedEvent event);

  void publishMemberLeftGroup(MemberLeftGroupEvent event);
}