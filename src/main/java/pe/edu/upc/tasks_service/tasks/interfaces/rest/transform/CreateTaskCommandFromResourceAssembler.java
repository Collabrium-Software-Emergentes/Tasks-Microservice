package pe.edu.upc.tasks_service.tasks.interfaces.rest.transform;

import com.collabrium.tasks.management.domain.model.commands.CreateTaskCommand;
import com.collabrium.tasks.management.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {

  private CreateTaskCommandFromResourceAssembler() {
  }

  public static CreateTaskCommand toCommandFromResource(
      CreateTaskResource resource,
      Long memberId,
      Long userId
  ) {

    return new CreateTaskCommand(
        resource.title(),
        resource.description(),
        resource.dueDate(),
        memberId,
        userId
    );
  }
}