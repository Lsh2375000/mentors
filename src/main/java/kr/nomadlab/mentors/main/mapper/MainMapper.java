package kr.nomadlab.mentors.main.mapper;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.main.domain.MainVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainMapper {

    void insert(MainVO mainVO); // 메인 게시물 등록

    int getCount(PageRequestDTO pageRequestDTO); // 게시물 총 갯수

    int getMentoringCnt(@Param("mno") Long mno); // 내 멘토링 횟수
    List<MainVO> list(PageRequestDTO pageRequestDTO); // 리스트 출력

    MainVO getOne(Long mbNo); // 게시물 상세뷰

    void updateOne(MainVO mainVO); // 게시물 수정
    void deleteOne(Long mbNo); // 게시물 삭제
    
    int isMentoring(Long mno); // 멘토 본인이 작성한 글이 있는지
    
    
    
    List<MainVO> myPageList(@Param("size") int size, @Param("skip") int skip, @Param("mno") Long mno); // 마이페이지 멘토링 목록 리스트 출력
}
