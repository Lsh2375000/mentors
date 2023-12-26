'use strict'

// 채팅방 조회 요청
async function getRoomMessages(roomId) {
    console.log('axios: ' + roomId);
    const response = await axios.get('/api/chat/room',{params: {roomId: roomId}});

    return response.data;
}

// 채팅방 목록 조회
async function getRooms(mno) {
    try {
        console.log('mno', mno);
        const response = await axios.get('/api/chat/rooms', { params: { mno: mno } });

        console.log(response);

        return response.data;
    } catch (error) {
        console.error('Error while fetching chat rooms:', error);
        throw error; // You can handle the error based on your application's requirements.
    }
}
