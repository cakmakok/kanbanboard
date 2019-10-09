package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import com.example.demo.model.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "tasks")
public class Task {

    public enum TaskStatus {
        REQUESTED(0),IN_PROGRESS(1),DONE(2);
        private int value;
        TaskStatus(int value) {
            this.value =value;
        }
        public int getValue() { return value; }

        public static TaskStatus parse(int id) {
            TaskStatus task = null; // Default
            for (TaskStatus item : TaskStatus.values()) {
                if (item.getValue()==id) {
                    task = item;
                    break;
                }
            }
            return task;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "task_status")
    private int task_status_id;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "PersonTask",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"task_id", "person_id"})})
    @JsonIgnoreProperties("tasks")
    private List<Person> persons;

    public List<Person> getPersons(){
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public TaskStatus getTaskStatus() {
        return TaskStatus.parse(this.task_status_id);
    }

    public void setTaskStatus(TaskStatus taskstatus) {
        this.task_status_id = taskstatus.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
