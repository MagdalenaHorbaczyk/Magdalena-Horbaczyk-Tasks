package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @MockBean
    DbService dbService;
    @MockBean
    TaskMapper taskMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(100L, "task", "description");
        taskList.add(task);
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(100L, "Dto Task", "DTO description");
        taskDtoList.add(taskDto);
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(100)))
                .andExpect(jsonPath("$[0].title", is("Dto Task")))
                .andExpect(jsonPath("$[0].content", is("DTO description")));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task = new Task(100L, "title", "content");
        TaskDto taskDto = new TaskDto(100L, "Task Dto title", "Task Dto content");
        when(dbService.getTask(task.getId())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?id=100")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.title", is("Task Dto title")))
                .andExpect(jsonPath("$.content", is("Task Dto content")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(100L, "task", "description");
        TaskDto taskDto = new TaskDto(100L, "Dto Task", "DTO description");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.title", is("Dto Task")))
                .andExpect(jsonPath("$.content", is("DTO description")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(100L, "task", "description");
        TaskDto taskDto = new TaskDto(100L, "Dto Task", "DTO description");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}