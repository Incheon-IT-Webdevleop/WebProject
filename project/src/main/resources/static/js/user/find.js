$(document).ready(function() {
    // URL 파라미터에서 type 값을 가져옵니다.
    const urlParams = new URLSearchParams(window.location.search);
    const type = urlParams.get('type');

    // type에 따라 초기 탭을 설정합니다.
    if (type === 'password') {
        $("#pwTab").addClass("active");
        $("#idTab").removeClass("active");
        $("#pw-form-container").show();
        $("#id-form-container").hide();
    } else {
        $("#idTab").addClass("active");
        $("#pwTab").removeClass("active");
        $("#id-form-container").show();
        $("#pw-form-container").hide();
    }

    $("#idTab").click(function() {
        $(this).addClass("active");
        $("#pwTab").removeClass("active");
        $("#id-form-container").show();
        $("#pw-form-container").hide();
		$(".error-message").hide();
    });

    $("#pwTab").click(function() {
        $(this).addClass("active");
        $("#idTab").removeClass("active");
        $("#pw-form-container").show();
        $("#id-form-container").hide();
		$(".error-message").hide();
    });
	
	// 아이디 찾기 폼 제출
	// 아이디 찾기일 경우
	// req1은 이름, req2는 이메일
	 $("#id-form").submit(function(e) {
		e.preventDefault();
		
		// 아이디 찾기 폼에서 value
		let $idFormName = $("#id-form input[placeholder='이름']").val();
		let $idFormEmail = $("#id-form input[placeholder='이메일']").val();
		
		
		// 유효성 검사
		if(!$idFormName || !$idFormEmail){
			$(".error-message").html("빈칸이 존재합니다.<br> 모두 입력하신후 다시 시도해주시길 바랍니다.").show();
			return;
		}
		$(".error-message").hide();
		

     	$.ajax({
        	url: '/find',
	        type: 'POST',
	        data: $(this).serialize(),
	        success: function(response) {
				if(response.message ==="emptyIdError"){		
		             $(".error-message").html("잘못 입력하셨습니다<br>해당하는 정보가 존재하지 않습니다.").show();
				}else {
					$(".error-message").hide();
					$("#id-form-container").hide();
					$("#show-find-container").show();
					$("#id-find-result").val(response.id).show();
				}
	         },
	         error: function() {
	             $(".error-message").text("아이디 찾기에 실패했습니다.").show();
	         }
	     });
	 });
	 
     // 비밀번호 찾기 폼 제출
	 // 비밀번호 찾기일 경우 
	 // req1은 아이디
	 // req2는 이메일
     $("#pw-form").submit(function(e) {
         e.preventDefault();
		 
		 // 비밀번호 찾기 폼에서 value
 		let $pwdFormId = $("#pw-form-container input[placeholder='아이디']").val();
 		let $pwdFormEmail = $("#pw-form-container input[placeholder='이메일']").val();
		 		
		if(!$pwdFormId || $pwdFormEmail){
			$(".error-message").html("빈칸이 존재합니다.<br> 모두 입력하신후 다시 시도해주시길 바랍니다.").show();
			return;
		}
		$(".error-message").hide();
		 
         $.ajax({
             url: '/find',
             type: 'POST',
             data: $(this).serialize(),
             success: function(response) {
                 if(response.result === 'emptyPwdError'){
					$(".error-message").html("잘못 입력하셨습니다<br>해당하는 정보가 존재하지 않습니다.").show();
				 }else{
					
				 }
             },
             error: function() {
                 $(".error-message").html("비밀번호 찾기에 실패했습니다.").show();
             }
         });
     });
	
});