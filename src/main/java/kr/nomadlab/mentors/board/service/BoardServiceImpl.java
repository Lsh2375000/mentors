package kr.nomadlab.mentors.board.service;

import kr.nomadlab.mentors.board.domain.BoardLikeVO;
import kr.nomadlab.mentors.board.domain.BoardVO;
import kr.nomadlab.mentors.board.domain.HashTagVO;
import kr.nomadlab.mentors.board.dto.BoardDTO;
import kr.nomadlab.mentors.board.dto.BoardLikeDTO;
import kr.nomadlab.mentors.board.dto.HashTagDTO;
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

        if (boardDTO.getTagList().size() > 0) { // 태그 목록이 존재하는 경우
            List<HashTagDTO> tagList = boardDTO.getTagList();
            tagList.forEach(this::addHashTag); // DB에 태그 저장
        }

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
    public Long addLike(BoardLikeDTO boardLikeDTO) { // 게시글 좋아요 추가
        BoardLikeVO boardLikeVO = modelMapper.map(boardLikeDTO, BoardLikeVO.class);
        boardMapper.insertLike(boardLikeVO);

        return boardLikeVO.getBlNo();
    }

    @Override
    public Boolean removeLike(Long blNo) { // 게시글 좋아요 삭제
        Boolean result = boardMapper.deleteLike(blNo);
        return result;
    }

    @Override
    public void addHashTag(HashTagDTO hashTagDTO) { // 해쉬태그 추가
        HashTagVO hashTagVO = modelMapper.map(hashTagDTO, HashTagVO.class);
        boardMapper.insertTag(hashTagVO);
    }

    @Override
    public void removeHashTag(Long htNo) { // 해쉬태그 삭제
        boardMapper.deleteTag(htNo);
    }

}
