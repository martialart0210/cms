package com.mac.m2land_cms.service;

import com.mac.m2land_cms.model.entity.GameLog;
import com.mac.m2land_cms.model.entity.OperationLog;
import com.mac.m2land_cms.model.entity.User;
import com.mac.m2land_cms.repository.GameLogRepository;
import com.mac.m2land_cms.repository.OperationLogRepository;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.utils.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserLogoutHandler implements LogoutHandler {
    private UserRepository userRepository;
    private GameLogRepository gameLogRepository;
    private ModelMapper modelMapper;
    private OperationLogRepository operationLogRepository;

    @Autowired
    public UserLogoutHandler(UserRepository userRepository, GameLogRepository gameLogRepository, ModelMapper modelMapper, OperationLogRepository operationLogRepository) {
        this.userRepository = userRepository;
        this.gameLogRepository = gameLogRepository;
        this.modelMapper = modelMapper;
        this.operationLogRepository = operationLogRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String username = userDetails.getUsername();
            Optional<User> user = userRepository.findUsersByUsername(username);
            if (user.isPresent()){
                GameLog gameLog = new GameLog();
                gameLog.setTimestamp(LocalDateTime.now());
                gameLog.setDetail(CommonConstants.MessageLog.DETAIL_LOGOUT);
                gameLog.setLabel(CommonConstants.MessageLog.LABEL_LOGOUT);
                gameLog.setUserId(user.get().getId());
                gameLog.setColor(CommonConstants.COLOR.COLOR_LOGOUT);
                gameLogRepository.save(gameLog);

                OperationLog operationLog = modelMapper.map(gameLog, OperationLog.class);
                operationLogRepository.save(operationLog);
            }
        }
    }
}
