package kr.nomadlab.mentors.exChange.service;

import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import kr.nomadlab.mentors.exChange.mapper.ExchangeMapper;
import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExchangeServiceImpl implements ExchangeService{
    private final ModelMapper modelMapper;
    private final ExchangeMapper exchangeMapper;


    @Override
    public void insertExchange(ExchangeDto exchangeDto) {
        exchangeDto.setComplete(false);
        ExchangeVO exchangeVO = modelMapper.map(exchangeDto, ExchangeVO.class);
        log.info(exchangeVO);
        exchangeMapper.insertExchange(exchangeVO);
    }
}
