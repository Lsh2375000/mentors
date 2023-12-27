package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.CoinStatsVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoinStatsMapper {

    void insertCoinStats(CoinStatsVO coinStatsVO); // 코인 충전량 통계 추가

}
