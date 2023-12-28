package kr.nomadlab.mentors.chat.service;

import kr.nomadlab.mentors.chat.dto.ChatListDTO;
import kr.nomadlab.mentors.chat.dto.ChatMessageDTO;
import kr.nomadlab.mentors.chat.dto.ChatRoomDTO;
import kr.nomadlab.mentors.chat.mapper.ChatMessageMapper;
import kr.nomadlab.mentors.chat.mapper.ChatRoomMapper;
import kr.nomadlab.mentors.main.mapper.MainMapper;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.payInfo.mapper.PayInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatService{

    private final ChatRoomMapper chatRoomMapper;
    private final ChatMessageMapper chatMessageMapper;
    private final MainMapper mainMapper;
    private final PayInfoMapper payInfoMapper;
    private final MemberMapper memberMapper;

    // 보낸 메세지
    public ChatMessageDTO sendMessage(ChatMessageDTO messageDTO) {
        chatMessageMapper.insertMessage(messageDTO); // db에 채팅 메세지 정보 저장
        log.info("sendMessage...");
        log.info(messageDTO.getCmNo());

        return messageDTO;
    }

    // 메세지 조회
    public List<ChatMessageDTO> getMessages(String roomId) {

        return chatMessageMapper.selectMessageList(roomId);
    }

    // 채팅방 생성
    public ChatRoomDTO createChatRoom(Long mno, String name, int maxMembers) {
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.create(mno, name, maxMembers);
        chatRoomMapper.insertChatRoom(chatRoomDTO); // 채팅방 저장
        
        ChatListDTO chatListDTO = ChatListDTO.builder()
                .mno(chatRoomDTO.getMno())
                .roomId(chatRoomDTO.getRoomId())
                .role("LEADER")
                .build();
        
        chatRoomMapper.insertChatList(chatListDTO); // 채팅방 참여 인원 저장

        return chatRoomDTO;
    }

    // 채팅방 초대
    public void inviteChatRoom(Long mno, String roomId, String nickname) {
        chatRoomMapper.insertChatList(ChatListDTO.builder()
                .mno(mno)
                .nickname(nickname)
                .roomId(roomId)
                .role("MEMBER")
                .build());
    }

    // 채팅 목록 조회
    public List<ChatRoomDTO> getRoomList(Long mno) {
        return chatRoomMapper.selectRoomList(mno);
    }

    // 채팅방 아이디와 일치하는 채팅방 조회
    public ChatRoomDTO getRoom(String roomId) {
        return chatRoomMapper.selectRoomById(roomId);
    }

    // 채팅방에 참여한 회원 목록 조회
    @Override
    public List<ChatListDTO> getChatMembers(String roomId) {
        return chatRoomMapper.selectChatMembers(roomId);
    }

    // 채팅방에 해당 회원 존재 여부 확인
    @Override
    public Boolean findMemberInRoom(String roomId, Long mno) {
        return chatRoomMapper.findMemberInRoom(roomId, mno);
    }

    // 채팅방에서 나간 회원정보 삭제
    @Override
    public void removeChatMember(String roomId, Long mno, Long mbNo) {
        ChatListDTO chatListDTO = ChatListDTO.builder()
                .roomId(roomId)
                .mno(mno)
                .build();
        chatRoomMapper.deleteChatMember(chatListDTO);
        mainMapper.updateCurPeople(roomId); // 메인 게시판 현재인원 업데이트

        if (chatListDTO.getRole().equals("LEADER")) { // 방장이 채팅방을 나갈경우
            chatRoomMapper.updateLeaderMno(chatListDTO.getRoomId());
        }
    }
}
