// 이메일을 입력 받는 input 태그의 id : email
// 인증받기 버튼 태그의 id : emailAuth
// 인증 번호를 입력 받는 input 태그의 id : authCode
// 입력한 인증 번호와 이메일로 받은 인증 번호가 일치하는지 여부를 보여주는 span 태그의 id : emailAuthWarn
// 이메일 정규식 검사

document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('span.btnFindZipcode').addEventListener('click', execDaumPostcode);

    function execDaumPostcode() {
        /* 상황에 맞춰서 변경해야 하는 부분 */
        const zipcode = document.getElementById('zipcode');
        const address01 = document.getElementById('address01');
        const address02 = document.getElementById('address02');

        /* 수정없이 사용 하는 부분 */
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if (data.userSelectedType === 'R') {
                    //법정동명이 있을 경우 추가한다.
                    if (data.bname !== '') {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if (data.buildingName !== '') {
                        extraAddr += (extraAddr !== '' ? ' / ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                zipcode.value = data.zonecode; //5자리 새우편번호 사용
                address01.value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                address02.focus();
            }
        }).open();
    }

    $("#email").on("keyup", function () {
        console.log("이메일 인식함")
        let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        //console.log("email : "+$memail.val());
        if (!regExp.test($("#email").val())) {
            // console.log("형식 미확인");
            emchk = false;

            $("#mailTxt").html("<span id='chkmail'>이메일 형식이 맞지 않습니다</span>");

            $("#chkmail").css({
                "color": "#FA3E3E",
                "font-weight": "bold",
                "font-size": "15px",
                "position": "absolute",
                "padding-top": "40px",
                "z-index" : "-1"
            });

        } else {
            emchk = true;

            console.log("형식 확인");
            $("#mailTxt").html("<span id='chkmail'>이메일 형식을 확인했습니다</span>")

            $("#chkmail").css({
                "color": "#0D6EFD",
                "font-weight": "bold",
                "font-size": "15px",
                "position": "absolute",
                "padding-top": "40px",
                "z-index" : "-1"
            })
        }
    });

});
