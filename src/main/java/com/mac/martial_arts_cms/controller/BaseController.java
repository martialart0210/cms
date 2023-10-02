package com.mac.martial_arts_cms.controller;

import com.mac.martial_arts_cms.model.dto.ApiResponseDto;
import com.mac.martial_arts_cms.utils.CommonConstants;
import com.mac.martial_arts_cms.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The class BaseController
 * @author <a href="mailto:dongphuocan123@gmail.com">DongPhuocAn</a>
 */
public abstract class BaseController {
    @Autowired
    MessageUtils messageUtils;

    /**
     *
     * @param msgCode
     * @param data
     * @param params
     * @return object DTO when process successfully
     */
    public ResponseEntity<? extends Object> success(String msgCode, Object data, Object[] params) {

        ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(msgCode)
                .message(messageUtils.getMessage(msgCode, params)).data(data)
                .status(CommonConstants.ApiStatus.STATUS_OK).build();

        return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.OK);
    }

    /**
     *
     * @param msgCode
     * @param params
     * @return object DTO when process failed
     */
    public ResponseEntity<? extends Object> failed(String msgCode, Object[] params) {

        ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(msgCode)
                .message(messageUtils.getMessage(msgCode, params)).data(null)
                .status(CommonConstants.ApiStatus.STATUS_ERROR).build();

        return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.OK);
    }

    /**
     *
     * @param msgCode
     * @param data
     * @param params
     * @return object DTO when process failed with error
     */
    public ResponseEntity<? extends Object> failedWithError(String msgCode, Object data, Object[] params) {

        ApiResponseDto apiResponseDto = ApiResponseDto.builder().code(msgCode)
                .message(messageUtils.getMessage(msgCode, params)).data(data)
                .status(CommonConstants.ApiStatus.STATUS_ERROR).build();

        return new ResponseEntity<ApiResponseDto>(apiResponseDto, HttpStatus.OK);
    }
}
