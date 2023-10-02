package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.FigureNotFoundException;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.model.entity.UserCharacter;
import com.mac.m2land_cms.model.request.UserCharacterRequest;
import com.mac.m2land_cms.model.response.UserCharacterResponse;
import com.mac.m2land_cms.repository.UserCharacterRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * The Class UserCharacterServiceImplTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserCharacterServiceImplTest {

    @Mock
    private UserCharacterRepository userCharacterRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserCharacterServiceImpl userCharacterService;

    @Before
    public void setUp(){
        when(messageUtils.getMessage(any(),any())).thenReturn("Error message");
    }

    @Test
    public void getFigureInfoSuccess(){
        Long idUser = 123L;
        UserCharacter userCharacter =
                new UserCharacter();
        UserCharacterResponse response = new UserCharacterResponse();
        response.setCharacterName("characterName");
        response.setGold(1L);
        response.setGuildName("guildName");
        when(userCharacterRepository.findByUserId(idUser)).thenReturn(Optional.of(userCharacter));
        when(modelMapper.map(userCharacter,UserCharacterResponse.class)).thenReturn(response);

        UserCharacterResponse result = userCharacterService.getUserCharacterInfo(idUser);
        assertEquals("characterName",result.getCharacterName());
    }


    @Test(expected = FigureNotFoundException.class)
    public void getFigureInfoNotFound(){
        Long idUser = 123L;
        when(userCharacterRepository.findByUserId(idUser)).thenReturn(Optional.empty());
        userCharacterService.getUserCharacterInfo(idUser);
    }

    @Test
    public void updateFigureSuccess(){
        UserCharacter userCharacter =
                new UserCharacter();
        UserCharacterResponse response = new UserCharacterResponse();
        response.setCharacterName("characterName");
        response.setGold(1L);
        response.setGuildName("guildName");

        UserCharacterRequest request = new UserCharacterRequest();
        request.setId(1L);
        request.setCharacterName("characterName");
        request.setGold(1L);
        request.setGuildName("guildName");
        request.setUserId(1L);

        User mockUser = new User();
        when(modelMapper.map(request, UserCharacter.class)).thenReturn(userCharacter);
        when(userCharacterRepository.findById(userCharacter.getId())).thenReturn(Optional.of(userCharacter));
        when(userCharacterRepository.save(userCharacter)).thenReturn(userCharacter);
        when(modelMapper.map(userCharacter,UserCharacterResponse.class)).thenReturn(response);
        UserCharacterResponse result = userCharacterService.updateUserCharacter(request);

        assertEquals("characterName",result.getCharacterName());
    }

    @Test(expected = FigureNotFoundException.class)
    public void updateFigureUserNotFoundException(){
        UserCharacter userCharacter =
                new UserCharacter();
        UserCharacterResponse response = new UserCharacterResponse();
        response.setCharacterName("characterName");
        response.setGold(1L);
        response.setGuildName("guildName");

        UserCharacterRequest request = new UserCharacterRequest();
        request.setId(1L);
        request.setCharacterName("characterName");
        request.setGold(1L);
        request.setGuildName("guildName");
        request.setUserId(1L);

        when(modelMapper.map(request, UserCharacter.class)).thenReturn(userCharacter);

        userCharacterService.updateUserCharacter(request);
    }


    @Test(expected = FigureNotFoundException.class)
    public void updateFigureFigureNotFoundException(){
        UserCharacter userCharacter =
                new UserCharacter();
        UserCharacterResponse response = new UserCharacterResponse();
        response.setCharacterName("characterName");
        response.setGold(1L);
        response.setGuildName("guildName");

        UserCharacterRequest request = new UserCharacterRequest();
        request.setId(1L);
        request.setCharacterName("characterName");
        request.setGold(1L);
        request.setGuildName("guildName");
        request.setUserId(1L);

        when(modelMapper.map(request, UserCharacter.class)).thenReturn(userCharacter);

        userCharacterService.updateUserCharacter(request);
    }
}
