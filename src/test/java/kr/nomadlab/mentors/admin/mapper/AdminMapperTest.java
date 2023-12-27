package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void CompleteExchange(){
        List<ExchangeVO> exchangeVOList = adminMapper.getExedSearchAll(AdminExSearchDTO.builder()
                        .up(3000)
                        .down(5000)
                .build());
        log.info(exchangeVOList);
    }
}
