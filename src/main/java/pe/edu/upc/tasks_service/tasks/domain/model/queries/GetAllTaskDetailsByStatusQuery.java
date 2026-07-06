package pe.edu.upc.tasks_service.tasks.domain.model.queries;

public record GetAllTaskDetailsByStatusQuery(
    String taskStatus
) {
}