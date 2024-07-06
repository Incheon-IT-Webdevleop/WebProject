// 마우스 이벤트를 감지하여 사이드바를 보여주는 함수
		
	document.addEventListener('mousemove', function(event) {
		   
	 var sidebar = document.getElementById('sidebar');   
	 var dragRange = 50; // 사이드바가 나타날 오른쪽으로의 드래그 거리 임계값
	
	// 마우스가 왼쪽 사이드 영역에서 오른쪽으로 threshold 이상 드래그했을 때
    if (event.clientX < dragRange) {
    	sidebar.style.left = '0'; // 사이드바를 화면 내에 나타나도록 left 값을 0으로 변경
    } else {
        sidebar.style.left = '-200px'; // threshold 미만으로 드래그할 경우 다시 사이드바를 왼쪽으로 숨김
    }
});