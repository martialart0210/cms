package com.mac.m2land_cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.request.SuspensionRequest;
import com.mac.m2land_cms.model.response.OperationLogResponse;
import com.mac.m2land_cms.model.response.UserAccountResponse;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.service.inf.GameLogService;
import com.mac.m2land_cms.service.inf.OperationLogService;
import com.mac.m2land_cms.service.inf.UserService;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The Class UserControllerTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserControllerTest implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime>{

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private GameLogService gameLogService;

    @Mock
    private OperationLogService operationLogService;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

//    @Test
//    public void testSearchAccountUser() throws Exception {
//        PageResDto<UserResponse> mockResponse = new ArrayList<>();
//        when(userService.searchUserAccount(anyString(), anyString(), any())).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/searchAccountUser")
//                        .param("searchCondition", "condition")
//                        .param("searchWord", "word"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").value(mockResponse));
//
//        Pageable sizePagination = PageRequest.of(0, 2);
//        verify(userService, times(1)).searchUserAccount("condition", "word", sizePagination);
//        verifyNoMoreInteractions(userService);
//    }

    @Test
    public void testReleaseSuspension() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users/releaseSuspension")
                        .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).releaseSuspension("username");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testSuspension() throws Exception {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        LocalDateTime suspensionStart = LocalDateTime.of(2023, 6, 1, 10, 21, 56);
        LocalDateTime suspensionEnd = LocalDateTime.of(2023, 6, 30, 10, 21, 56);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .create();
        String content = gson.toJson(new SuspensionRequest(1L, suspensionStart, suspensionEnd, "reason"));
        mockMvc.perform(MockMvcRequestBuilders.put("/users/suspension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userService, times(1)).UpdateSuspension(1L, suspensionStart, suspensionEnd, "reason");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserAccountInfo() throws Exception {
        UserAccountResponse mockResponse = new UserAccountResponse();
        when(userService.getUserAccountInfo(anyString())).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/getUserAccountInfo")
                        .param("username", "username"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(mockResponse));

        verify(userService, times(1)).getUserAccountInfo("username");
        verifyNoMoreInteractions(userService);
    }

//    @Test
//    public void testGetGameLogByUserId() throws Exception {
//        List<GameLogResponse> mockResponse = new ArrayList<>();
//        when(gameLogService.findByUserId(anyLong())).thenReturn(mockResponse);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/gameLog")
//                        .param("userId", "1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").value(mockResponse));
//
//        verify(gameLogService, times(1)).findByUserId(1L);
//        verifyNoMoreInteractions(gameLogService);
//    }

    @Test
    public void testGetOperationLogByUserId() throws Exception {
        List<OperationLogResponse> mockResponses = new ArrayList<>();
        mockResponses.add(new OperationLogResponse("label",LocalDateTime.now(),"detail","color"));

        PageResDto<OperationLogResponse> mockResponse = new PageResDto<>();
        mockResponse.setContent(mockResponses);
        when(operationLogService.findByUserId(any(Pageable.class),anyLong())).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/operationLog")
                        .param("userId", "1")
                        .param("page","0")
                        .param("size","3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(operationLogService, times(1)).findByUserId(any(Pageable.class),anyLong());
    }

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String formatted = jsonElement.getAsString();
        return LocalDateTime.parse(formatted, formatter);
    }

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        String formatted = localDateTime.format(formatter);
        return new JsonPrimitive(formatted);
    }
}
