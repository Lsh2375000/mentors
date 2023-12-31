'use strict'

// 게시글 좋아요 추가 요청
async function addLike(boardLikeDTO) {
    console.log(boardLikeDTO);
    const response = await axios.post('/board/like', boardLikeDTO,{
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}

// 게시글 좋아요 삭제 요청
async function removeLike(blNo) {
    console.log(blNo);
    const response = await axios.delete(`/board/like/${blNo}`);

    return response.data;
}

// 해쉬태그 수정 요청
async function modifyTag(boardNo, tagList) {
    console.log(boardNo);
    const response = await axios.put(`/board/tag/${boardNo}`, tagList);

    return response.data;
}