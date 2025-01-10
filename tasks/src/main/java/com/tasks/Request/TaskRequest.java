package com.tasks.Request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String descripcion;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private String status;
}
