package pe.edu.upc.tasks_service.tasks.domain.services;


import pe.edu.upc.tasks_service.tasks.domain.model.commands.DeleteTaskCommand;

public interface TaskCommandService {

  void handle(DeleteTaskCommand command);
}