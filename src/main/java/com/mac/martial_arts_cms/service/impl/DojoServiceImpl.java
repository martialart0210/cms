package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.model.dto.DojoDto;
import com.mac.martial_arts_cms.model.dto.PageResDto;
import com.mac.martial_arts_cms.model.entity.Dojo;
import com.mac.martial_arts_cms.repository.DojoRepository;
import com.mac.martial_arts_cms.service.inf.DojoService;
import com.mac.martial_arts_cms.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        PageResDto<DojoDto> page = pageUtils.convertPageEntityToDTO(list, DojoDto.class);
        return page;
    }
}
