package kr.nomadlab.mentors.main.service;

import kr.nomadlab.mentors.chat.mapper.ChatRoomMapper;
import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.domain.MainVO;
import kr.nomadlab.mentors.main.dto.MainDTO;
import kr.nomadlab.mentors.main.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MainServiceImpl implements MainService{

    private final ModelMapper modelMapper;
    private final MainMapper mainMapper;
    private final ChatRoomMapper chatRoomMapper;


    @Override
    public void register(MainDTO mainDTO) { // 게시물 등록
        MainVO mainVO = modelMapper.map(mainDTO, MainVO.class);
        mainMapper.insert(mainVO);
    }
    @Override
    public int mentoringCnt(Long mno) { // 멘토링 횟수

        return mainMapper.getMentoringCnt(mno);
    }


    @Override
    public PageResponseDTO<MainDTO> list(PageRequestDTO pageRequestDTO) { // 게시물 목록
        log.info("무료/유료 ? "+pageRequestDTO.getPaidFree());

        List<MainVO> voList = mainMapper.list(pageRequestDTO);
        log.info(voList);
        List<MainDTO> dtoList = new ArrayList<>();

        voList.forEach(mainVO -> {
            MainDTO mainDTO = modelMapper.map(mainVO, MainDTO.class);
            dtoList.add(mainDTO);
        });

        int total = mainMapper.getCount(pageRequestDTO);

        PageResponseDTO<MainDTO> responseDTO = PageResponseDTO.<MainDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return responseDTO;
    }



    @Override
    public MainDTO getBoard(Long mbNo) { // 상세뷰
        MainVO mainVO = mainMapper.getOne(mbNo);
        MainDTO mainDTO = modelMapper.map(mainVO, MainDTO.class);

        return mainDTO;
    }

    @Override
    public void modifyBoard(MainDTO mainDTO) { // 수정
        MainVO mainVO = modelMapper.map(mainDTO, MainVO.class);
        mainMapper.updateOne(mainVO);
    }

    @Override
    public void removeOne(Long mbNo) { // 삭제
        mainMapper.deleteOne(mbNo);
    }

    @Override
    public boolean isMentoring(Long mno) { // 멘토가 작성한 글이 있는지
        boolean isMentoring;

        if(mainMapper.isMentoring(mno) == 0){
            isMentoring = false;
        }else{
            isMentoring = true;
        }
        return isMentoring;
    }

    @Override
    public void updateCurPeople(String roomId) { // 현재 인원 업데이트
        mainMapper.updateCurPeople(roomId);
        log.info("현재인원 업데이트 서비스");
    }

    @Override
    public PageResponseDTO<MainDTO> myPageList(PageRequestDTO pageRequestDTO, Long mno) { // 마이페이지에서 멘토가 작성한 글 목록보기

        List<MainVO> voList = mainMapper.myPageList(pageRequestDTO.getSize(), pageRequestDTO.getSkip(), mno);

        List<MainDTO> dtoList = new ArrayList<>();

        voList.forEach(mainVO -> {
            MainDTO mainDTO = modelMapper.map(mainVO, MainDTO.class);
            dtoList.add(mainDTO);
        });

        int total = mainMapper.myPageCount(pageRequestDTO.getSize(), pageRequestDTO.getSkip(), mno);

        PageResponseDTO<MainDTO> responseDTO = PageResponseDTO.<MainDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return responseDTO;
    }

    @Override
    public PageResponseDTO<MainDTO> mainListTee(PageRequestDTO pageRequestDTO, Long mno) { // 멘티가 참가중인 멘토링 목록
        List<MainVO> voList = mainMapper.mainListTee(pageRequestDTO.getSize(), pageRequestDTO.getSkip(), pageRequestDTO.getSort(), mno);
        log.info("서비스 sort ?"+pageRequestDTO.getSort());
        List<MainDTO> dtoList = new ArrayList<>();

        voList.forEach(mainVO -> {
            MainDTO mainDTO = modelMapper.map(mainVO, MainDTO.class);
            dtoList.add(mainDTO);
        });

        int total = mainMapper.mainListTeeCnt(pageRequestDTO.getSize(), pageRequestDTO.getSkip(), pageRequestDTO.getSort(), mno);

        PageResponseDTO<MainDTO> responseDTO = PageResponseDTO.<MainDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return responseDTO;
    }


}
