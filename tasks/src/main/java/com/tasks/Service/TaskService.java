package com.tasks.Service;

import com.tasks.Persistence.TasksMapper;
import com.tasks.Request.TaskRequest;
import com.tasks.Response.GenericResponse;
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

    public Optional<TaskResponse> getTaskById(Long id){
        Optional<TaskResponse> taskResponse = Optional.of(mapper.getTaskById(id));
        return taskResponse;
    }

    public GenericResponse createTask(TaskRequest request){
        GenericResponse response = new GenericResponse();
        try {
            mapper.createTask(request);
            response.setCode(0);
        }catch (Exception e){
            response.setCode(-1);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    public GenericResponse updateTask(TaskRequest taskRequest){
        GenericResponse response = new GenericResponse();
        try {
            mapper.updateTask(taskRequest);
            response.setCode(0);
        }catch (Exception e){
            response.setCode(-1);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public GenericResponse deleteTask (TaskRequest taskRequest){
        GenericResponse response = new GenericResponse();
        try {
            mapper.updateTask(taskRequest);
            response.setCode(0);
        }catch (Exception e){
            response.setCode(-1);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
