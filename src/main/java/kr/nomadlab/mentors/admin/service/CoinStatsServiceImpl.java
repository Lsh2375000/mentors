package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.CoinStatsVO;
import kr.nomadlab.mentors.admin.dto.CoinStatsDTO;
import kr.nomadlab.mentors.admin.mapper.CoinStatsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CoinStatsServiceImpl implements CoinStatsService{

    private final ModelMapper modelMapper;
    private final CoinStatsMapper coinStatsMapper;

    @Override // 코인 충전량 통계 추가
    public void insertCoinStats(CoinStatsDTO coinStatsDTO) {
        CoinStatsVO coinStatsVO = modelMapper.map(coinStatsDTO, CoinStatsVO.class);
        coinStatsMapper.insertCoinStats(coinStatsVO);
    }
}
