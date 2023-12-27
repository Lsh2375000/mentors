package kr.nomadlab.mentors.project.mapper;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.domain.HashTagVO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.project.domain.ProjectLikeVO;
import kr.nomadlab.mentors.project.domain.ProjectTagVO;
import kr.nomadlab.mentors.project.domain.ProjectVO;
import kr.nomadlab.mentors.project.dto.ProjectTagDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProjectMapper {

    void insertProject(ProjectVO projectVO); //게시글 생성

    void deleteProject(Long projectNo); // 게시글 삭제

    ProjectVO selectProject(Long projectNo); // 게시글 조회

    void updateProject(ProjectVO projectVO); // 게시글 수정

    int getCount(PageRequestDTO pageRequestDTO); // 게시글 총 갯수

    List<ProjectVO> selectProjectList(PageRequestDTO pageRequestDTO); // 게시글 전체 조회

    void updateHit(Long projectNo); // 조회수 업데이트

    void insertLike(ProjectLikeVO projectLikeVO); // 좋아요 추가

    Boolean deleteLike(Long pjlNo); // 좋아요 삭제

    List<ProjectLikeVO> selectLikeList(Long projcectNo); // 해당 게시글 좋아요 조회
    
    List<ProjectTagVO> selectTagList(Long projectNo); // 태그 목록 조회

    List<ProjectTagDTO> selectTopTagList(); // 상위 10개 태그 조회
    
    void insertTag(ProjectTagVO projectTagVO); // 태그 추가

    void deleteTag(Long projectNo); // 태그 삭제

    List<ProjectVO> selectMyProjectList(@Param("mno") Long mno,
                                        @Param("skip") int skip,
                                        @Param("size") int size); // 게시글 전체 조회

    int getMyCount(Long mno); // 본인 게시글 총 갯수
}
