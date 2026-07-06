package pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.RemoveMemberFromGroupCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.events.MemberRemovedFromGroupEvent;

public class RemoveMemberFromGroupCommandFromEventAssembler {

  private RemoveMemberFromGroupCommandFromEventAssembler() {
  }

  public static RemoveMemberFromGroupCommand toCommandFromEvent(
      MemberRemovedFromGroupEvent event
  ) {
    return new RemoveMemberFromGroupCommand(
        event.memberId()
    );
  }
}