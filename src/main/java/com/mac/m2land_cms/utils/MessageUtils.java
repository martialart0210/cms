package com.mac.m2land_cms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {
    /**
     * The resources.
     */
    @Autowired
    private MessageSource resources;

    public String getMessage(String code, @Nullable Object[] args) {
        return resources.getMessage(code, args, getLocale());
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

}
