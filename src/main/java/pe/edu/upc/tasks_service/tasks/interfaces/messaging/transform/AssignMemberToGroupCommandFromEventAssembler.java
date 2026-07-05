package pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.AssignMemberToGroupCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.events.InvitationAcceptedEvent;

public class AssignMemberToGroupCommandFromEventAssembler {

  private AssignMemberToGroupCommandFromEventAssembler() {
  }

  public static AssignMemberToGroupCommand toCommandFromEvent(
      InvitationAcceptedEvent event
  ) {

    return new AssignMemberToGroupCommand(
        event.memberId(),
        event.groupId()
    );
  }
}