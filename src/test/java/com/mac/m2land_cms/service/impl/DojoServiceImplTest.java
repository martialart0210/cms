package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.model.dto.ContentPageDto;
import com.mac.m2land_cms.model.dto.DojoDto;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.Dojo;
import com.mac.m2land_cms.repository.DojoRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mac.m2land_cms.model.enum_class.DojoStatus.IN_OPERATION;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * The Class DojoController.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class DojoServiceImplTest {

    @Mock
    private DojoRepository repository;

    @Mock
    private MessageUtils messageUtils;

    @Autowired
    private PageUtils pageUtils;

    @Mock
    private PageUtils pageUtilsMock;

    @InjectMocks
    private DojoServiceImpl dojoService;

    @Before
    public void setUp() {
        when(messageUtils.getMessage(any(), any())).thenReturn("Error message");
    }

    @Test
    public void findAll() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 6, 1, 10, 21, 56);

        //Actual
        Pageable sizePagination = PageRequest.of(0, 5);
        Dojo dojo = new Dojo();
        dojo.setId(1l);
        dojo.setDojoName("an");
        dojo.setCreatedAt(dateTime);
        dojo.setStatus(IN_OPERATION);
        List<Dojo> dojoList = new ArrayList<>();
        dojoList.add(dojo);
        Page<Dojo> dojoPageActual = new PageImpl<>(dojoList, sizePagination, dojoList.size());
        when(repository.findAll(sizePagination)).thenReturn(dojoPageActual);
        PageResDto<DojoDto> pageResDtoActual = pageUtils.convertPageEntityToDTO(dojoPageActual, DojoDto.class);

        //Expected
        ContentPageDto contentPageDto = new ContentPageDto(1,1,5,0);
        DojoDto dojoDto = new DojoDto();
        dojoDto.setId(1l);
        dojoDto.setDojoName("an");
        dojoDto.setCreatedAt(dateTime);
        dojoDto.setStatus(IN_OPERATION);
        List<DojoDto> dojoDtos = new ArrayList<>();
        dojoDtos.add(dojoDto);
        PageResDto<DojoDto> pageResDto = new PageResDto<>(dojoDtos, contentPageDto);

        when(pageUtilsMock.convertPageEntityToDTO(dojoPageActual, DojoDto.class)).thenReturn(pageResDtoActual);
        dojoService.findAll(sizePagination);

        assertEquals(pageResDto, pageResDtoActual);
    }
}
