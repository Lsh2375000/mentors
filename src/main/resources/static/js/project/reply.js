'use strict'

//댓글 페이징
async function getList({projectNo, page, size, goLast}){
    const result = await axios.get(`/projectReplies/list/${projectNo}?page=${page}`, {params: {page, size}})
    if (goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({projectNo:projectNo, page:lastPage, size:size})
    }
    return result.data;
}

// 댓글 등록
async function addReply(replyObj){
    console.log('되나?');
    console.log(replyObj);
    const response = await axios.post('/projectReplies', replyObj);
    return response;
}

//댓글 내용을 눌렀을 때 내용 불러옴!
async function getReply(rno) {
    const response = await axios.get(`/projectReplies/${rno}`);
    return response.data;
}

async function modifyReply(replyObj) {
    const response = await axios.put(`/projectReplies/${replyObj.pjrNo}`, replyObj);
    return response.data;
}

// 댓글 삭제
async function removeReply(pjrNo){
    const response = await axios.delete(`/projectReplies/${pjrNo}`);
    return response.data;
}


