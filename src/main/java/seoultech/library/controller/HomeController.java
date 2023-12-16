package seoultech.library.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seoultech.library.model.BookItem;
import seoultech.library.model.BookItemRepository;
import seoultech.library.service.BookItemService;
import seoultech.library.service.UserService;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(Mappings.HOME)
public class HomeController {


    private UserService userService;
    private BookItemRepository br;
    private BookItemService bs;

    public HomeController(BookItemService bookItemService, UserService userService, BookItemRepository br) {
        this.userService = userService;
        this.br = br;
        this.bs = bookItemService;
    }


    @GetMapping
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/sign/in");
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test(
            @RequestParam(value = "isbn") final String iSBN){

        BookItem optBookItem = br.findFirstByiSBN(iSBN);
        RestResponse response = RestResponse.ok();
        //response.put("bookItem", optBookItem.get());

        return ResponseEntity.ok(response);
    }
}
