package com.mac.martial_arts_cms.service.impl;

import com.mac.martial_arts_cms.Exceptions.FigureNotFoundException;
import com.mac.martial_arts_cms.model.response.FigureResponse;
import com.mac.martial_arts_cms.repository.FigureRepository;
import com.mac.martial_arts_cms.service.inf.FigureService;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The Class FigureServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class FigureServiceImpl implements FigureService {


    @Autowired
    private FigureRepository repository;

    @Autowired
    private MessageUtils messageUtils;


    /**
     * @param username
     * @return FigureResponse
     */
    @Override
    public FigureResponse getFigureInfo(String username) {
        List<Optional<Object>> figure = repository.getInfoFigureByUsername(username);
        Object[] list = (Object[]) figure.get(0).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
            return new FigureNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
        });
        FigureResponse response = new FigureResponse();
        response.setCharacterName((String) list[0]);
        response.setGold((Long) list[1]);
        response.setGuildName((String) list[2]);
        return response;

    }
}
