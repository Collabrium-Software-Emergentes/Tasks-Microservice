package pe.edu.upc.tasks_service.tasks.domain.services;



import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Member;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetAllMembersByGroupIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetMemberByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MemberQueryService {

  /**
   * Retrieves a member by their ID.
   *
   * @param query the query containing the member ID
   * @return an Optional containing the member if found, or empty if not found
   */
  Optional<Member> handle(GetMemberByIdQuery query);

  List<Member> handle(GetAllMembersByGroupIdQuery query);
}