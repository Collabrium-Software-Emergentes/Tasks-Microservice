package pe.edu.upc.tasks_service.tasks.interfaces.messaging.transform;

import com.collabrium.tasks.management.domain.model.commands.RemoveMemberFromGroupCommand;
import com.collabrium.tasks.management.domain.model.events.MemberRemovedFromGroupEvent;

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