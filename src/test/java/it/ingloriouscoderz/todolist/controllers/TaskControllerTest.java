package it.ingloriouscoderz.todolist.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.ingloriouscoderz.todolist.model.Task;
import it.ingloriouscoderz.todolist.services.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
  @Mock
  TaskRepository repo;

  @InjectMocks
  TaskController controller;

  private List<Task> expectedTasks;

  TaskControllerTest() {
    expectedTasks = new ArrayList<Task>();
    expectedTasks.add(new Task("1", "Learn Spring", true));
    expectedTasks.add(new Task("2", "Do interview", false));
    expectedTasks.add(new Task("3", "Forget everything", false));
  }

  @Test
  public void testGetTasks() {
    when(repo.findAll()).thenReturn(expectedTasks);

    List<Task> tasks = controller.getTasks();

    assertEquals(expectedTasks, tasks);
  }
}
