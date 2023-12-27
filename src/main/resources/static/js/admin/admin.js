//리스트 불러오기 (검색)
async function getList(types, keyword, up, down, from, to){
    console.log(types, keyword, up, down, from, to);
    const response = await axios.get(`/admin/exchangeList?types=${types}&keyword=${keyword}&up=${up}&down=${down}&from=${from}&to=${to}`);
    console.log(response);
    return response.data;
}

//리스트 불러오기 (검색)
async function getExchangedList(types, keyword, up, down, from, to){
    console.log(types, keyword, up, down, from, to);
    const response = await axios.get(`/admin/exchangedList?types=${types}&keyword=${keyword}&up=${up}&down=${down}&from=${from}&to=${to}`);
    console.log(response);
    return response.data;
}