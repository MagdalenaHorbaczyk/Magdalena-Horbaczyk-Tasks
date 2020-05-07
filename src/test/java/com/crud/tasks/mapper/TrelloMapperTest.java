package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("one", "ListOne", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("BoardOne", "one", trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardsDto);

        //then
        assertEquals(1, trelloBoards.size());
        Assert.assertEquals(trelloBoardsDto.get(0).getId(), trelloBoards.get(0).getId());
        Assert.assertEquals(trelloBoardsDto.get(0).getName(), trelloBoards.get(0).getName());
        Assert.assertEquals(trelloBoardsDto.get(0).getLists().get(0).getId(),
                trelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloBoardsDto.get(0).getLists().get(0).getName(),
                trelloBoards.get(0).getLists().get(0).getName());
        Assert.assertEquals(trelloBoardsDto.get(0).getLists().get(0).isClosed(),
                trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardDto() {
        //given
        TrelloList trelloList = new TrelloList("one", "ListOne", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("one", "one", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //when
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardDto(trelloBoards);

        //then
        assertEquals(1, trelloBoardsDto.size());
        Assert.assertEquals(trelloBoards.get(0).getId(), trelloBoardsDto.get(0).getId());
        Assert.assertEquals(trelloBoards.get(0).getName(), trelloBoardsDto.get(0).getName());
        Assert.assertEquals(trelloBoards.get(0).getLists().get(0).getId(),
                trelloBoardsDto.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloBoards.get(0).getLists().get(0).getName(),
                trelloBoardsDto.get(0).getLists().get(0).getName());
        Assert.assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(),
                trelloBoardsDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToList() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("one", "ListOne", true));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //then
        assertEquals(1, trelloLists.size());
        Assert.assertEquals(trelloListsDto.get(0).getId(), trelloLists.get(0).getId());
        Assert.assertEquals(trelloListsDto.get(0).getName(), trelloLists.get(0).getName());
        Assert.assertEquals(trelloListsDto.get(0).isClosed(), trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToListDto() {
        //given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("one", "ListOne", true));

        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //then
        assertEquals(1, trelloListsDto.size());
        Assert.assertEquals(trelloLists.get(0).getId(), trelloListsDto.get(0).getId());
        Assert.assertEquals(trelloLists.get(0).getName(), trelloListsDto.get(0).getName());
        Assert.assertEquals(trelloLists.get(0).isClosed(), trelloListsDto.get(0).isClosed());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "description", "pos", "one");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "description", "pos", "one");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), trelloCardDto.getName());
        Assert.assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        Assert.assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        Assert.assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }
}
