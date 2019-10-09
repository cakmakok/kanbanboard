package com.example.demo.dao;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.example.demo.exception.*;
import java.util.List;
import java.util.Optional;

@Repository("PostgresDao")

public class PostgresDataAccessService implements TaskDao {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task insertTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> selectAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task updateTask(long id, Task task) {
        return taskRepository.findById(id).map(t -> {
                    t.setTitle(task.getTitle());
                    t.setDescription(task.getDescription());
                    t.setTaskStatus(task.getTaskStatus());
                    t.setPersons(task.getPersons());
            return taskRepository.save(t);
                }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    @Override
    public ResponseEntity<?> deleteTaskById(long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }
}
