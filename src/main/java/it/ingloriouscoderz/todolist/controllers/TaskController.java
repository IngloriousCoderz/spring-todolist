package it.ingloriouscoderz.todolist.controllers;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private List<Task> tasks;

  public TaskController() {
    tasks = new ArrayList<Task>();
    tasks.add(new Task(1, "Learn Spring", true));
    tasks.add(new Task(2, "Do interview", false));
    tasks.add(new Task(3, "Forget everything", false));
  }

  @GetMapping
  public List<Task> getTasks() {
    return tasks;
  }

  @PostMapping
  public Task create(@RequestBody Task body) {
    int maxId = tasks.size() > 0 ? tasks.get(tasks.size() - 1).id() : 0;
    Task task = new Task(maxId + 1, body.text(), body.completed());
    tasks.add(task);
    return task;
  }

  @PutMapping("/{id}")
  public Task replace(@PathVariable int id, @RequestBody Task body) {
    Task taskToReplace = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    int index = tasks.indexOf(taskToReplace);
    Task task = new Task(id, body.text(), body.completed());
    tasks.set(index, task);
    return task;
  }

  @PatchMapping("/{id}")
  public Task update(@PathVariable int id, @RequestBody Task body) {
    Task taskToReplace = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    int index = tasks.indexOf(taskToReplace);
    Task task = new Task(id, body.text() != null ? body.text() : taskToReplace.text(),
        body.completed() != null ? body.completed() : taskToReplace.completed());
    tasks.set(index, task);
    return task;
  }

  @DeleteMapping("/{id}")
  public Task delete(@PathVariable int id) {
    Task task = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    tasks.remove(task);
    return task;
  }
}
