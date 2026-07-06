package pe.edu.upc.tasks_service.tasks.application.internal.mappers;

import pe.edu.upc.tasks_service.tasks.application.internal.dto.TaskDetailsDTO;
import pe.edu.upc.tasks_service.tasks.application.internal.dto.TaskMemberDTO;
import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Member;
import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Task;
import pe.edu.upc.tasks_service.shared.infrastructure.clients.iam.resources.UserOnlyResource;

public class TaskDetailsDTOAssembler {

  private TaskDetailsDTOAssembler() {
  }

  public static TaskDetailsDTO toDTO(
      Task task,
      Member member,
      UserOnlyResource user
  ) {

    var memberDTO =
        new TaskMemberDTO(
            member.getId(),
            user.name(),
            user.surname(),
            user.imgUrl()
        );

    return new TaskDetailsDTO(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getDueDate().toString(),
        task.getCreatedAt().toString(),
        task.getUpdatedAt() != null
            ? task.getUpdatedAt().toString()
            : null,
        task.getStatus().name(),
        task.getTimesRearranged(),
        task.getTimePassed(),
        task.getImageUrl(),
        memberDTO,
        task.getGroupId() != null
            ? task.getGroupId().value()
            : null
    );
  }
}