package youngjun.bigdataProject.web.detail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class WordController {

    @GetMapping("wc")
    public String wordCloud() {
        return "word";
    }
}
