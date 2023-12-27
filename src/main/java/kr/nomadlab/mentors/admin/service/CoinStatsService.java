package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.dto.CoinStatsDTO;

public interface CoinStatsService {
    
    void insertCoinStats(CoinStatsDTO coinStatsDTO); // 충전량 통계
}
