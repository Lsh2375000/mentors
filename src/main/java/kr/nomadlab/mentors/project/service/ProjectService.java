package kr.nomadlab.mentors.project.service;


import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.project.dto.ProjectDTO;
import kr.nomadlab.mentors.project.dto.ProjectLikeDTO;
import kr.nomadlab.mentors.project.dto.ProjectTagDTO;

import java.util.List;


public interface ProjectService {
    
    void registerProject(ProjectDTO projectDTO); // 게시글 등록

    void removeProject(Long projectNo); //게시글 삭제

    ProjectDTO getProject(Long projectNo, String mode); //게시글 조회

    void modifyProject(ProjectDTO projectDTO); // 게시글 수정

    PageResponseDTO<ProjectDTO> getProjectList(PageRequestDTO pageRequestDTO); // 게시글 목록 조회
    
    Long addLike(ProjectLikeDTO projectLikeDTO); // 게시글 좋아요 추가
    
    Boolean removeLike(Long pjlNo); // 게시글 좋아요 삭제
    
    List<ProjectTagDTO> getTopTagList(); // 상위 10개 태그 조회
    
    void addHashTag(ProjectTagDTO projectTagDTO); // 해쉬태그 추가

    void modifyHashTag(Long projectNo, List<ProjectTagDTO> tagList); // 해쉬태그 수정

    PageResponseDTO<ProjectDTO> getMyProjectList(Long mno, PageRequestDTO pageRequestDTO); // 게시글 목록 조회

}
