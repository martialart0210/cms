package com.mac.m2land_cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.mac.m2land_cms.model.entity.UserCharacter;
import com.mac.m2land_cms.model.request.UserCharacterRequest;
import com.mac.m2land_cms.model.response.UserCharacterResponse;
import com.mac.m2land_cms.repository.UserCharacterRepository;
import com.mac.m2land_cms.service.inf.UserCharacterService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The Class UserCharacterControllerTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserCharacterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserCharacterService userCharacterService;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private UserCharacterRepository repository;

    @InjectMocks
    private UserCharacterController userCharacterController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userCharacterController).build();
    }

    @Test
    public void figureInfoTest() throws Exception {
        UserCharacterResponse response = new UserCharacterResponse();
        when(userCharacterService.getUserCharacterInfo(anyLong())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/userCharacter")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(response));
        verify(userCharacterService, times(1)).getUserCharacterInfo(1L);
        verifyNoMoreInteractions(userCharacterService);
    }


    @Test
    public void updateFigureTest() throws Exception {
        UserCharacter userCharacter = new UserCharacter();
        when(repository.findByUserId(anyLong())).thenReturn(Optional.of(userCharacter));
        UserCharacterRequest request = new UserCharacterRequest(1L,"characterName",3000L,"guildName",1L);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .create();
        String content = gson.toJson(request);
        mockMvc.perform(MockMvcRequestBuilders.put("/userCharacter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userCharacterService,times(1)).updateUserCharacter(request);
        verifyNoMoreInteractions(userCharacterService);
    }
}
