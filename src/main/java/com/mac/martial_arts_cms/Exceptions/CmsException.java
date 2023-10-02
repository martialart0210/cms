package com.mac.martial_arts_cms.Exceptions;

import com.mac.martial_arts_cms.utils.MessageUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MessageUtils messageUtils;

	private String msgCode;

	private Object param[];

	/**
	 * Constructor
	 * 
	 * @param String   msgCode
	 * @param Object[] param
	 */
	public CmsException(String msgCode, Object[] param) {
		this.msgCode = msgCode;
		this.param = param;
	}

}
