package com.example.demo.controller;
import com.example.demo.exception.*;

import com.example.demo.Service.TaskService;
import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/kanban")
@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.selectAllTasks();
    }

    @GetMapping(value = "tasks/{id}")
    public Optional<Task> get(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public Task createQuestion(@RequestBody Task task) {
        return taskService.insertTask(task);
    }

    @PutMapping(value = "tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping(value = "tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

}
