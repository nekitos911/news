package com.application.news.controller;

import com.application.news.domain.Message;
import com.application.news.domain.User;
import com.application.news.error.CommonException;
import com.application.news.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    private final MessageRepo messageRepo;

    @Autowired
    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/home")
    public String home(
            @RequestParam(required = false) String filter,
            Model model) {
        Iterable<Message> messages;
        if(!StringUtils.isEmpty(filter)) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "home";
    }

    @PostMapping("/home")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile fileMultipart,
            Model model) {

        Message message = new Message(text, tag, user);
        if(fileMultipart != null && !StringUtils.isEmpty(fileMultipart.getOriginalFilename())) {
            if(!Files.exists(Paths.get(uploadPath))) {
                try {
                    Files.createDirectory(Paths.get(uploadPath));
                } catch (IOException e) {
                    throw new CommonException(e.getMessage(), model);
                }
            }
            String fileId = UUID.randomUUID().toString();
            String fileName = fileId + "." + fileMultipart.getOriginalFilename();
            try {
                fileMultipart.transferTo(Paths.get(uploadPath + "/" + fileName).toFile());
            } catch (IOException e) {
                throw new CommonException(e.getMessage(), model);
            }
            message.setFilename(fileName);
        }


        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "home";
    }

}
