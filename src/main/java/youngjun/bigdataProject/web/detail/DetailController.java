package youngjun.bigdataProject.web.detail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/detail")
public class DetailController {

    @GetMapping("/{region}")
    public String detail(@PathVariable String region, Model model) {
        return "detail";
    }
}
