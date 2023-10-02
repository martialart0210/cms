package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.SuspensionException;
import com.mac.m2land_cms.Exceptions.UserNotFoundException;
import com.mac.m2land_cms.model.entity.PushNotification;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.request.PushNotificationRequest;
import com.mac.m2land_cms.model.response.PushNotificationResponse;
import com.mac.m2land_cms.repository.PushNotificationRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class PushNotificationImplTest {

    @Mock
    private PushNotificationRepository pushNotificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PushNotificationServiceImpl pushNotificationService;


    @Before
    public void setUp() {
        when(messageUtils.getMessage(any(), any())).thenReturn("Error message");
    }

    @Test
    public void registerSuccess(){

        Long userId = 1L;
        LocalDateTime noticeCreate = LocalDateTime.now();
        LocalDateTime noticeStart = LocalDateTime.now();
        LocalDateTime noticeEnd = LocalDateTime.now().plusDays(1);
        LocalDateTime noticeTimeRemind = LocalDateTime.now();

        PushNotificationRequest request = new PushNotificationRequest();
        request.setUserId(userId);
        request.setNotificationCreate(noticeCreate);
        request.setNotificationMessage("Test Push Notification");
        request.setNotificationStart(noticeStart);
        request.setNotificationEnd(noticeEnd);
        request.setTimeRemind(noticeTimeRemind);
        request.setNotificationCount(1L);

        PushNotification notice = new PushNotification();
        notice.setUserId(userId);
        notice.setNotificationCreate(noticeCreate);
        notice.setNotificationMessage("Test Push Notification");
        notice.setNotificationStart(noticeStart);
        notice.setNotificationEnd(noticeEnd);
        notice.setTimeRemind(noticeTimeRemind);
        notice.setNotificationCount(1L);

        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(modelMapper.map(request, PushNotification.class)).thenReturn(notice);

        PushNotificationResponse expectedResponse = new PushNotificationResponse(
                "Test Push Notification",
                noticeCreate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                noticeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                noticeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                noticeTimeRemind.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                1L
        );

        PushNotificationResponse result = pushNotificationService.register(request);

        assertEquals(expectedResponse, result);
    }


    @Test(expected = UserNotFoundException.class)
    public void testRegister_WithInvalidUserId_ShouldThrowUserNotFoundException() {
        Long userId = 1L;
        LocalDateTime noticeStart = LocalDateTime.now();
        LocalDateTime noticeEnd = LocalDateTime.now().minusDays(1);

        PushNotificationRequest request = new PushNotificationRequest();
        request.setUserId(userId);
        request.setNotificationMessage("Test Push Notification");
        request.setNotificationStart(noticeStart);
        request.setNotificationEnd(noticeEnd);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        pushNotificationService.register(request);
    }


    @Test(expected = SuspensionException.class)
    public void testRegister_WithInvalidUserId_ShouldThrowSuspensionException(){
        Long userId = 1L;
        LocalDateTime noticeStart = LocalDateTime.now().plusDays(1);
        LocalDateTime noticeEnd = LocalDateTime.now();
        LocalDateTime noticeCreate = LocalDateTime.now().plusDays(1);
        LocalDateTime noticeTimeRemind = LocalDateTime.now();

        PushNotificationRequest request = new PushNotificationRequest();
        request.setUserId(userId);
        request.setNotificationMessage("Test Push Notification");
        request.setNotificationStart(noticeStart);
        request.setNotificationEnd(noticeEnd);

        PushNotification notice = new PushNotification();
        notice.setUserId(userId);
        notice.setNotificationCreate(noticeCreate);
        notice.setNotificationMessage("Test Push Notification");
        notice.setNotificationStart(noticeStart);
        notice.setNotificationEnd(noticeEnd);
        notice.setTimeRemind(noticeTimeRemind);
        notice.setNotificationCount(1L);

        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(modelMapper.map(request, PushNotification.class)).thenReturn(notice);

        pushNotificationService.register(request);
    }
}
