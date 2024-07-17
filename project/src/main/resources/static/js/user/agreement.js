$(document).ready(function() {
		
	$('.btn').on('click', function(event){
		
		if(!$('#agreeTerms').is(':checked')){
			$('.error-message').text("이용약관에 동의해주세요.").show();
			$(this).focus();
			return;
		}
		if(!$('#agreePrivacy').is(':checked')){
			$('.error-message').text("개인정보 처리방침에 동의해주세요.").show();
			$(this).focus();
			return;
		}
		
		location.href = '/register';
	})
});