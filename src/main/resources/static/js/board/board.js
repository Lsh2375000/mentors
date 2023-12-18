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