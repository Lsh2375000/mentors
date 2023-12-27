package kr.nomadlab.mentors.admin.controller;

import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.admin.service.AdminService;
import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminRestController {
    private final AdminService adminService;

    //리스트 불러오기
    @GetMapping(value = "/exchangeList")
    public List<ExchangeDto> getExchangeList(AdminExSearchDTO adminExSearchDTO){
        log.info(adminExSearchDTO);
        log.info("--------------");

        List<ExchangeDto> exchangeDTOList = adminService.adminExchangeSearch(adminExSearchDTO);

        log.info(exchangeDTOList);
        log.info("--------------");
        return exchangeDTOList;
    }
    //리스트 불러오기
    @GetMapping(value = "/exchangedList")
    public List<ExchangeDto> getExchangedList(AdminExSearchDTO adminExSearchDTO){
        log.info(adminExSearchDTO);
        log.info("--------------");

        List<ExchangeDto> exchangeDTOList = adminService.adminExchangedSearch(adminExSearchDTO);

        log.info(exchangeDTOList);
        log.info("--------------");
        return exchangeDTOList;
    }

    @GetMapping(value = "/exchangeInfo/{exNo}")
    public ExchangeDto getExchange(@PathVariable Long exNo){
        log.info(exNo);
        log.info("--------------");

        ExchangeDto exchangeDto = adminService.adminExchange(exNo);

        log.info("--------------");
        return exchangeDto;
    }

    @PutMapping("/exchange/{exNo}/complete")
    public void completeExchange(@PathVariable Long exNo) {
        adminService.completeExchange(exNo);
    }
}
