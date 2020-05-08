package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    AdminConfig adminConfig;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task", "description", "pos", "id");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "one", "task", "http://test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("one", createdTrelloCardDto.getId());
        assertEquals("task", createdTrelloCardDto.getName());
        assertEquals("http://test.com", createdTrelloCardDto.getShortUrl());
    }
    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("one", "board", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> fetchedTrelloBoardsList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, fetchedTrelloBoardsList.size());
    }
}
