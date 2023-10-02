package com.mac.m2land_cms.controller;

import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.dto.VideoDto;
import com.mac.m2land_cms.service.inf.VideoService;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The Class VideoControllerTest.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class VideoControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private VideoController videoController;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private VideoService videoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }

    @Test
    public void getAll() throws Exception {
        Pageable sizePagination = PageRequest.of(0, 5);
        PageResDto<VideoDto> responses = new PageResDto<>();
        when(videoService.findAll(sizePagination)).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/video/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("size","5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(videoService,times(1)).findAll(sizePagination);
        verifyNoMoreInteractions(videoService);
    }

    @Test
    public void addVideo() throws Exception {
        String link = "https://www.youtube.com/watch?v=DISPAJ3Hbks%26list=PLmAVoNqyB8jDOJYq5VlSLJTTHyAWgS7Op";
        mockMvc.perform(MockMvcRequestBuilders.post("/video/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("link", link))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(videoService,times(1)).addVideo(link);
        verifyNoMoreInteractions(videoService);
    }

    @Test
    public void deleteVideo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/video/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(videoService,times(1)).deleteVideo(1l);
        verifyNoMoreInteractions(videoService);
    }
}
