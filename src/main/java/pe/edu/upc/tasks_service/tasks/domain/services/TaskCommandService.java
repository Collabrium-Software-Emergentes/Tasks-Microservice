package pe.edu.upc.tasks_service.tasks.domain.services;

import com.collabrium.tasks.management.domain.model.commands.DeleteTaskCommand;

public interface TaskCommandService {

  void handle(DeleteTaskCommand command);
}