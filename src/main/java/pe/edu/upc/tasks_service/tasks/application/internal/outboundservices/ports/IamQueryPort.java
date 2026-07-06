package pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.ports;


import pe.edu.upc.tasks_service.shared.infrastructure.clients.iam.resources.UserOnlyResource;

public interface IamQueryPort {

  UserOnlyResource getUserOnlyById(Long id);

  UserOnlyResource getUserByMemberId(Long memberId);
}