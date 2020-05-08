package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void trelloConfigTest() {
        assertEquals("magdalenahorbaczyk", trelloConfig.getTrelloUsername());
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
        assertEquals("52075275b02ac8955b5da8197b6b8f2e", trelloConfig.getTrelloAppKey());
        assertEquals("d88a355d4a5728c75a9feefde545c351c0a83e0ff994c4380cb048f010ca2c3f", trelloConfig.getTrelloToken());
    }
}
