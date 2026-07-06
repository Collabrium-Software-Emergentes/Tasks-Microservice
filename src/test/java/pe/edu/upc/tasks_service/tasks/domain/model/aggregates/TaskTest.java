package pe.edu.upc.tasks_service.tasks.domain.model.aggregates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.CreateTaskCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.UpdateTaskCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.commands.UpdateTaskStatusCommand;
import pe.edu.upc.tasks_service.tasks.domain.model.valueobjects.TaskStatus;

import java.lang.reflect.Field;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;
    private OffsetDateTime now;

    @BeforeEach
    void setUp() {
        now = OffsetDateTime.now(ZoneOffset.UTC);
        CreateTaskCommand command = new CreateTaskCommand(
                "Test Title",
                "Test Description",
                now.plusDays(1),
                1L
        );
        task = new Task(command);

        try {
            setField(task, "createdAt", now);
            setField(task, "updatedAt", now);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set auditable fields via reflection: " + e.getMessage());
        }
    }

    private void setField(Object targetObject, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = targetObject.getClass().getSuperclass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(targetObject, value);
    }

    @Test
    void constructor_ShouldInitializeTaskWithProvidedValuesAndDefaultStatus() {
        assertNotNull(task);
        assertEquals("Test Title", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertEquals(now.plusDays(1).toLocalDate(), task.getDueDate().toLocalDate());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(0, task.getTimesRearranged());
        assertEquals(0L, task.getTimePassed());
    }

    @Test
    void updateStatus_FromInProgressToCompleted_ShouldUpdateTimePassed() {
        try {
            Thread.sleep(100);
            setField(task, "updatedAt", OffsetDateTime.now(ZoneOffset.UTC));
        } catch (InterruptedException | NoSuchFieldException | IllegalAccessException e) {
            Thread.currentThread().interrupt();
            fail("Failed to set auditable fields via reflection: " + e.getMessage());
        }

        UpdateTaskStatusCommand command = new UpdateTaskStatusCommand(task.getId(), TaskStatus.COMPLETED.name());
        task.updateStatus(command);

        assertEquals(TaskStatus.COMPLETED, task.getStatus());
        assertTrue(task.getTimePassed() > 0);
    }

    @Test
    void updateStatus_FromCompletedToInProgress_ShouldIncrementTimesRearranged() {
        try {
            setField(task, "updatedAt", OffsetDateTime.now(ZoneOffset.UTC)); // Simulate update
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set auditable fields via reflection: " + e.getMessage());
        }
        task.updateStatus(new UpdateTaskStatusCommand(task.getId(), TaskStatus.COMPLETED.name()));
        assertEquals(TaskStatus.COMPLETED, task.getStatus());

        try {
            setField(task, "updatedAt", OffsetDateTime.now(ZoneOffset.UTC)); // Simulate update
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set auditable fields via reflection: " + e.getMessage());
        }
        task.updateStatus(new UpdateTaskStatusCommand(task.getId(), TaskStatus.IN_PROGRESS.name()));
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(1, task.getTimesRearranged());
    }

    @Test
    void updateTask_ShouldUpdateTitleDescriptionAndDueDate() {
        OffsetDateTime newDueDate = now.plusDays(5);
        UpdateTaskCommand command = new UpdateTaskCommand(
                task.getId(),
                "New Title",
                "New Description",
                newDueDate,
                1L
        );
        task.updateTask(command);

        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertEquals(newDueDate.toLocalDate(), task.getDueDate().toLocalDate());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void updateTask_WithPastDueDate_ShouldSetStatusToExpired() {
        OffsetDateTime pastDueDate = now.minusDays(1);
        UpdateTaskCommand command = new UpdateTaskCommand(
                task.getId(),
                "Title",
                "Description",
                pastDueDate,
                1L
        );
        task.updateTask(command);

        assertEquals(pastDueDate.toLocalDate(), task.getDueDate().toLocalDate());
        assertEquals(TaskStatus.EXPIRED, task.getStatus());
    }
}