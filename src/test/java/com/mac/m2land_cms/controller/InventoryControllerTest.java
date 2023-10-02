package com.mac.m2land_cms.controller;


import com.mac.m2land_cms.model.response.InventoryResponse;
import com.mac.m2land_cms.service.inf.InventoryService;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The Class InventoryControllerTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class InventoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private MessageUtils messageUtils;

    @InjectMocks
    private InventoryController inventoryController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
    }

    @Test
    public void testGetInventoryByUserId() throws Exception {
        List<InventoryResponse> responses = new ArrayList<>();
        when(inventoryService.getInventoryByUserId(anyLong())).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/inventory/getInventoryByUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .param("idUser","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(inventoryService,times(1)).getInventoryByUserId(1L);
        verifyNoMoreInteractions(inventoryService);
    }
}
