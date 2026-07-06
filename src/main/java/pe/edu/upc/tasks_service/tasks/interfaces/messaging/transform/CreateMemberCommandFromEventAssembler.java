package pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.CreateMemberCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.events.UserMemberCreatedEvent;

public class CreateMemberCommandFromEventAssembler {

  private CreateMemberCommandFromEventAssembler() {
  }

  public static CreateMemberCommand toCommandFromEvent(
      UserMemberCreatedEvent event
  ) {

    return new CreateMemberCommand(
        event.userId()
    );
  }
}