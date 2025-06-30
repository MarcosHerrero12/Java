package com.tasks.Persistence;

import com.tasks.Request.TaskRequest;
import com.tasks.Response.TaskResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TasksMapper {

    @Select("SELECT * FROM tasks")
    List<TaskResponse> getAllTasks();

    @Select("SELECT * FROM tasks WHERE id = '${id}'")
    TaskResponse getTaskById(Long id);

    @Insert("Insert INTO tasks (title, description, creation_date, due_date, status) " +
    "VALUES (#{title}, #{descripcion}, NOW(), #{dueDate}, #{status})")
    void createTask(TaskRequest taskRequest);

    @Update("<script>" +
            "UPDATE tasks" +
            "<set>" +
            "<if test='descripcion != null'>description = #{descripcion}, </if>" +
            "<if test='status != null'>status = #{status}, </if>" +
            "<if test='dueDate != null'>due_date = #{dueDate}, </if>" +
            "<if test='title != null'>title = #{title}, </if>" +
            "</set>" +
            "WHERE title = #{title}" +
            "</script>")
    void updateTask(TaskRequest taskRequest);

    @Delete("DELETE FROM tasks WHERE title = #{title} ")
    void deleteTask(TaskRequest taskRequest);
}
