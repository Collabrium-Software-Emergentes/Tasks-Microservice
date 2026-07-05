package pe.edu.upc.tasks_service.tasks.application.internal.queryservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Member;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetAllMembersByGroupIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetMemberByIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.valueobjects.GroupId;
import pe.edu.upc.tasks_service.tasks.domain.services.MemberQueryService;
import pe.edu.upc.tasks_service.tasks.infrastructure.persistence.jpa.repositories.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberQueryServiceImpl implements MemberQueryService {

  private final MemberRepository memberRepository;

  public MemberQueryServiceImpl(
      MemberRepository memberRepository
  ) {

    this.memberRepository = memberRepository;
  }

  @Override
  public Optional<Member> handle(GetMemberByIdQuery query) {
    return memberRepository.findById(query.memberId());
  }

  @Override
  public List<Member> handle(GetAllMembersByGroupIdQuery query) {

    var groupId = new GroupId(query.groupId());

    return memberRepository.findMembersByGroupId(groupId);
  }
}