package pe.edu.upc.tasks_service.tasks.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import pe.edu.upc.tasks_service.tasks.domain.exceptions.InvalidGroupIdException;

@Embeddable
public record GroupId(Long value) {

  public GroupId {
    validateGroupId(value);
  }

  private static void validateGroupId(Long value) {

    if (value == null) {
      throw InvalidGroupIdException.forNull();
    }

    if (value <= 0) {
      throw InvalidGroupIdException.forZeroOrNegative(value);
    }
  }
}