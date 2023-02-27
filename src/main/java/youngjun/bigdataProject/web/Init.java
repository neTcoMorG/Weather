package youngjun.bigdataProject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.repository.RegionRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Init {

    public static final String[] REGIONS = {"Seoul", "Busan", "Daegu", "Incheon", "Gwangju", "Daejeon",
            "Ulsan", "Gyeonggi-do", "Gangwon-do", "Chungcheongbuk-do", "Chungcheongnam-do", "Jeollabuk-do",
            "Jeollanam-do", "Gyeongsangbuk-do", "Gyeongsangnam-do", "Jeju-do", "Sejong"};

    private RegionRepository regionRepository;

    @PostConstruct
    private void init () {
        for (String region : REGIONS) {
            if (!isExistRegion(region))
                regionRepository.save(new Region(region));
        }
    }

    private boolean isExistRegion (String region) {
        Optional<Region> findRegion = regionRepository.findByName(region);
        if (findRegion.isEmpty()) return false;
        return true;
     }
}
