'use strict'

// 게시글 좋아요 추가 요청
async function addLike(projectLikeDTO) {
    console.log(projectLikeDTO);
    const response = await axios.post('/project/like', projectLikeDTO,{
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}

// 게시글 좋아요 삭제 요청
async function removeLike(pjlNo) {
    console.log(pjlNo);
    const response = await axios.delete(`/project/like/${pjlNo}`);

    return response.data;
}

// 해쉬태그 수정 요청
async function modifyTag(projectNo, tagList) {
    console.log(projectNo);
    const response = await axios.put(`/project/tag/${projectNo}`, tagList);

    return response.data;
}