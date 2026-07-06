package pe.edu.upc.tasks_service.tasks.application.internal.commandservices;

import pe.edu.upc.tasks_service.tasks.domain.exceptions.InvalidTaskException;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.DeleteTaskCommand;
import pe.edu.upc.tasks_service.tasks.domain.services.TaskCommandService;
import pe.edu.upc.tasks_service.tasks.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {

  private final TaskRepository taskRepository;

  public TaskCommandServiceImpl(
      TaskRepository taskRepository
  ) {

    this.taskRepository = taskRepository;
  }

  @Override
  public void handle(DeleteTaskCommand command) {

    if (command == null) {
      throw InvalidTaskException.forNullDeleteCommand();
    }

    var taskId = command.taskId();

    if (!taskRepository.existsById(taskId)) {
      throw InvalidTaskException.forTaskNotFound(taskId);
    }

    taskRepository.deleteById(taskId);
  }
}