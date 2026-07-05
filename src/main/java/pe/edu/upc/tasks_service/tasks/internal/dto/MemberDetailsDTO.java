package pe.edu.upc.tasks_service.tasks.internal.dto;

public record MemberDetailsDTO(
    Long memberId,
    String username,
    String name,
    String surname,
    String imgUrl,
    String email,
    Long groupId
) {
}