package pe.edu.upc.tasks_service.tasks.rest.resources;

import java.time.OffsetDateTime;

public record CreateTaskResource(String title,
                                 String description,
                                 OffsetDateTime dueDate) {
}
