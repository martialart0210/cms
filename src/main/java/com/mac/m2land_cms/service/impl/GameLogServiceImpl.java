package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.GameLogNotFoundException;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.GameLog;
import com.mac.m2land_cms.model.response.GameLogResponse;
import com.mac.m2land_cms.repository.GameLogRepository;
import com.mac.m2land_cms.service.inf.GameLogService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The class GameLogServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class GameLogServiceImpl implements GameLogService {

    private final GameLogRepository repository;
    private final MessageUtils messageUtils;
    private final ModelMapper modelMapper;
    private final PageUtils pageUtils;

    public GameLogServiceImpl(GameLogRepository repository, MessageUtils messageUtils, ModelMapper modelMapper, PageUtils pageUtils) {
        this.repository = repository;
        this.messageUtils = messageUtils;
        this.modelMapper = modelMapper;
        this.pageUtils = pageUtils;
    }


    /**
     *
     * @param userId
     * @return PageResDto<GameLogResponse>
     */
    @Override
    public PageResDto<GameLogResponse> findByUserId(Long userId, Pageable pageable) {
        Page<GameLog> gameLogs = repository.findByUserId(userId,pageable);
        if (gameLogs.isEmpty()){
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER045, null));
            throw new GameLogNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER045, null));
        }
        return pageUtils.convertPageEntityToDTO(gameLogs,GameLogResponse.class);
    }

}
