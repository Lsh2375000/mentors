'use strict'

// 질문 추천 추가 요청
async function addVote(qnaVoteDTO) {
    console.log(qnaVoteDTO);
    const response = await axios.post('/question/vote', qnaVoteDTO,{
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}

// 질문 추천 삭제 요청
async function removeVote(vno) {
    console.log(vno);
    const response = await axios.delete(`/question/vote/${vno}`);

    return response.data;
}

// 태그 수정 요청
async function modifyTag(qno, tagList) {
    console.log(qno);
    const response = await axios.put(`/question/tag/${qno}`, tagList);

    return response.data;
}