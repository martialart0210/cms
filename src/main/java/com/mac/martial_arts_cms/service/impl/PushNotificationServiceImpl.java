package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.UserNotFoundException;
import com.mac.martial_arts_cms.model.entity.Notice;
import com.mac.martial_arts_cms.model.entity.PushNotification;
import com.mac.martial_arts_cms.model.request.PushNotificationRequest;
import com.mac.martial_arts_cms.model.response.NoticeRegisterResponse;
import com.mac.martial_arts_cms.model.response.PushNotificationResponse;
import com.mac.martial_arts_cms.repository.PushNotificationRepository;
import com.mac.martial_arts_cms.repository.UserRepository;
import com.mac.martial_arts_cms.service.inf.PushNotificationService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * The Class PushNotificationServiceImpl.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Service
@Slf4j
public class PushNotificationServiceImpl implements PushNotificationService {
    private PushNotificationRepository pushNotificationRepository;

    private UserRepository userRepository;

    private MessageUtils messageUtils;

    private ModelMapper modelMapper;

    @Autowired
    public PushNotificationServiceImpl(PushNotificationRepository pushNotificationRepository, UserRepository userRepository, MessageUtils messageUtils, ModelMapper modelMapper) {
        this.pushNotificationRepository = pushNotificationRepository;
        this.userRepository = userRepository;
        this.messageUtils = messageUtils;
        this.modelMapper = modelMapper;
    }

    /**
     * register
     * @param request
     * @return PushNotificationResponse
     */
    @Override
    public PushNotificationResponse register(PushNotificationRequest request) {
        PushNotification notification = modelMapper.map(request, PushNotification.class);
        userRepository.findById(request.getUserId()).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            return new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        });
        if (notification.getNotificationStart().isAfter(notification.getNotificationEnd())) {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER044, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER044, null));
        }
        pushNotificationRepository.save(notification);
        return getPushNotificationResponse(notification);
    }

    /**
     * getPushNotificationResponse
     * @param request
     * @return PushNotificationResponse
     */
    public PushNotificationResponse getPushNotificationResponse(PushNotification request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreate = request.getNotificationCreate().format(formatter);
        String formattedStart = request.getNotificationStart().format(formatter);
        String formattedEnd = request.getNotificationEnd().format(formatter);
        String formatTimeRemind = request.getTimeRemind().format(formatter);
        return new PushNotificationResponse(request.getNotificationMessage(), formattedCreate, formattedStart, formattedEnd, formatTimeRemind, request.getNotificationCount());
    }
}
