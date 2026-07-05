package pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.DeleteGroupDataCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.events.GroupDeletedEvent;

public class DeleteGroupDataCommandFromEventAssembler {

  private DeleteGroupDataCommandFromEventAssembler() {
  }

  public static DeleteGroupDataCommand toCommandFromEvent(
      GroupDeletedEvent event
  ) {

    return new DeleteGroupDataCommand(
        event.groupId()
    );
  }
}