package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.dto.DojoDto;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.service.inf.DojoService;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * The Class DojoController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class DojoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private DojoController dojoController;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private DojoService dojoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dojoController).build();
    }

    @Test
    public void getAll() throws Exception {
        Pageable sizePagination = PageRequest.of(0, 5);
        PageResDto<DojoDto> responses = new PageResDto<>();
        when(dojoService.findAll(sizePagination)).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/dojo/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("size","5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(dojoService,times(1)).findAll(sizePagination);
        verifyNoMoreInteractions(dojoService);
    }
}
