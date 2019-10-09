package com.example.demo.dao;

import com.example.demo.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


//TODO Itaskdao
public interface TaskDao {
    Task insertTask(Task task);

    List<Task> selectAllTasks();

    Optional<Task> getTaskById(long id);

    Task updateTask(long id, Task task);

    ResponseEntity<?> deleteTaskById(long id);

}
