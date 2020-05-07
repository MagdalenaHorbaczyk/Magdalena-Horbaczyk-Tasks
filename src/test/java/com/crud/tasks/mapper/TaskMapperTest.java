package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //given
        TaskDto taskDto = new TaskDto(100L, "title", "content");
        Task task = new Task(100L, "title", "content");
        //when
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //then
        assertEquals(task.getId(), mappedTask.getId());
        assertEquals(task.getTitle(), mappedTask.getTitle());
        assertEquals(task.getContent(), mappedTask.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //given
        Task task = new Task(100L, "title", "content");
        TaskDto taskDto = new TaskDto(100L, "title", "content");
        //when
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //then
        assertEquals(taskDto.getId(), mappedTaskDto.getId());
        assertEquals(taskDto.getTitle(), mappedTaskDto.getTitle());
        assertEquals(taskDto.getContent(), mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        Task task1 = new Task(100L, "title", "content");
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(task1);
        //when
        List<TaskDto> taskListsDto = taskMapper.mapToTaskDtoList(tasksList);
        //then
        Assert.assertEquals(1, taskListsDto.size());
        Assert.assertEquals(tasksList.get(0).getTitle(), taskListsDto.get(0).getTitle());
    }
}
