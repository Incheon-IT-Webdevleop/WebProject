$(document).ready(function() {
	$('#userId').on('blur', function() {
	        var userId = $(this).val();
	        if (userId) {
	            checkDuplicate('id', userId);
	        }
	    });

	    // 이메일 중복 검사
	    $('.email-container input[type="email"]').on('blur', function() {
	        var userEmail = $(this).val();
	        if (userEmail) {
	            checkDuplicate('email', userEmail);
	        }
	    });
		
		$('.email-container input[type="email"]').on('blur', function() {
		    var userEmail = $(this).val();
		    if (userEmail) {
		        checkDuplicate('email', userEmail);
		    }
		});

	    // 중복 검사 함수
	    function checkDuplicate(type, value) {
	        $.ajax({
	            url: '/api/check-duplicate', // 실제 서버의 중복 검사 API 주소로 변경해야 합니다
	            method: 'POST',
	            data: { type: type, value: value },
	            success: function(response) {
					console.log(response.isDuplicate)
					var message = '';
	                if (response.isDuplicate) {
						if(type === 'id'){
							message='이미 사용 중인 아이디입니다.';
						}else if(type==='email'){
							message = '이미 사용 중인 이메일입니다.';
						}else if(type==='nickname'){
							message = '이미 사용중인 닉네임입니다.';
						}
						
						if(type==='nickname'){
							$('#duplication-nickname').text(message).show();
						}else{
							$('#duplication').text(message).show();
						}
	
	               }
	            },
	            error: function() {
	                alert('중복 검사 중 오류가 발생했습니다. 다시 시도해 주세요.');
	            }
	        });
	    }
	// 회원가입 버튼을 눌렀을 경우 작동하는 이벤트
	// 회원가입에 성공했을 때 {"result":"success"}
    $('#register-form').on('submit', function(event) {
        event.preventDefault();
        var $form = $(this);
        $.ajax({
            type: $form.attr('method'),
            url: $form.attr('action'),
            data: $form.serialize(),
            success: function(response) {
                alert("회원가입에 성공하셨습니다.");
                // 로그인 성공 시 이전 페이지로 이동
                var url = '/login'; // 기본적인 리디렉션 URL
                window.location.href = url; // 리디렉션
            },
            error: function(xhr) {
               /* $('#duplication').text(xhr.responseText).show();*/
            }
        });
    });
});
