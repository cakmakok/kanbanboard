package com.example.demo.Service;

import com.example.demo.dao.TaskDao;
import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(@Qualifier("PostgresDao") TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> selectAllTasks(){
        return taskDao.selectAllTasks();
    }

    public Task insertTask(Task task){
        return taskDao.insertTask(task);
    }

    public Optional<Task> getTaskById(long id){
        return taskDao.getTaskById(id);
    }

    public Task updateTask(long id, Task task){
        return taskDao.updateTask(id,task);
    }

    public ResponseEntity<?> deleteTask(long id){
        return taskDao.deleteTaskById(id);
    }

}
