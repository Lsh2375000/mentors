package kr.nomadlab.mentors.project.service;


import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.project.domain.ProjectLikeVO;
import kr.nomadlab.mentors.project.domain.ProjectTagVO;
import kr.nomadlab.mentors.project.domain.ProjectVO;
import kr.nomadlab.mentors.project.dto.ProjectDTO;
import kr.nomadlab.mentors.project.dto.ProjectLikeDTO;
import kr.nomadlab.mentors.project.dto.ProjectTagDTO;
import kr.nomadlab.mentors.project.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class ProjectServiceImpl implements ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectMapper projectMapper;

    @Override
    public void registerProject(ProjectDTO projectDTO) { // 게시글 등록
        log.info("registerBoard...");
        log.info(projectDTO);

        ProjectVO projectVO = modelMapper.map(projectDTO, ProjectVO.class);

        projectMapper.insertProject(projectVO);

        if (projectDTO.getTagList().size() > 0) { // 태그 목록이 존재하는 경우
            List<ProjectTagDTO> tagList = projectDTO.getTagList();
            tagList.forEach(hashTagDTO -> {
                hashTagDTO.setProjectNo(projectVO.getProjectNo()); // 게시글 고유번호 추가
                addHashTag(hashTagDTO); // DB에 태그 저장
            });
        }
    }

    @Override
    public void removeProject(Long projectNo) { // 게시글 삭제
        projectMapper.deleteProject(projectNo);
    }

    @Override
    public ProjectDTO getProject(Long projectNo, String mode) { // 게시글 조회

        ProjectVO projectVO = projectMapper.selectProject(projectNo);
        List<ProjectLikeDTO> likeList = new ArrayList<>();
        List<ProjectTagDTO> tagList = new ArrayList<>();

        if (mode.equals("view")) { // mode가 view일때만
            projectMapper.updateHit(projectNo); // 조회수 업데이트
            List<ProjectLikeVO> likeVOList = projectMapper.selectLikeList(projectNo); // 게시글 좋아요 조회
            likeVOList.forEach(projectLikeVO -> likeList.add(modelMapper.map(projectLikeVO, ProjectLikeDTO.class)));
        }
        projectVO.getTagVOList().forEach(hashTagVO -> tagList.add(modelMapper.map(hashTagVO, ProjectTagDTO.class)));

        ProjectDTO projectDTO = modelMapper.map(projectVO, ProjectDTO.class);
        projectDTO.setLikeList(likeList);
        projectDTO.setTagList(tagList);

        return projectDTO;
    }

    @Override
    public void modifyProject(ProjectDTO projectDTO) { // 게시글 수정
        ProjectVO projectVO = modelMapper.map(projectDTO, ProjectVO.class);
        projectMapper.updateProject(projectVO);
    }

    @Override
    public PageResponseDTO<ProjectDTO> getProjectList(PageRequestDTO pageRequestDTO) { // 전체 게시물 조회
        List<ProjectVO> voList = projectMapper.selectProjectList(pageRequestDTO);
        List<ProjectDTO> dtoList = new ArrayList<>();

        voList.forEach(projectVO -> {
            ProjectDTO projectDTO = modelMapper.map(projectVO, ProjectDTO.class);

            if (projectVO.getTagVOList().size() > 0) { // 태그 목록이 존재하는 경우
                List<ProjectTagDTO> tagList = new ArrayList<>(); // 해시 태그 목록을 담을 리스트 객체
                projectVO.getTagVOList().forEach(hashTagVO -> tagList.add(modelMapper.map(hashTagVO, ProjectTagDTO.class)));
                projectDTO.setTagList(tagList);
            }

            dtoList.add(projectDTO);
        });

        int total = projectMapper.getCount(pageRequestDTO);

        return PageResponseDTO.<ProjectDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();
    }



    @Override
    public Long addLike(ProjectLikeDTO projectLikeDTO) { // 게시글 좋아요 추가
        ProjectLikeVO projectLikeVO = modelMapper.map(projectLikeDTO, ProjectLikeVO.class);
        projectMapper.insertLike(projectLikeVO);

        return projectLikeVO.getPjlNo();
    }

    @Override
    public Boolean removeLike(Long pjlNo) { // 게시글 좋아요 삭제
        Boolean result = projectMapper.deleteLike(pjlNo);
        return result;
    }

    @Override
    public List<ProjectTagDTO> getTopTagList() {
        List<ProjectTagDTO> topTagList = projectMapper.selectTopTagList();
        return topTagList;
    }

    @Override
    public void addHashTag(ProjectTagDTO projectTagDTO) { // 해쉬태그 추가
        ProjectTagVO projectTagVO = modelMapper.map(projectTagDTO, ProjectTagVO.class);
        projectMapper.insertTag(projectTagVO);
    }

    @Override
    public void modifyHashTag(Long projectNo, List<ProjectTagDTO> tagList) { // 해쉬 태그 추가 및 삭제

        projectMapper.deleteTag(projectNo); // 해당 게시글의 태그 모두 삭제
        
        if (tagList.size() > 0) { // 태그 목록이 존재하는 경우
            tagList.forEach(this::addHashTag); // 수정한 태그 목록 DB에 추가
        }
    }
}
