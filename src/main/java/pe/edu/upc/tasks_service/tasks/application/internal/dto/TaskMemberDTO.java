package pe.edu.upc.tasks_service.tasks.application.internal.dto;

public record TaskMemberDTO(
    Long id,
    String name,
    String surname,
    String urlImage
) {
}