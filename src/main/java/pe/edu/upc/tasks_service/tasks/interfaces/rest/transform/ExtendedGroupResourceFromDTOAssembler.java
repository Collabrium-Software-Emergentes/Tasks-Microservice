package pe.edu.upc.tasks_service.tasks.interfaces.rest.transform;


import pe.edu.upc.tasks_service.tasks.application.internal.dto.ExtendedGroupDTO;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.resources.ExtendedGroupResource;

public class ExtendedGroupResourceFromDTOAssembler {

  private ExtendedGroupResourceFromDTOAssembler() {
  }

  public static ExtendedGroupResource toResourceFromDTO(
      ExtendedGroupDTO dto
  ) {

    var members =
        dto.members()
            .stream()
            .map(
                MemberResourceFromDTOAssembler
                    ::toResourceFromDTO
            )
            .toList();

    return new ExtendedGroupResource(
        dto.id(),
        dto.name(),
        dto.imgUrl(),
        dto.description(),
        dto.code(),
        members
    );
  }
}