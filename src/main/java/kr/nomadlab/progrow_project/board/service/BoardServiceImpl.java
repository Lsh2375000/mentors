package kr.nomadlab.progrow_project.board.service;


import kr.nomadlab.progrow_project.board.domain.BoardVO;
import kr.nomadlab.progrow_project.board.dto.BoardDTO;
import kr.nomadlab.progrow_project.board.dto.BoardListReplyCountDTO;
import kr.nomadlab.progrow_project.board.mapper.BoardMapper;
import kr.nomadlab.progrow_project.common.PageRequestDTO;
import kr.nomadlab.progrow_project.common.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Value("${com.example.upload.path}")
    private String uploadPath;


    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;


    @Override
    public void add(BoardDTO boardDTO) {
        log.info(boardDTO);
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        log.info("boardVO: " + boardVO);
        boardMapper.insert(boardVO);
    }



    /*게시물 하나 선택해서 삭제*/
    @Override
    public void removeOne(int boardNo) {
        boardMapper.removeOne(boardNo);
    }

    @Override
    public BoardDTO selectOne(int boardNo, String mode) {
        BoardVO boardVO = boardMapper.selectOne(boardNo);
        boardMapper.hit(boardNo);
        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);
        return boardDTO;
    }


    @Override
    public void modify(BoardDTO boardDTO) {
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.modify(boardVO);
     }

    @Override
    public int getCount(PageRequestDTO pageRequestDTO) {
        return 0;
    }

    /*페이징 기능*/
    @Override
    public PageResponseDTO<BoardListReplyCountDTO> getList(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword=pageRequestDTO.getKeyword();

        List<BoardListReplyCountDTO> dtoList = boardMapper.selectList(pageRequestDTO);
        List<BoardDTO> boardDTOList = new ArrayList<>();

       int total = boardMapper.getCount(pageRequestDTO);




        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();
    }

    @Override
    public List<BoardDTO> myPage(String mid) {
        List<BoardVO> boardVOS = boardMapper.myPage(mid);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardVOS.forEach(boardVO -> boardDTOList.add(modelMapper.map(boardVO, BoardDTO.class)));
        return boardDTOList;
    }


}
