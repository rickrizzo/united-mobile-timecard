package ua.server.base.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


public class MessagesUtil {
	
	public static String getLocalizedMessage(MessageSource messageSource, String key, Object[] args, Locale locale) {
		return messageSource.getMessage(key, args, locale);
	}
	public static String getLocalizedMessage(MessageSource messageSource, String key, Object[] args) {
		return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
	}
	public static String getLocalizedMessage(MessageSource messageSource, String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}
	public static String getLocalizedMessage(MessageSource messageSource, String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
}