package pe.edu.upc.tasks_service.tasks.interfaces.rest.controllers;

import pe.edu.upc.tasks_service.tasks.domain.model.commands.LeaveGroupCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetAllMembersByGroupIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.model.queries.GetMemberByIdQuery;
import pe.edu.upc.tasks_service.tasks.domain.services.MemberCommandService;
import pe.edu.upc.tasks_service.tasks.domain.services.MemberQueryService;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.resources.MemberOnlyResource;
import pe.edu.upc.tasks_service.tasks.interfaces.rest.transform.MemberOnlyResourceFromEntityAssembler;
import pe.edu.upc.tasks_service.shared.infrastructure.security.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "Member management API")
public class MemberController {

  private final MemberQueryService memberQueryService;
  private final MemberCommandService memberCommandService;

  public MemberController(
      MemberQueryService memberQueryService,
      MemberCommandService memberCommandService
  ) {

    this.memberQueryService = memberQueryService;
    this.memberCommandService = memberCommandService;
  }

  @GetMapping("{memberId}")
  @Operation(summary = "Get member details by id", description = "Fetches the details of the member.")
  public ResponseEntity<MemberOnlyResource> getMemberById(
      @PathVariable Long memberId
  ) {

    var getMemberByIdQuery = new GetMemberByIdQuery(memberId);
    var memberOptional = memberQueryService.handle(getMemberByIdQuery);
    if (memberOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var memberResource = MemberOnlyResourceFromEntityAssembler.toResourceFromEntity(memberOptional.get());
    return ResponseEntity.ok(memberResource);
  }

  @DeleteMapping("/group/leave")
  @Operation(
      summary = "Leave group by member authenticated",
      description = "Allows the authenticated member to leave their group.")
  public ResponseEntity<Void> leaveGroupByMemberAuthenticated(
      @AuthenticationPrincipal AuthenticatedUser user
  ) {

    var leaveGroupCommand = new LeaveGroupCommand(user.userId());

    memberCommandService.handle(leaveGroupCommand);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/basic")
  @Operation(
      summary = "Get all members by group ID",
      description = "Fetches a list of all members belonging to the specified group."
  )
  public ResponseEntity<List<MemberOnlyResource>> getAllMembersByGroupId(
      @RequestParam Long groupId
  ) {

    var getAllMembersByGroupIdQuery = new GetAllMembersByGroupIdQuery(groupId);

    var members = memberQueryService.handle(getAllMembersByGroupIdQuery);

    var membersResources = members
        .stream()
        .map(MemberOnlyResourceFromEntityAssembler::toResourceFromEntity)
        .toList();

    return ResponseEntity.ok(membersResources);
  }
}