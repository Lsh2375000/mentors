package kr.nomadlab.mentors.project.mapper;

import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.project.domain.ProjectVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProjectMapperTest {
    @Autowired
    private ProjectMapper projectMapper;

    @Test
    void selectMyProjectList() {
        Long mno = 1L;
        List<ProjectVO> projectList = projectMapper.selectMyProjectList(mno, 0, 10);
        projectList.forEach(log::info);
    }
}