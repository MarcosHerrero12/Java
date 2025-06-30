package com.tasks.Controller;

import com.tasks.Request.TaskRequest;
import com.tasks.Response.GenericResponse;
import com.tasks.Response.TaskResponse;
import com.tasks.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("getAllTasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        Optional<List<TaskResponse>> taskResponses = service.getAllTasks();
        return ResponseEntity.ok().body(taskResponses.get());
    }

    @Operation(summary = "Obtener una tarea por el id", description = "Devuelve la tarea con el id dado")
    @PostMapping("/getById")
    public ResponseEntity<TaskResponse> getTaskById(Long id) {
        Optional<TaskResponse> getTaskById = service.getTaskById(id);
        return ResponseEntity.ok().body(getTaskById.get());
    }

    @Operation(summary = "Crear una nueva Tarea")
    @PostMapping("createTask")
    public ResponseEntity<GenericResponse> createTask(@RequestBody TaskRequest taskRequest) {
        GenericResponse createTaskResponse = service.createTask(taskRequest);
        return ResponseEntity.ok().body(createTaskResponse);
    }

    @Operation(summary = "Actualizar una tarea")
    @PostMapping("updateTask")
    public ResponseEntity<GenericResponse> updateTask(@RequestBody TaskRequest taskRequest) {
        GenericResponse uodateTaskResponse = service.updateTask(taskRequest);
        return ResponseEntity.ok().body(uodateTaskResponse);
    }

    @Operation(summary = "Eliminar una tarea")
    @PostMapping("deleteteTask")
    public ResponseEntity<GenericResponse> deleteTask(@RequestBody TaskRequest taskRequest) {
        GenericResponse uodateTaskResponse = service.deleteTask(taskRequest);
        return ResponseEntity.ok().body(uodateTaskResponse);

    }
}