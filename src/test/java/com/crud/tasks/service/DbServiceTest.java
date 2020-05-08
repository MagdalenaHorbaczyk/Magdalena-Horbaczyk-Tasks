package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //when
        dbService.getAllTasks();
        //then
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void getTaskTest() {
        //given
        Task task = new Task(100L, "title", "content");
        //when
        dbService.getTask(task.getId());
        //then
        verify(taskRepository, times(1)).findById(100L);
    }

    @Test
    public void saveTaskTest() {
        //given
        Task task = new Task(100L, "title", "content");
        //when
        dbService.saveTask(task);
        //then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void deleteTaskTest() {
        //given
        Task task = new Task(100L, "title", "content");
        //when
        dbService.deleteTask(task.getId());
        //then
        verify(taskRepository, times(1)).deleteById(100L);
    }
}
