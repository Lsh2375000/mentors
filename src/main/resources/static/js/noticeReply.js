// Axios를 이용할 때 async / await를 같이 이용하면 비동기 처리를 동기화된 코드처럼 작성할 수 있음.
// async는 함수 선언 시에 사용하는 데 해당 함수가 비동기 처리를 위한 함수라는 것을 명시하기 위해서 사용하고,
// await은 async 함수 내에서 비동기 호출하는 부분에 사용.

// async function get1(num) { // 댓글 목록을 전부 조회하는 컨트롤러
//     const result = await axios.get(`/noticeReplies/list/${num}`);
//     return result.data;
// }

// 댓글의 동적 페이징처리를 위한 함수
async function getList({num, page, size, goLast}) { // 게시글과 페이지 정보를 받아 페이징에 필요한 정보를 넘겨주는 컨트롤러
    const result = await axios.get(`/noticeReplies/list/${num}?page=${page}`, {params: {page, size}});
    // const result = await axios.get(`/noticeReplies/list/${num}`, {params: {page, size}});
    if(goLast) {
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size));

        return getList({num:num, page:lastPage, size:size});
    }
    return result.data;
}

async function addReply(replyObj) { // 하나의 댓글을 생성하는 rest 컨트롤러에 리플 객체를 넘겨서 생성
    const response = await axios.post(`/noticeReplies/`, replyObj);
    return response;
}

async function getReply(rno) { // 하나의 댓글을 조회하는 rest 컨트롤러
    const response = await axios.get(`/noticeReplies/${rno}`);
    return response.data;
}

async function modifyReply(replyObj) { // 리플의 고유값과 리플의 수정값을 가진 객체를 함께 전달해 해당 값으로 수정하는 컨트롤러
    const response = await axios.put(`/noticeReplies/${replyObj.rno}`, replyObj);
    return response.data;
}

async function removeReply(rno) { // 리플의 고유값을 전달해 해당 리플을 삭제하는 컨트롤러
    const response = await axios.delete(`/noticeReplies/${rno}`);
    return response.data;
}