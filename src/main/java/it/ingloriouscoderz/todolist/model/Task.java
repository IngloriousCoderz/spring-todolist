package it.ingloriouscoderz.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tasks")
public record Task(@Id String id, String text, Boolean completed) {
}
