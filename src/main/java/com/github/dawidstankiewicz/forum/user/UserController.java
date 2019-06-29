package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.user.exception.UserNotFoundException;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/user/{username}")
    public String findUserByUsernameAndViewProfilePage(@PathVariable String username,
        Model model) {
        UserProfile userProfile;
        try {
            userProfile = userProfileService.findOne(username);
        } catch (NullPointerException e) {
            throw new UserNotFoundException();
        }
        model.addAttribute("userProfile", userProfile);
        return "user";
    }

    @RequestMapping(value = "/users")
    public String listOfAllUser(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/myprofile")
    public String myProfile(Authentication authentication,
        Model model) {
        String username = authentication.getName();
        UserProfile userProfile;
        try {
            userProfile = userProfileService.findOne(username);
        } catch (NullPointerException e) {
            throw new UserNotFoundException();
        }
        model.addAttribute("userProfile", userProfile);
        return "user";
    }

    @RequestMapping(value = "/myprofile/edit/picture", method = RequestMethod.POST)
    public String processAndSaveProfilePicture(@RequestPart MultipartFile profilePicture,
        HttpServletRequest request,
        Authentication authentication,
        RedirectAttributes redirectModel) {
        if (authentication.getName() == null) {
            return "redirect:/login";
        }
        if (profilePicture.isEmpty()) {
            return "redirect:/myprofile";
        }
        User user = userService.findByUsername(authentication.getName());
        try {
            String path =
                request.getSession().getServletContext().getRealPath("/resources/public/img/pp/");
            profilePicture.transferTo(new File(path + user.getId() + ".jpg"));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        userService.save(user);
        redirectModel.addFlashAttribute("message", "user.picture.successfully.saved");
        return "redirect:/myprofile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logOutAndRedirectToLoginPage(Authentication authentication,
        HttpServletRequest request,
        HttpServletResponse response) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }
}
