package pe.edu.upc.tasks_service.tasks.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tasks_service.tasks.domain.model.aggregates.Task;
import pe.edu.upc.tasks_service.tasks.domain.model.valueobjects.GroupId;
import pe.edu.upc.tasks_service.tasks.domain.model.valueobjects.TaskStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByMember_Id(Long memberId);

  List<Task> findByStatus(TaskStatus status);

  List<Task> findByGroupId_Value(Long groupId);

  void deleteAllByMember_Id(Long memberId);

  void deleteAllByGroupId(GroupId groupId);

  Optional<Task> findFirstByMember_IdAndStatusAndDueDateAfterOrderByDueDateAsc(
      Long memberId,
      TaskStatus status,
      OffsetDateTime now
  );
}