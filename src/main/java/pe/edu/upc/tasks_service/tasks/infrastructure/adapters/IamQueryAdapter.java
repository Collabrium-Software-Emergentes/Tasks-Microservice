package pe.edu.upc.tasks_service.tasks.infrastructure.adapters;

import pe.edu.upc.tasks_service.shared.infrastructure.clients.iam.IamFeignClient;
import pe.edu.upc.tasks_service.shared.infrastructure.clients.iam.resources.UserOnlyResource;
import pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.ports.IamQueryPort;

import org.springframework.stereotype.Component;

@Component
public class IamQueryAdapter implements IamQueryPort {

  private final IamFeignClient client;

  public IamQueryAdapter(
      IamFeignClient client
  ) {

    this.client = client;
  }

  @Override
  public UserOnlyResource getUserOnlyById(Long id) {
    return client.getUserOnlyById(id);
  }

  @Override
  public UserOnlyResource getUserByMemberId(Long memberId) {
    return client.getUserOnlyByMemberId(memberId);
  }
}