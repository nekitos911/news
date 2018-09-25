package com.application.news.error;

import org.springframework.ui.Model;

public class CommonException extends CustomError {

    public CommonException(Exception e) {
        super(e);
    }

    public CommonException(String msg, Model model) {
        super(msg, model);
    }
}
