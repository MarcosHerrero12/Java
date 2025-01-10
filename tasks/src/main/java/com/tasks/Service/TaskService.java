package com.tasks.Service;

import com.tasks.Persistence.TasksMapper;
import com.tasks.Response.TaskResponse;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TaskService {

    private final TasksMapper mapper;

    @Autowired
    public TaskService(TasksMapper mapper){this.mapper = mapper;};

    public Optional<List<TaskResponse>> getAllTasks(){
        Optional<List<TaskResponse>> taskResponses = Optional.of(mapper.getAllTasks());
        return taskResponses;
    }
}
