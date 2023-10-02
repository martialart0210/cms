package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.UserNotFoundException;
import com.mac.martial_arts_cms.model.entity.Notice;
import com.mac.martial_arts_cms.model.request.NoticeRegisterRequest;
import com.mac.martial_arts_cms.model.response.NoticeRegisterResponse;
import com.mac.martial_arts_cms.repository.NoticeRepository;
import com.mac.martial_arts_cms.repository.UserRepository;
import com.mac.martial_arts_cms.service.inf.NoticeService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;


/**
 * The Class NoticeServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageUtils messageUtils;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param request
     * @return NoticeRegisterRequest
     */
    @Override
    public NoticeRegisterResponse register(NoticeRegisterRequest request) {
        Notice notice = modelMapper.map(request, Notice.class);
        userRepository.findById(request.getUserId()).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
            return new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER037, null));
        });
        if (notice.getNoticeStart().isAfter(notice.getNoticeEnd())) {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER044, null));
            throw new UserNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER044, null));
        }
        repository.save(notice);
        return getNoticeRegisterResponse(notice);
    }

    /**
     *
     * @param request
     * @return NoticeRegisterResponse
     */
    public NoticeRegisterResponse getNoticeRegisterResponse(Notice request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreate = request.getNoticeCreate().format(formatter);
        String formattedStart = request.getNoticeStart().format(formatter);
        String formattedEnd = request.getNoticeEnd().format(formatter);
        String formatTimeRemind = request.getTimeRemind().format(formatter);
        return new NoticeRegisterResponse(request.getNoticeMessage(), formattedCreate, formattedStart, formattedEnd, formatTimeRemind, request.getNoticeCount());
    }
}
