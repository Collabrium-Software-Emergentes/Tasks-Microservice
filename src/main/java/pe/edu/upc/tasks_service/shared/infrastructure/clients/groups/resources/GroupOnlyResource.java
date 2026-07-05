package pe.edu.upc.tasks_service.shared.infrastructure.clients.groups.resources;

public record GroupOnlyResource(
    Long id,
    String name,
    String imgUrl,
    String description,
    String code,
    Long leaderId
) {
}