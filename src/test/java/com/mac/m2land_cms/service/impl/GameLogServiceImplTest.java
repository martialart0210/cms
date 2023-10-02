package com.mac.m2land_cms.service.impl;


import com.mac.m2land_cms.Exceptions.GameLogNotFoundException;
import com.mac.m2land_cms.model.entity.GameLog;
import com.mac.m2land_cms.model.response.GameLogResponse;
import com.mac.m2land_cms.repository.GameLogRepository;
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


/**
 * The Class GameLogServiceImplTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class GameLogServiceImplTest {

    @Mock
    private GameLogRepository repository;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PageUtils pageUtils;

    @InjectMocks
    private GameLogServiceImpl gameLogService;

    @Before
    public void setUp() {
        when(messageUtils.getMessage(any(), any())).thenReturn("Error message");
    }

    @Test
    public void testFindByUserId_WithValidUserId_ShouldReturnListGameLogResponse() {
        Long userId = 123L;
        List<GameLog> mockGameLogs = new ArrayList<>();
        mockGameLogs.add(new GameLog(1L,"Label1",LocalDateTime.now(), "detail1","color1",1L));
        mockGameLogs.add(new GameLog(2L,"Label2",LocalDateTime.now(), "detail2","color2",2L));
        GameLog gameLog = new GameLog(1L,"Label1",LocalDateTime.now(), "detail1","color1",1L);
        GameLogResponse response = new GameLogResponse("Label2",LocalDateTime.now(), "detail2","color2");
        Page<GameLog> mockPage = new PageImpl<>(mockGameLogs);

        when(repository.findByUserId(eq(userId),any(Pageable.class))).thenReturn(mockPage);
        when(modelMapper.map(gameLog,GameLogResponse.class)).thenReturn(response);
        gameLogService.findByUserId(userId, PageRequest.of(0, 10));

        verify(repository, times(1)).findByUserId(userId, PageRequest.of(0, 10));

    }

    @Test(expected = GameLogNotFoundException.class)
    public void testFindByUserId_WithInvalidUserId_ShouldThrowGameLogNotFoundException() {
        Long userId = 123L;
        List<GameLog> mockGameLogs = new ArrayList<>();
        Page<GameLog> mockPage = new PageImpl<>(mockGameLogs);
        when(repository.findByUserId(eq(userId),any(Pageable.class))).thenReturn(mockPage);

        gameLogService.findByUserId(userId,PageRequest.of(0, 10));

    }
}
