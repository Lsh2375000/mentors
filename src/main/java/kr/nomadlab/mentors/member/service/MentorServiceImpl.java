package kr.nomadlab.mentors.member.service;


import kr.nomadlab.mentors.member.domain.MentorVO;
import kr.nomadlab.mentors.member.dto.MentorDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.member.mapper.MentorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class MentorServiceImpl implements MentorService{
    private final MentorMapper mentorMapper;
    private final ModelMapper modelMapper;
    private final MemberMapper memberMapper;
    @Value("${kr.nomadlab.upload.path}")
    private String uploadPath;

    @Override
    public void add(MentorDTO mentorDTO, List<MultipartFile> files) {
        log.info("MentorService add()...");
        log.info(files);
        if (files.size() > 0) {
            StringBuilder str = new StringBuilder();
            for(MultipartFile file : files) {
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + file.getOriginalFilename());
                str.append(uuid).append("_").append(file.getOriginalFilename()).append("|");
                try {
                    file.transferTo(savePath);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            mentorDTO.setFileNames(str.toString());
            log.info("입력된 첨부파일 : " + str.toString());
        }

        MentorVO mentorVO = modelMapper.map(mentorDTO, MentorVO.class);
        Integer role_set = 0; // ROLE_MENTOR로 저장
        memberMapper.addMemberRole(mentorVO.getMemberId(), role_set);
        mentorMapper.insert(mentorVO);
    }

    @Override
    public List<MentorDTO> getAll() {
        log.info("MentorService getAll()...");

        List<MentorVO> mentorVOList = mentorMapper.selectAll();
        List<MentorDTO> mentorDTOList = new ArrayList<>();
        mentorVOList.forEach(mentorVO -> mentorDTOList.add(modelMapper.map(mentorVO, MentorDTO.class)));
        return mentorDTOList;
    }



    @Override
    public MentorDTO getOne(String memberId) {
        log.info("MentorService getOne()...");

        MentorVO mentorVO = mentorMapper.selectOne(memberId);
        MentorDTO mentorDTO = modelMapper.map(mentorVO, MentorDTO.class);

        return mentorDTO;
    }


    @Override
    public void modify(MentorDTO mentorDTO, List<MultipartFile> files) {
        log.info("MentorService modify()...");
        log.info(files);

        if (files.size() > 0) {
            log.info("파일을 등록하면");
            StringBuilder str = new StringBuilder();
            MentorDTO mentorDTO1 = getOne(mentorDTO.getMemberId());
            str.append(mentorDTO1.getFileNames());
            for(MultipartFile file : files) {
                if (file.getOriginalFilename().indexOf(".pdf") > -1) {
                    // 아무 파일을 넣지 않고 수정했을 경우에 확장자를 확인하고 없다면 빈문자열을 넣는다.
                    String uuid = UUID.randomUUID().toString();
                    Path savePath = Paths.get(uploadPath, uuid + "_" + file.getOriginalFilename());
                    str.append(uuid).append("_").append(file.getOriginalFilename()).append("|");
                    try {
                        file.transferTo(savePath);
                    } catch (IOException e){
                        log.error(e.getMessage());
                    }
                }
            }
            mentorDTO.setFileNames(str.toString());
        }

        MentorVO mentorVO = modelMapper.map(mentorDTO, MentorVO.class);
        mentorMapper.update(mentorVO);
    }



    @Override
    public void remove(String memberId) {
        log.info("MentorService remove()....");
        mentorMapper.delete(memberId);
    }


}
