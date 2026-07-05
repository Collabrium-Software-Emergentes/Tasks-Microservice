package pe.edu.upc.tasks_service.tasks.domain.exceptions;

public class InvalidGroupIdException extends RuntimeException {

  private InvalidGroupIdException(String message) {
    super(message);
  }

  public static InvalidGroupIdException forNull() {
    return new InvalidGroupIdException(
        "GroupId value cannot be null"
    );
  }

  public static InvalidGroupIdException forZeroOrNegative(Long value) {
    return new InvalidGroupIdException(
        String.format(
            "GroupId value must be greater than 0, but was: %d",
            value
        )
    );
  }
}
