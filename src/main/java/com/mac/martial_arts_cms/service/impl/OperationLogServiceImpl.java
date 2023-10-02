package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.GameLogNotFoundException;
import com.mac.martial_arts_cms.model.entity.OperationLog;
import com.mac.martial_arts_cms.model.response.OperationLogResponse;
import com.mac.martial_arts_cms.repository.OperationLogRepository;
import com.mac.martial_arts_cms.service.inf.OperationLogService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class OperationLogServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogRepository repository;

    @Autowired
    private MessageUtils messageUtils;


    /**
     *
     * @param userId
     * @return List<OperationLogResponse>
     */
    @Override
    public List<OperationLogResponse> findByUserId(Long userId) {
        List<OperationLog> gameLogs = repository.findByUserId(userId);
        if (gameLogs.isEmpty()){
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER046, null));
            throw new GameLogNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER046, null));
        }
        return gameLogs.stream()
                .map(this::mapToGameLogResponse)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param operationLog
     * @return OperationLogResponse
     */
    private OperationLogResponse mapToGameLogResponse(OperationLog operationLog){
        LocalDateTime timestamp = operationLog.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        return new OperationLogResponse(operationLog.getLabel(), formattedTimestamp, operationLog.getDetail());
    }
}
