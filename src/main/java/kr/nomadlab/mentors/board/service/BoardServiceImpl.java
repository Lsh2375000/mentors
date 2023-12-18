package kr.nomadlab.mentors.board.service;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.mapper.BoardMapper;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;

    @Override
    public void registerBoard(BoardDTO boardDTO) { // 게시글 등록
        log.info("registerBoard...");
        log.info(boardDTO);

        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.insertBoard(boardVO);
    }

    @Override
    public void removeBoard(Long boardNo) { // 게시글 삭제
        boardMapper.deleteBoard(boardNo);
    }

    @Override
    public BoardDTO getBoard(Long boardNo, String mode) { // 게시글 조회

        BoardVO boardVO = boardMapper.selectBoard(boardNo);
        List<BoardLikeDTO> likeList = new ArrayList<>();

        if (mode.equals("view")) { // mode가 view일때만
            boardMapper.updateHit(boardNo); // 조회수 업데이트
            List<BoardLikeVO> likeVOList = boardMapper.selectLikeList(boardNo); // 게시글 좋아요 조회
            likeVOList.forEach(boardLikeVO -> likeList.add(modelMapper.map(boardLikeVO, BoardLikeDTO.class)));
        }

        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);
        boardDTO.setLikeList(likeList);

        return boardDTO;
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) { // 게시글 수정
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.updateBoard(boardVO);
    }

    @Override
    public PageResponseDTO<BoardDTO> getBoardList(PageRequestDTO pageRequestDTO) { // 전체 게시물 조회
        List<BoardVO> voList = boardMapper.selectBoardList(pageRequestDTO);
        List<BoardDTO> dtoList = new ArrayList<>();

        voList.forEach(boardVO -> dtoList.add(modelMapper.map(boardVO, BoardDTO.class)));

        int total = boardMapper.getCount(pageRequestDTO);
        return PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();
    }

    @Override
    public void addLike(BoardLikeDTO boardLikeDTO) { // 게시글 좋아요 추가
        BoardLikeVO boardLikeVO = modelMapper.map(boardLikeDTO, BoardLikeVO.class);
        boardMapper.insertLike(boardLikeVO);
    }

//    @Override
//    public List<BoardDTO> myPage(String mid) {
//        List<BoardVO> boardVOS = boardMapper.myPage(mid);
//        List<BoardDTO> boardDTOList = new ArrayList<>();
//        boardVOS.forEach(boardVO -> boardDTOList.add(modelMapper.map(boardVO, BoardDTO.class)));
//        return boardDTOList;
//    }

}
