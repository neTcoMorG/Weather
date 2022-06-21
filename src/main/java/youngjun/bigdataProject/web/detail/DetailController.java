package youngjun.bigdataProject.web.detail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import youngjun.bigdataProject.domain.weather.WeatherRepository;
import youngjun.bigdataProject.domain.weather.WeatherService;

@Slf4j
@Controller
@RequestMapping("/detail")
@RequiredArgsConstructor
public class DetailController {

    private final WeatherService service;

    private final Pageable topFour = PageRequest.of(0, 4);

    @GetMapping("/{region}")
    public String detail(@PathVariable String region, Model model) {
        model.addAttribute("weather", service.requestWeather(region));
        model.addAttribute("history_temp", service.getHistoryTemp(region));
        model.addAttribute("history_hum", service.getHistoryHumidity(region));

        log.info("rank_temp={}", service.getTempRank());
        log.info("rank_hum={}", service.getHumRank());
        log.info("rank_wind={}", service.getWindRank());

        model.addAttribute("temp_rank", service.getTempRank());
        model.addAttribute("hum_rank", service.getHumRank());
        model.addAttribute("wind_rank",service.getWindRank());

        model.addAttribute("test", service.getTempRank().getRegion());

        return "detail";
    }
}
