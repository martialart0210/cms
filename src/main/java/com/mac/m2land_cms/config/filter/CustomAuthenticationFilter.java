package com.mac.m2land_cms.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mac.m2land_cms.model.dto.ApiResponseDto;
import com.mac.m2land_cms.model.dto.AuthenDto;
import com.mac.m2land_cms.model.dto.LogInDto;
import com.mac.m2land_cms.model.dto.UserDto;
import com.mac.m2land_cms.model.entity.GameLog;
import com.mac.m2land_cms.repository.GameLogRepository;
import com.mac.m2land_cms.service.inf.FirebaseMessagingService;
import com.mac.m2land_cms.service.inf.TokenService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.LocalHandBeanUtil;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.TokenUtils;
import com.mac.m2land_cms.utils.UserAuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String SESSION = "sessionId:";
    public static String currentUsername = "";

    private AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final FirebaseMessagingService firebaseMessagingService;

    @Autowired
    private UserAuthUtils authUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private LocalHandBeanUtil localHandBeanUtil;

    @Autowired
    private MessageUtils messageUtils;

    private GameLogRepository gameLogRepository;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManage, TokenService tokenService, FirebaseMessagingService firebaseMessagingService, GameLogRepository gameLogRepository) {
        this.authenticationManager = authenticationManage;
        this.tokenService = tokenService;
        this.firebaseMessagingService = firebaseMessagingService;
        this.gameLogRepository = gameLogRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String requestData = "";
        implementBean(request);
        try {
            requestData = request.getReader().lines().collect(Collectors.joining());
        } catch (IOException ex) {

        }
        LogInDto loginInfor = null;
        String username = null;
        String password = null;
        try {
            loginInfor = new Gson().fromJson(requestData, LogInDto.class);
            username = loginInfor.getUsername();
            password = loginInfor.getPassword();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        currentUsername = username;
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(authToken);
        return auth;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // set content type as json
        response.setContentType("application/json");
        String userName = currentUsername;
        implementBean(request);
        ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(CommonConstants.MessageError.ER017)
                .message(messageUtils.getMessage(CommonConstants.MessageError.ER017, null)).data(null)
                .status(CommonConstants.ApiStatus.STATUS_ERROR).build();
		try {
			response.getWriter().write(localHandBeanUtil.objectToJsonString(apiResponseDto));
            response.setContentType("application/json");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
		implementBean(request);
		String accessToken = tokenUtils.generateAccessToken(request, (User) authResult.getPrincipal());
        String refreshToken = tokenUtils.generateRefreshToken(request,(User) authResult.getPrincipal());
        UserDto userDto = new UserDto();
        try {
             userDto = authUtils.getUserInfo(((User) authResult.getPrincipal()).getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        AuthenDto authenDto = new AuthenDto(accessToken,refreshToken,"Bearer",userDto);
        ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(CommonConstants.MessageSuccess.SC007)
                .message(messageUtils.getMessage(CommonConstants.MessageSuccess.SC007, null)).data(authenDto)
                .status(CommonConstants.ApiStatus.STATUS_OK).build();
        try {
            response.getWriter().write(localHandBeanUtil.objectToJsonString(apiResponseDto));
            response.setContentType("application/json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameLog gameLog = new GameLog();
        gameLog.setColor(CommonConstants.COLOR.COLOR_LOGIN);
        gameLog.setLabel(CommonConstants.MessageLog.LOGIN);
        gameLog.setDetail(CommonConstants.MessageLog.DETAIL_LOGIN);
        gameLog.setTimestamp(LocalDateTime.now());
        gameLog.setUserId(userDto.getId());
        gameLogRepository.save(gameLog);
//        tokenService.addSessionId(SESSION + String.valueOf(userDto.getId()),refreshToken);
        response.setHeader("Access-Control-Expose-Headers",
				CommonConstants.Authentication.ACCESS_TOKEN + "," + CommonConstants.Authentication.REFRESH_TOKEN
						+ ", x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
						+ "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//        String isDeviceToken = tokenService.getSessionId(SESSION + String.valueOf(userDto.getId()));
//        if (null != isDeviceToken){
//            try {
//                firebaseMessagingService.sendNotificationToBatch(Arrays.asList(isDeviceToken),"deviceToken",isDeviceToken);
//                System.out.println("send success!!!!");
//            } catch (FirebaseMessagingException e) {
//                System.out.println("errr: " + e);
//                throw new RuntimeException(e);
//            }
//        }e
	}

    private void implementBean(HttpServletRequest request) {
        if (tokenUtils == null || objectMapper == null || authUtils == null || messageUtils == null || localHandBeanUtil == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils
                    .getWebApplicationContext(servletContext);
            assert webApplicationContext != null;
            authUtils = webApplicationContext.getBean(UserAuthUtils.class);
            objectMapper = webApplicationContext.getBean(ObjectMapper.class);
            tokenUtils = webApplicationContext.getBean(TokenUtils.class);
            messageUtils = webApplicationContext.getBean(MessageUtils.class);
            localHandBeanUtil = webApplicationContext.getBean((LocalHandBeanUtil.class));
        }
    }

}
