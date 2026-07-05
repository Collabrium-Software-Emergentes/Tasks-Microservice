package pe.edu.upc.tasks_service.tasks.application.internal.outboundservices.ports;


import pe.edu.upc.tasks_service.shared.infrastructure.clients.groups.resources.GroupOnlyResource;

public interface GroupsQueryPort {

  GroupOnlyResource getGroupOnlyById(Long id);

  GroupOnlyResource getGroupByLeaderId(Long leaderId);
}