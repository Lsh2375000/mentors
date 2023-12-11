package kr.nomadlab.progrow_project.boardQna.mapper;


import kr.nomadlab.progrow_project.boardQna.domain.QBoardVO;
import kr.nomadlab.progrow_project.boardQna.domain.QBoardListAllVO;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QBoardMapper {

    void insert(QBoardVO qBoardVO); //게시글 생성 메서드

    QBoardVO modify(Long qBoardVO); //게시글 수정 메서드
    
    void deleteOne(Long qBoardVO); // 게시글 하나 삭제

    void update(QBoardVO qBoardVO); // 게시글 하나 수정

    QBoardVO selectOne(Long qnaBoardNo); //게시글 하나 선택해서 읽기
    
    void updateHit(Long qnaBoardNo); // 게시글 조회수 수정

    List<QBoardListAllVO> selectList(PageRequestDTO pageRequestDTO); // 게시글 전체 리스트 형태로 조회

    int getCount(PageRequestDTO pageRequestDTO); // 게시글 수 카운팅
}
