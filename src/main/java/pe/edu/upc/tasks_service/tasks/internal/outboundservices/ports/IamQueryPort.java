package pe.edu.upc.tasks_service.tasks.internal.outboundservices.ports;

import com.collabrium.tasks.shared.infrastructure.clients.iam.resources.UserOnlyResource;

public interface IamQueryPort {

  UserOnlyResource getUserOnlyById(Long id);

  UserOnlyResource getUserByMemberId(Long memberId);
}