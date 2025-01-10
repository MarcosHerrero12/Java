package com.tasks.Persistence;

import com.tasks.Response.TaskResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TasksMapper {

    @Select("SELECT * FROM tasks")
    List<TaskResponse> getAllTasks();
}
