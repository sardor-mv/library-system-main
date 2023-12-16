package seoultech.library.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import seoultech.library.model.Role;
import seoultech.library.model.User;
import seoultech.library.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(Mappings.ADMIN + Mappings.USER)
public class UserManageController {

    private UserService userService;

    public UserManageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/create")
    public ModelAndView create() {

        final ModelAndView mav = new ModelAndView();
        mav.setViewName("user/create");

        return mav;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView create(
            @RequestParam(value = "username") final String _username,
            @RequestParam(value = "email") final String _email,
            @RequestParam(value = "firstName") final String _firstName,
            @RequestParam(value = "lastName") final String _lastName,
            @RequestParam(value = "password") final String password){

        final ModelAndView mav = new ModelAndView();

        String username = _username.trim();
        String email = _email.trim();
        String firstName = _firstName.trim();
        String lastName = _lastName.trim();

        Optional<User> optUser = userService.findByUsername(username);
        if(optUser.isPresent()) {
            mav.addObject("userCreateMessage", "Following username already exists: ");
            mav.addObject("username", username);
            mav.setViewName("book/response");
            return mav;
        }
        User user = new User(username, email, firstName, lastName);
        user.setActive(true);
        user.setDeleted(false);
        user.setImmutable(false);
        user.setPassword("");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.getUserRole());
        user.setRoles(roleSet);

        userService.save(user, User.getAdminUser());
        userService.updatePassword(user, password, User.getAdminUser());

        mav.addObject("userCreateMessage", "User with following username successfully created: ");
        mav.addObject("username", username);
        mav.setViewName("book/response");
        return mav;
    }

}