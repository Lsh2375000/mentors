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

        boardMapper.insertBoard(boardVO);

        if (boardDTO.getTagList().size() > 0) { // 태그 목록이 존재하는 경우
            List<HashTagDTO> tagList = boardDTO.getTagList();
            tagList.forEach(hashTagDTO -> {
                hashTagDTO.setBoardNo(boardVO.getBoardNo()); // 게시글 고유번호 추가
                addHashTag(hashTagDTO); // DB에 태그 저장
            });
        }
    }

    @Override
    public void removeBoard(Long boardNo) { // 게시글 삭제
        boardMapper.deleteBoard(boardNo);
    }

    @Override
    public BoardDTO getBoard(Long boardNo, String mode) { // 게시글 조회

        BoardVO boardVO = boardMapper.selectBoard(boardNo);
        List<BoardLikeDTO> likeList = new ArrayList<>();
        List<HashTagDTO> tagList = new ArrayList<>();

        if (mode.equals("view")) { // mode가 view일때만
            boardMapper.updateHit(boardNo); // 조회수 업데이트
            List<BoardLikeVO> likeVOList = boardMapper.selectLikeList(boardNo); // 게시글 좋아요 조회
            likeVOList.forEach(boardLikeVO -> likeList.add(modelMapper.map(boardLikeVO, BoardLikeDTO.class)));
        }
        boardVO.getTagVOList().forEach(hashTagVO -> tagList.add(modelMapper.map(hashTagVO, HashTagDTO.class)));

        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);
        boardDTO.setLikeList(likeList);
        boardDTO.setTagList(tagList);

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

        voList.forEach(boardVO -> {
            BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);

            if (boardVO.getTagVOList().size() > 0) { // 태그 목록이 존재하는 경우
                List<HashTagDTO> tagList = new ArrayList<>(); // 해시 태그 목록을 담을 리스트 객체
                boardVO.getTagVOList().forEach(hashTagVO -> tagList.add(modelMapper.map(hashTagVO, HashTagDTO.class)));
                boardDTO.setTagList(tagList);
            }

            dtoList.add(boardDTO);
        });

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
    public List<HashTagDTO> getTopTagList() {
        List<HashTagDTO> topTagList = boardMapper.selectTopTagList();
        return topTagList;
    }

    @Override
    public void addHashTag(HashTagDTO hashTagDTO) { // 해쉬태그 추가
        HashTagVO hashTagVO = modelMapper.map(hashTagDTO, HashTagVO.class);
        boardMapper.insertTag(hashTagVO);
    }

    @Override
    public void modifyHashTag(Long boardNo, List<HashTagDTO> tagList) { // 해쉬 태그 추가 및 삭제

        boardMapper.deleteTag(boardNo); // 해당 게시글의 태그 모두 삭제
        
        if (tagList.size() > 0) { // 태그 목록이 존재하는 경우
            tagList.forEach(this::addHashTag); // 수정한 태그 목록 DB에 추가
        }
    }

    @Override
    public PageResponseDTO<BoardDTO> getMyBoardList(Long mno, PageRequestDTO pageRequestDTO) { // 본인이 작성한 게시글 목록 조회
        List<BoardVO> voList = boardMapper.selectMyBoardList(mno, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<BoardDTO> dtoList = new ArrayList<>();

        voList.forEach(boardVO -> {
            BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);

            if (boardVO.getTagVOList().size() > 0) { // 태그 목록이 존재하는 경우
                List<HashTagDTO> tagList = new ArrayList<>(); // 해시 태그 목록을 담을 리스트 객체
                boardVO.getTagVOList().forEach(hashTagVO -> tagList.add(modelMapper.map(hashTagVO, HashTagDTO.class)));
                boardDTO.setTagList(tagList);
            }

            dtoList.add(boardDTO);
        });

        int total = boardMapper.getMyCount(mno);

        return PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();
    }
}
