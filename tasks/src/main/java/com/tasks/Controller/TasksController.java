package com.tasks.Controller;

import com.tasks.Response.TaskResponse;
import com.tasks.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@Tag(name = "Tareas", description = "API para la gestión de tareas")
public class TasksController {
    @Autowired
    private TaskService service;

    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista de todas las tareas")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        Optional<List<TaskResponse>> taskResponses = service.getAllTasks();
        return ResponseEntity.ok().body(taskResponses.get());
    }
}