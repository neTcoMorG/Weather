package youngjun.bigdataProject.web.detail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import youngjun.bigdataProject.domain.dto.rank.RankDto;
import youngjun.bigdataProject.domain.dto.weather.WeatherDto;
import youngjun.bigdataProject.domain.weather.RegionService;

@Slf4j
@Controller
@RequestMapping("/detail")
@RequiredArgsConstructor
public class DetailController {

    private final RegionService regionService;

    @GetMapping("/{region}")
    public String detail(@PathVariable String region, Model model) {
        WeatherDto weather = regionService.getWeather(region);
        RankDto rank = regionService.getRank((byte)4);

        initModel(model, weather, rank);
        return "detail";
    }

    private void initModel (Model model, WeatherDto weather, RankDto rank) {
        model.addAttribute("weather", weather.getRecent());
        model.addAttribute("temp_rank", rank.getTempRankObject());
        model.addAttribute("wind_rank", rank.getWindRankObject());
        model.addAttribute("temp_object", weather.getTempObject());
        model.addAttribute("hum_object", weather.getHumObject());
    }
}
