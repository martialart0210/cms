package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.GameLogNotFoundException;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.OperationLog;
import com.mac.m2land_cms.model.response.OperationLogResponse;
import com.mac.m2land_cms.repository.OperationLogRepository;
import com.mac.m2land_cms.service.inf.OperationLogService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The class OperationLogServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogRepository repository;
    private final MessageUtils messageUtils;
    private final ModelMapper modelMapper;
    private final PageUtils pageUtils;

    public OperationLogServiceImpl(OperationLogRepository repository, MessageUtils messageUtils, ModelMapper modelMapper, PageUtils pageUtils) {
        this.repository = repository;
        this.messageUtils = messageUtils;
        this.modelMapper = modelMapper;
        this.pageUtils = pageUtils;
    }


    /**
     *
     * @param userId
     * @return PageResDto<OperationLogResponse>
     */
    @Override
    public PageResDto<OperationLogResponse> findByUserId(Pageable pageable, Long userId) {
        Page<OperationLog> operationLogs = repository.findByUserId(userId,pageable);
        if (operationLogs.isEmpty()){
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER046, null));
            throw new GameLogNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER046, null));
        }
        return pageUtils.convertPageEntityToDTO(operationLogs, OperationLogResponse.class);
    }
}
