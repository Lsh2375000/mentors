package kr.nomadlab.mentors.notify.service;

import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.notify.dto.NotifyDto;
import kr.nomadlab.mentors.notify.mapper.NotifyMapper;
import kr.nomadlab.mentors.notify.vo.NotifyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotifyServiceImpl implements NotifyService{
    private final ModelMapper modelMapper;
    private final NotifyMapper notifyMapper;

    private final MemberMapper memberMapper;

    @Override
    public void addNotify(Long mno, String types) {
        String message = null;
        if(types.equals("payments")){
            message = "결제에 성공하였습니다";
        }

        NotifyVO notifyVO = NotifyVO.builder()
                .receiverMno(mno)
                .types(types)
                .content(message)
                .build();
        notifyMapper.addNotify(notifyVO);
    }

    @Override
    public int countNotify(Long mno) {
        int count = notifyMapper.countNotify(mno);
        return count;
    }

    @Override
    public void readNotify(Long mno) {
        notifyMapper.readNotify(mno);
    }

    @Override
    public List<NotifyDto> getNotReadNotify(Long mno) {
        List<NotifyVO> notifyVOList = notifyMapper.getNotReadNotify(mno);
        List<NotifyDto> notifyDtoList = new ArrayList<>();
        notifyVOList.forEach(notifyVO -> {
            NotifyDto notifyDto =modelMapper.map(notifyVO, NotifyDto.class);
            notifyDtoList.add(notifyDto);
        });
        return notifyDtoList;
    }

    @Override
    public void passNotify(NotifyDto notifyDto, Long mno) {
        String message = null;

        log.info("this is mentoring" + mno);
        if(notifyDto.getTypes().equals("mentoring")){
            String nickName = memberMapper.getNickName(mno);
            message = nickName + "님이 멘토링을 신청하셨습니다.";
        }
        if(notifyDto.getTypes().equals("mentorApply")){
            message = "멘토가 되었습니다.";
        }
        NotifyVO notifyVO = NotifyVO.builder()
                .receiverMno(notifyDto.getReceiverMno())
                .sendMno(mno)
                .types(notifyDto.getTypes())
                .typesNo(notifyDto.getTypesNo())
                .content(message)
                .build();
        notifyMapper.addNotify(notifyVO);
    }
}
