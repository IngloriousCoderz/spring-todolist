package it.ingloriouscoderz.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ingloriouscoderz.todolist.model.Task;
import it.ingloriouscoderz.todolist.services.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  TaskRepository tasks;

  @GetMapping
  public List<Task> getTasks() {
    return tasks.findAll();
  }

  @PostMapping
  public Task create(@RequestBody Task body) {
    Task task = tasks.insert(new Task(null, body.text(), body.completed()));
    return task;
  }

  @PutMapping("/{id}")
  public Task replace(@PathVariable String id, @RequestBody Task body) {
    Task task = tasks.save(new Task(id, body.text(), body.completed()));
    return task;
  }

  @PatchMapping("/{id}")
  public Task update(@PathVariable String id, @RequestBody Task body) {
    Optional<Task> taskToUpdate = tasks.findById(id);
    Task task = tasks.save(new Task(id, body.text() != null ? body.text() : taskToUpdate.get().text(),
        body.completed() != null ? body.completed() : taskToUpdate.get().completed()));
    return task;
  }

  @DeleteMapping("/{id}")
  public Task delete(@PathVariable String id) {
    Optional<Task> task = tasks.findById(id);
    tasks.delete(task.get());
    return task.get();
  }
}
