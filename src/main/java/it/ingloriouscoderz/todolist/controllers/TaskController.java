package it.ingloriouscoderz.todolist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.ingloriouscoderz.todolist.model.Task;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
  private List<Task> tasks;

  public TaskController() {
    tasks = new ArrayList<Task>();
    tasks.add(new Task(1, "Learn Spring", true));
    tasks.add(new Task(2, "Do interview", false));
    tasks.add(new Task(3, "Forget everything", false));
  }

  @GetMapping("/tasks")
  public List<Task> getTasks() {
    return tasks;
  }

  @PostMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
  public Task create(@RequestBody Task body) {
    int maxId = tasks.size() > 0 ? tasks.get(tasks.size() - 1).id(): 0;
    Task task = new Task(maxId + 1, body.text(), body.completed());
    tasks.add(task);
    return task;
  }
}
