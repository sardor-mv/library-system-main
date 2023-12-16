package seoultech.library.controller;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import seoultech.library.model.BookStatus;
import seoultech.library.service.BookItemService;
import seoultech.library.service.UserService;

@Controller
@RequestMapping(Mappings.ADMIN)
public class DashboardController {

    private SessionRegistry sessionRegistry;
    private BookItemService bookItemService;
    private UserService userService;

    public DashboardController(SessionRegistry sessionRegistry, BookItemService bookItemService, UserService userService) {
        this.sessionRegistry = sessionRegistry;
        this.bookItemService = bookItemService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashBoard() {

        final ModelAndView mav = new ModelAndView();

        mav.addObject("activeDash", "active");
        mav.addObject("totalBooks", bookItemService.count());
        mav.addObject("lentBooks", bookItemService.countByStatus(BookStatus.LOANED));
        mav.addObject("totalMembers", userService.count());
        mav.addObject("loggedMembers", sessionRegistry.getAllPrincipals().size());
        mav.setViewName("admin/dashboard");
        return mav;
    }

}