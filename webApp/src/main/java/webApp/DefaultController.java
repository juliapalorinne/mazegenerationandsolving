package webApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String frontPage(Model model) {
        model.addAttribute("message", "Welcome to maze generator and solver!");
        
        return "index";
    }
    
    @GetMapping("/depthfirstsearch")
    public String depthfirstSearch(Model model) {
        model.addAttribute("message", "Welcome to depth-first search!");
        
        return "depthfirstsearch";
    }
}
