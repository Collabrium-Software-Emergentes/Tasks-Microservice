package pe.edu.upc.tasks_service.tasks.internal.outboundservices.ports;

import com.collabrium.tasks.shared.infrastructure.clients.groups.resources.GroupOnlyResource;

public interface GroupsQueryPort {

  GroupOnlyResource getGroupOnlyById(Long id);

  GroupOnlyResource getGroupByLeaderId(Long leaderId);
}