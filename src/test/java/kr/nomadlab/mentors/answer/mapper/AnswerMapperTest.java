package kr.nomadlab.mentors.answer.mapper;

import kr.nomadlab.mentors.answer.domain.AnswerVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class AnswerMapperTest {
    @Autowired
    private AnswerMapper answerMapper;

    @Test
    void selectMyAnswerList() {
        Long mno = 1L;
        List<AnswerVO> answerVOList = answerMapper.selectMyAnswerList(mno, 0, 10);
        answerVOList.forEach(log::info);
    }
}