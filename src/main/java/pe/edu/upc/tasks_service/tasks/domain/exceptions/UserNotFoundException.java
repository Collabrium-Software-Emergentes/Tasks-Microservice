package pe.edu.upc.tasks_service.tasks.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public static UserNotFoundException forId(Long userId) {

    return new UserNotFoundException(
        "User not found with id: " + userId
    );
  }

  public static UserNotFoundException forMember(Long memberId) {

    return new UserNotFoundException(
        "User not found for member id: " + memberId
    );
  }
}