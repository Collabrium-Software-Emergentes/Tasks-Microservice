package pe.edu.upc.tasks_service.tasks.interfaces.rest.transform;


import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Member;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.resources.MemberOnlyResource;

public class MemberOnlyResourceFromEntityAssembler {

  private MemberOnlyResourceFromEntityAssembler() {
  }

  public static MemberOnlyResource toResourceFromEntity(
      Member entity
  ) {

    Long groupId = entity.getGroupId() != null
        ? entity.getGroupId().value()
        : null;

    return new MemberOnlyResource(
        entity.getId(),
        groupId
    );
  }
}