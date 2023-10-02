package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.GameLogNotFoundException;
import com.mac.martial_arts_cms.model.entity.GameLog;
import com.mac.martial_arts_cms.model.response.GameLogResponse;
import com.mac.martial_arts_cms.repository.GameLogRepository;
import com.mac.martial_arts_cms.service.inf.GameLogService;
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
 * The class GameLogServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class GameLogServiceImpl implements GameLogService {

    @Autowired
    private GameLogRepository repository;

    @Autowired
    private MessageUtils messageUtils;


    /**
     *
     * @param userId
     * @return List<GameLogResponse>
     */
    @Override
    public List<GameLogResponse> findByUserId(Long userId) {
        List<GameLog> gameLogs = repository.findByUserId(userId);
        if (gameLogs.isEmpty()){
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER045, null));
            throw new GameLogNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER045, null));
        }
        return gameLogs.stream()
                .map(this::mapToGameLogResponse)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param gameLog
     * @return GameLogResponse
     */
    private GameLogResponse mapToGameLogResponse(GameLog gameLog){
        LocalDateTime timestamp = gameLog.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        return new GameLogResponse(gameLog.getLabel(), formattedTimestamp, gameLog.getDetail());
    }
}
