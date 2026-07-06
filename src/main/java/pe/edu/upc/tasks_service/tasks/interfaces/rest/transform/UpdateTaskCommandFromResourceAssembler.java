package pe.edu.upc.tasks_service.tasks.interfaces.rest.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.UpdateTaskCommand;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {

  private UpdateTaskCommandFromResourceAssembler() {
  }

  public static UpdateTaskCommand toCommandFromResource(
      UpdateTaskResource resource,
      Long taskId,
      Long userId
  ) {

    return new UpdateTaskCommand(
        taskId,
        resource.title(),
        resource.description(),
        resource.dueDate(),
        resource.memberId(),
        userId
    );
  }
}