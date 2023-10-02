package com.mac.m2land_cms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.mac.m2land_cms.model.request.PushNotificationRequest;
import com.mac.m2land_cms.service.inf.PushNotificationService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * The Class PushNotificationControllerTest.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class PushNotificationControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PushNotificationController pushNotificationController;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private PushNotificationService pushNotificationService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pushNotificationController).build();
    }

    @Test
    public void registerNotification() throws Exception {
        LocalDateTime noticeCreate = LocalDateTime.of(2023, 6, 1, 10, 21, 56);
        LocalDateTime noticeStart = LocalDateTime.of(2023, 6, 30, 10, 21, 56);
        LocalDateTime noticeEnd = LocalDateTime.of(2023, 6, 30, 10, 21, 56);
        LocalDateTime timeRemind = LocalDateTime.of(2023, 6, 30, 10, 21, 56);
        PushNotificationRequest request = new PushNotificationRequest("noticeMessage", noticeCreate, noticeStart, noticeEnd, timeRemind, 1L, 1L);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        context.serialize(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .create();
        String content = gson.toJson(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/push-notification/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(pushNotificationService, times(1)).register(request);
        verifyNoMoreInteractions(pushNotificationService);

    }
}
