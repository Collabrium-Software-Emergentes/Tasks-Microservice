package pe.edu.upc.tasks_service.tasks.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.tasks_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.tasks_service.tasks.domain.exceptions.InvalidMemberException;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.CreateMemberCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.valueobjects.GroupId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "members")
@NoArgsConstructor
@AttributeOverrides({
    @AttributeOverride(
        name = "groupId.value",
        column = @Column(name = "group_id")
    )
})
public class Member extends AuditableAbstractAggregateRoot<Member> {

  @OneToMany(
      mappedBy = "member",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<Task> tasks = new ArrayList<>();

  @Embedded
  private GroupId groupId;

  public Member(CreateMemberCommand command) {
    validateCreation(command);
    this.groupId = null;
  }

  private void validateCreation(CreateMemberCommand command) {

    if (command == null) {
      throw InvalidMemberException.forNullCreateCommand();
    }
  }

  public void assignGroup(GroupId groupId) {

    if (groupId == null) {
      throw InvalidMemberException.forNullGroupId();
    }

    this.groupId = groupId;
  }

  public void removeGroup() {
    this.groupId = null;
  }

  public boolean belongsToGroup() {
    return this.groupId != null;
  }

  public void addTask(Task task) {

    validateTask(task);

    this.tasks.add(task);
    task.setMember(this);
  }

  public void removeTask(Task task) {

    validateTask(task);

    this.tasks.remove(task);
    task.setMember(null);
  }

  public void clearTasks() {

    if (this.tasks.isEmpty()) {
      return;
    }

    for (Task task : this.tasks) {
      task.setMember(null);
    }

    this.tasks.clear();
  }

  private void validateTask(Task task) {

    if (task == null) {
      throw InvalidMemberException.forNullTask();
    }
  }

  @Override
  public String toString() {

    return String.format(
        "Member{id=%d, groupId=%s, tasks=%d}",
        getId() != null ? getId() : 0,
        groupId != null ? groupId.toString() : "null",
        tasks.size()
    );
  }
}