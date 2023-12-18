'use strict'

//댓글 페이징
async function getList({boardNo, page, size, goLast}){
    const result = await axios.get(`/replies/list/${boardNo}?page=${page}`, {params: {page, size}})
    if (goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({boardNo:boardNo, page:lastPage, size:size})
    }
    return result.data;
}

// 댓글 등록
async function addReply(replyObj){
    console.log('되나?');
    console.log(replyObj);
    const response = await axios.post('/replies', replyObj);
    return response;
}

//댓글 내용을 눌렀을 때 내용 불러옴!
async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`);
    return response.data;
}

async function modifyReply(replyObj) {
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj);
    return response.data;
}

// 댓글 삭제
async function removeReply(rno){
    const response = await axios.delete(`/replies/${rno}`);
    return response.data;
}


