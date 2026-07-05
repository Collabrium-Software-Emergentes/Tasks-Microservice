package pe.edu.upc.tasks_service.shared.infrastructure.security;

import java.util.List;

public record AuthenticatedUser(
    Long userId,
    String username,
    List<String> roles
) {
}