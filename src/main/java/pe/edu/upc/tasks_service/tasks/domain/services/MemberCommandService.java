package pe.edu.upc.tasks_service.tasks.domain.services;



import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Member;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.*;

import java.util.Optional;

public interface MemberCommandService {

  void handle(CreateMemberCommand command);

  Optional<Member> handle(AssignMemberToGroupCommand command);

  Optional<Member> handle(RemoveMemberFromGroupCommand command);

  void handle(LeaveGroupCommand command);

  void handle(DeleteGroupDataCommand command);
}
