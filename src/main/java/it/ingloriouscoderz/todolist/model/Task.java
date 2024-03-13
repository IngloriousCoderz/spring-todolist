package it.ingloriouscoderz.todolist.model;

public record Task(int id, String text, boolean completed) {}

// public class Task {
//   private int id;
//   private String text;
//   private boolean isCompleted;

//   public Task(int id, String text, boolean isCompleted) {
//     this.id = id;
//     this.text = text;
//     this.isCompleted = isCompleted;
//   }

//   public int getId() {
//     return id;
//   }
  
//   public void setId(int id) {
//     this.id = id;
//   }
  
//   public String getText() {
//     return text;
//   }
  
//   public void setText(String text) {
//     this.text = text;
//   }
  
//   public boolean isCompleted() {
//     return isCompleted;
//   }
  
//   public void setCompleted(boolean isCompleted) {
//     this.isCompleted = isCompleted;
//   }
// }
