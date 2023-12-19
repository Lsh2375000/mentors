package kr.nomadlab.mentors.board.service;

import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    void registerBoardTest() { // 게시글 등록 테스트
        BoardDTO boardDTO = BoardDTO.builder()
                .memberId("test6789")
                .writer("닉네임")
                .title("테스트 제목")
                .content("테스트 내용")
                .build();
        boardService.registerBoard(boardDTO);
    }

    @Test
    void removeBoardTest() { // 게시글 삭제 테스트
        Long boardNo = 4L;
        boardService.removeBoard(boardNo);
    }

    @Test
    void getBoardTest() { // 게시글 조회 테스트
        Long boardNo = 4L;
        String mode = "view";
        BoardDTO boardDTO = boardService.getBoard(boardNo, mode);
        log.info(boardDTO);
    }

    @Test
    void modifyBoardTest() { // 게시글 수정 테스트
        Long boardNo = 4L;
        String mode = "modify";
        BoardDTO boardDTO = boardService.getBoard(boardNo, mode);
        boardDTO.setTitle("테스트 제목 수정");
        boardService.modifyBoard(boardDTO);
    }

    @Test
    void getBoardListTest() { // 게시글 목록 테스트
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("t")
                .keyword("테스트")
                .build();
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getBoardList(pageRequestDTO);
        log.info(pageResponseDTO);
    }
}