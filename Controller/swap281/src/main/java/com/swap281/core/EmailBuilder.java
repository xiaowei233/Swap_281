package com.swap281.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailBuilder {

	private TemplateEngine templateEngine;
 
    @Autowired
    public EmailBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String build(String username, String link, String message) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("link", link);
        context.setVariable("message", message);
        return templateEngine.process("account_verification_template", context);
    }
 
}