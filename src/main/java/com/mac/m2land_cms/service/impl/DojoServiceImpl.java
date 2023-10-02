package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.model.dto.ContentPageDto;
import com.mac.m2land_cms.model.dto.DojoDto;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.entity.Dojo;
import com.mac.m2land_cms.model.entity.DojoMember;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.enum_class.DojoPosition;
import com.mac.m2land_cms.repository.DojoRepository;
import com.mac.m2land_cms.service.inf.DojoService;
import com.mac.m2land_cms.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The class DojoServiceImp.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Service
public class DojoServiceImpl implements DojoService {
    private DojoRepository dojoRepository;
    private PageUtils pageUtils;

    @Autowired
    public DojoServiceImpl(DojoRepository dojoRepository, PageUtils pageUtils) {
        this.dojoRepository = dojoRepository;
        this.pageUtils = pageUtils;
    }

    /**
     * findAll
     * @param pageable
     * @return PageResDto<DojoDto>
     */
    @Override
    public PageResDto<DojoDto> findAll(Pageable pageable) {
        Page<Dojo> list = dojoRepository.findAll(pageable);

        List<DojoDto> newDto = new ArrayList<>();
        list.forEach(dojo -> {
            DojoDto dojoDto = new DojoDto();
            BeanUtils.copyProperties(dojo, dojoDto);
            dojoDto.setMember(dojo.getDojoMemberList().size());
            if(getGuildLeader(dojo) != null) {
                dojoDto.setGuildLeader(getGuildLeader(dojo));
            }
            newDto.add(dojoDto);
        });
        ContentPageDto contentPageDto = new ContentPageDto(list.getTotalPages(), list.getTotalElements(), list.getSize(), list.getNumber());

        PageResDto<DojoDto> page = new PageResDto<>(newDto, contentPageDto);
        return page;
    }

    /**
     * get guild leader
     * @param dojo
     * @return
     */
    private String getGuildLeader(Dojo dojo) {
        for (DojoMember dojoMember: dojo.getDojoMemberList()) {
            if(dojoMember.getPosition().compareTo(DojoPosition.INSTRUCTOR) == 0){
                return dojoMember.getCharacter().getId().toString();
            }
        }
        return null;
    }
}
