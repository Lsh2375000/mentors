// Qna 게시판 JavaScript
async function getQ1(qnaBoardNo) { // 댓글 달린 글 조회
    const result = await axios.get(`/qnaReplies/list/${qnaBoardNo}`);
    // console.log(result);
    return result.data;
}

async function getQList({qnaBoardNo, page, size, goLast}) { // 댓글 전체 리스트 조회
    const result = await axios.get(`/qnaReplies/list/${qnaBoardNo}?page=${page}`, {params: {page, size}});
    if (goLast) {
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size));

        return getQList({qnaBoardNo:qnaBoardNo, page:lastPage, size:size});
    }
    return result.data;
}

async function addQReply(replyObj) { // 댓글 추가
    const response = await axios.post(`/qnaReplies/`, replyObj);

    return response;
}

async function getQReply(qnaRno) { // 특정 댓글 조회
    const response = await axios.get(`/qnaReplies/${qnaRno}`);
    return response.data;
}

async function modifyQReply(replyObj) { // 댓글 수정
    const response = await axios.put(`/qnaReplies/${replyObj.qnaRno}`, replyObj);
    return response.data;
}

async function removeQReply(qnaRno) { // 댓글 삭제
    const response = await axios.delete(`/qnaReplies/${qnaRno}`);
    return response.data;
}