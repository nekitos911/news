package com.application.news.error;

import org.springframework.ui.Model;

public class RegisterException extends CustomError {
    private static final String exceptionId = "1";
    private static final String TITLE = "Ошибка регистрации: ";

    public RegisterException(Exception e) {
        super(e);
    }

    public RegisterException(String msg, Model model) {
        super(TITLE + msg, model);
    }
}
