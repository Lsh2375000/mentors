package kr.nomadlab.mentors.question.mapper;

import kr.nomadlab.mentors.question.domain.QuestionVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class QuestionMapperTest {
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void selectMyQuestionList() {
        Long mno = 1L;
        List<QuestionVO> questionVOList = questionMapper.selectMyQuestionList(mno, 0, 10);
        questionVOList.forEach(log::info);
    }
}