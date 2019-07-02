package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.exception.ForumException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ActivationController {

    @Autowired
    private ActivationService activationService;

    @RequestMapping(value = "/users/{username}/activation")
    public String activateUserAndRedirectToLoginPage(@PathVariable String username,
        @RequestParam String id) {
        activationService.activate(username, id);
        return "activation_success";
    }

    @ExceptionHandler(ForumException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleActivationError(
        HttpServletRequest request,
        ForumException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("timestamp", new Date());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

}
