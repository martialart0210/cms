package com.mac.m2land_cms.Exceptions;

/**
 * The class WebSecurityConfig.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public class GameLogNotFoundException extends RuntimeException{

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public GameLogNotFoundException(String message) {
        super(message);
    }
}
