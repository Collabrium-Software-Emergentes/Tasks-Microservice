package pe.edu.upc.tasks_service.tasks.domain.model.events;

public record MemberRemovedFromGroupEvent(
    Long memberId
) {
}