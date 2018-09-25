package com.application.news.error;

import org.springframework.ui.Model;

public abstract class CustomError extends RuntimeException {

    public CustomError(Exception e) {
        super(e);
    }

    public CustomError(String msg, Model model) {
        super(msg);
        model.addAttribute("message", msg);
    }
}
