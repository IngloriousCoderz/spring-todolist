package it.ingloriouscoderz.todolist.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.ingloriouscoderz.todolist.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

}
