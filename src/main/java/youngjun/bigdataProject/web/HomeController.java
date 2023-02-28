package youngjun.bigdataProject.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@EnableJpaAuditing
public class HomeController {

    @GetMapping
    public String home() {
        return "korea";
    }
}
