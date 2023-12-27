'use strict'

// 댓글 페이징
async function getList({qno, page, size, goLast}){
    const result = await axios.get(`/answer/list/${qno}?page=${page}`, {params: {page, size}})
    if (goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({qno:qno, page:lastPage, size:size})
    }
    return result.data;
}

// 댓글 등록
async function addReply(replyObj){
    console.log('되나?');
    console.log(replyObj);
    const response = await axios.post('/answer', replyObj);
    return response;
}

// 댓글 내용을 눌렀을 때 내용 불러옴!
async function getReply(ano) {
    const response = await axios.get(`/answer/${ano}`);
    return response.data;
}

// 댓글 수정 요청
async function modifyReply(replyObj) {
    const response = await axios.put(`/answer/${replyObj.ano}`, replyObj);
    return response.data;
}

// 댓글 삭제
async function removeReply(ano) {
    const response = await axios.delete(`/answer/${ano}`);
    return response.data;
}

// 답변 채택 요청
async function modifySelect(replyObj) {
    const response = await axios.put(`/answer/select/${replyObj.ano}`, replyObj);
    return response.data;
}