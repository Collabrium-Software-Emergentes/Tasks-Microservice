package pe.edu.upc.tasks_service.tasks.domain.services;



import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Task;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetTaskByIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetTasksByGroupIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetTasksByMemberIdQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {

  /**
   * Retrieves tasks assigned to a specific member.
   */
  Optional<Task> handle(GetTaskByIdQuery query);

  List<Task> handle(GetTasksByMemberIdQuery query);

  List<Task> handle(GetTasksByGroupIdQuery query);
}