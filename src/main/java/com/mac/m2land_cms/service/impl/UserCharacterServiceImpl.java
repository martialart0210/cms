package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.FigureNotFoundException;
import com.mac.m2land_cms.model.entity.UserCharacter;
import com.mac.m2land_cms.model.request.UserCharacterRequest;
import com.mac.m2land_cms.model.response.UserCharacterResponse;
import com.mac.m2land_cms.repository.UserCharacterRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.service.inf.UserCharacterService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * The Class UserCharacterServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class UserCharacterServiceImpl implements UserCharacterService {


    private final UserCharacterRepository repository;

    private final UserRepository userRepository;

    private final MessageUtils messageUtils;

    private final ModelMapper modelMapper;

    public UserCharacterServiceImpl(UserCharacterRepository repository, UserRepository userRepository, MessageUtils messageUtils, ModelMapper modelMapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.messageUtils = messageUtils;
        this.modelMapper = modelMapper;
    }


    /**
     * @param userId
     * @return UserCharacterResponse
     */
    @Override
    public UserCharacterResponse getUserCharacterInfo(Long userId) {
        Optional<UserCharacter> figure = Optional.ofNullable(repository.findByUserId(userId).orElseThrow(() -> {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
            return new FigureNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
        }));
        return modelMapper.map(figure.get(),UserCharacterResponse.class);

    }

    /**
     *
     * @param request
     * @return UserCharacterResponse
     */
    @Override
    public UserCharacterResponse updateUserCharacter(UserCharacterRequest request) {
        UserCharacter figure = modelMapper.map(request, UserCharacter.class);
        validateFigure(figure);
        repository.save(figure);
        return modelMapper.map(figure, UserCharacterResponse.class);
    }

    /**
     *
     * @param figure
     */
    private void validateFigure(UserCharacter figure) {
        if (repository.findById(figure.getId()).isEmpty()){
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
            throw new FigureNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER042, null));
        }
    }
}
