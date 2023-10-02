package com.mac.martial_arts_cms.Exceptions;

/**
 * The class UserSuspensionException.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
public class UserSuspensionException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UserSuspensionException(String message) {
        super(message);
    }
}
