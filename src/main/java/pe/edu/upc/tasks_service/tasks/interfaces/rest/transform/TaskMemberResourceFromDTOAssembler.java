package pe.edu.upc.tasks_service.tasks.interfaces.rest.transform;


import pe.edu.upc.tasks_service.tasks.application.internal.dto.TaskMemberDTO;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.resources.TaskMemberResource;

public class TaskMemberResourceFromDTOAssembler {

  private TaskMemberResourceFromDTOAssembler() {
  }

  public static TaskMemberResource toResourceFromDTO(
      TaskMemberDTO dto
  ) {

    if (dto == null) {
      return null;
    }

    return new TaskMemberResource(
        dto.id(),
        dto.name(),
        dto.surname(),
        dto.urlImage()
    );
  }
}