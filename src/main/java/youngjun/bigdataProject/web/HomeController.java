package youngjun.bigdataProject.web;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableJpaAuditing
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("test", "hello, world");
        return "korea";
    }
}
