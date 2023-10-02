package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.GameLogNotFoundException;
import com.mac.m2land_cms.model.entity.OperationLog;
import com.mac.m2land_cms.model.response.OperationLogResponse;
import com.mac.m2land_cms.repository.OperationLogRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


/**
 * The Class OperationLogServiceImplTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class OperationLogServiceImplTest {

    @Mock
    private OperationLogRepository operationLogRepository;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PageUtils pageUtils;

    @InjectMocks
    private OperationLogServiceImpl operationLogService;

    @Before
    public void setUp(){
        when(messageUtils.getMessage(any(),any())).thenReturn("Error message");
    }

    @Test
    public void findByUserIdSuccess(){
        Long idUser = 123L;

        List<OperationLog> listOperator = new ArrayList<>();
        listOperator.add(new OperationLog(1L, "label", LocalDateTime.now(), "detail", "color", 1L));
        listOperator.add(new OperationLog(2L, "label2", LocalDateTime.now(), "detail2", "color2", 2L));
        OperationLogResponse response = new OperationLogResponse("label", LocalDateTime.now(), "detail", "color");

        Page<OperationLog> mockPage = new PageImpl<>(listOperator);
        when(operationLogRepository.findByUserId(eq(idUser), any(Pageable.class))).thenReturn(mockPage);
        when(modelMapper.map(any(OperationLog.class), eq(OperationLogResponse.class))).thenReturn(response);

        operationLogService.findByUserId(PageRequest.of(0, 10), idUser);
        verify(operationLogRepository, times(1)).findByUserId(idUser, PageRequest.of(0, 10));
    }

    @Test(expected = GameLogNotFoundException.class)
    public void findByUserIdIsEmpty(){
        Long idUser = 123L;
        List<OperationLog> listOperator = new ArrayList<>();
        Page<OperationLog> mockPage = new PageImpl<>(listOperator);
        when(operationLogRepository.findByUserId(eq(idUser), any(Pageable.class))).thenReturn(mockPage);

        operationLogService.findByUserId(PageRequest.of(0, 10), idUser);
    }


}
