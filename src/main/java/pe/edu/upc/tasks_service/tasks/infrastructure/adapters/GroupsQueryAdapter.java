package pe.edu.upc.tasks_service.tasks.infrastructure.adapters;

import org.springframework.stereotype.Component;
import pe.edu.upc.tasks_service.shared.infrastructure.clients.groups.GroupsFeignClient;
import pe.edu.upc.tasks_service.shared.infrastructure.clients.groups.resources.GroupOnlyResource;
import pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.ports.GroupsQueryPort;

@Component
public class GroupsQueryAdapter implements GroupsQueryPort {

  private final GroupsFeignClient client;

  public GroupsQueryAdapter(
      GroupsFeignClient client
  ) {

    this.client = client;
  }

  @Override
  public GroupOnlyResource getGroupOnlyById(Long id) {
    return client.getGroupOnlyById(id);
  }

  @Override
  public GroupOnlyResource getGroupByLeaderId(Long leaderId) {
    return client.getGroupByLeaderId(leaderId);
  }
}