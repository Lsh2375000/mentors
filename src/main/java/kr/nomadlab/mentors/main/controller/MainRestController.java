package kr.nomadlab.mentors.main.controller;

import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainRestController {

    private final MainService mainService;

    @GetMapping("/{mbNo}")
    public MainDTO getOne(@PathVariable("mbNo") Long mbNo, Model model){
        log.info("레스트 들어옴");
        log.info(mbNo);

        MainDTO mainDTO = mainService.getBoard(mbNo);

        model.addAttribute("test", 1234);

        return mainDTO;
    }
}
