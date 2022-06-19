package youngjun.bigdataProject.web.detail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import youngjun.bigdataProject.domain.weather.WeatherService;

@Slf4j
@Controller
@RequestMapping("/detail")
@RequiredArgsConstructor
public class DetailController {

    private final WeatherService service;

    @GetMapping("/{region}")
    public String detail(@PathVariable String region, Model model) {
        model.addAttribute("weather", service.requestWeather(region));
        return "detail";
    }
}
