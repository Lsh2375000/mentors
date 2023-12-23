package kr.nomadlab.mentors.exChange.service;

import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import org.modelmapper.ModelMapper;

public interface ExchangeService {
    void insertExchange(ExchangeDto exchangeDto);
}
