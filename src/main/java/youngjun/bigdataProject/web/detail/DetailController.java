package youngjun.bigdataProject.web.detail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import youngjun.bigdataProject.domain.dto.WeatherDto;
import youngjun.bigdataProject.domain.weather.RegionService;

@Slf4j
@Controller
@RequestMapping("/detail")
@RequiredArgsConstructor
public class DetailController {

    private final RegionService regionService;

    @GetMapping("/{region}")
    public String detail(@PathVariable String region, Model model) {
        WeatherDto weather = regionService.getWeather(region, (byte)4);
        initModel(model, weather);
        return "detail";
    }

    private void initModel (Model model, WeatherDto dto) {



//        Weather weather = service.getWeather(region);
//        model.addAttribute("weather", "ss");
//        model.addAttribute("history_temp", service.getHistoryTemp(region));
//        model.addAttribute("history_hum", service.getHistoryHumidity(region));
//        model.addAttribute("temp_rank", service.getTempRank());
//        model.addAttribute("hum_rank", service.getHumRank());
//        model.addAttribute("wind_rank",service.getWindRank());
//        model.addAttribute("test", service.getTempRank().getRegion());
    }
}
