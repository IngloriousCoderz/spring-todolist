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

import it.ingloriouscoderz.todolist.model.InMemoryTask;

@RestController
@RequestMapping("/im-tasks")
public class InMemoryTaskController {
  private List<InMemoryTask> tasks;

  public InMemoryTaskController() {
    tasks = new ArrayList<InMemoryTask>();
    tasks.add(new InMemoryTask(1, "Learn Spring", true));
    tasks.add(new InMemoryTask(2, "Do interview", false));
    tasks.add(new InMemoryTask(3, "Forget everything", false));
  }

  @GetMapping
  public List<InMemoryTask> getTasks() {
    return tasks;
  }

  @PostMapping
  public InMemoryTask create(@RequestBody InMemoryTask body) {
    int maxId = tasks.size() > 0 ? tasks.get(tasks.size() - 1).id() : 0;
    InMemoryTask task = new InMemoryTask(maxId + 1, body.text(), body.completed());
    tasks.add(task);
    return task;
  }

  @PutMapping("/{id}")
  public InMemoryTask replace(@PathVariable int id, @RequestBody InMemoryTask body) {
    InMemoryTask taskToReplace = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    int index = tasks.indexOf(taskToReplace);
    InMemoryTask task = new InMemoryTask(id, body.text(), body.completed());
    tasks.set(index, task);
    return task;
  }

  @PatchMapping("/{id}")
  public InMemoryTask update(@PathVariable int id, @RequestBody InMemoryTask body) {
    InMemoryTask taskToReplace = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    int index = tasks.indexOf(taskToReplace);
    InMemoryTask task = new InMemoryTask(id, body.text() != null ? body.text() : taskToReplace.text(),
        body.completed() != null ? body.completed() : taskToReplace.completed());
    tasks.set(index, task);
    return task;
  }

  @DeleteMapping("/{id}")
  public InMemoryTask delete(@PathVariable int id) {
    InMemoryTask task = tasks.stream().filter(t -> t.id() == id).findAny().orElse(null);
    tasks.remove(task);
    return task;
  }
}
