document.addEventListener("DOMContentLoaded", (event) =>{
	

	//대분류(업종) 요소 가져오기
	const sectorCategoryOption = document.getElementById("sectorCategory").value;
	//console.log(sectorCategoryOption);
	
	//중분류(지역-시,도 카테고리) 요소 가져오기
	const bigCategoryOption = document.getElementById("bigAreaCategory");
	
	//중분류별 소분류(지역-시,군,구 카테고리) 종류
	const smallCategoryOption = {
		"서울특별시": ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"],
		  "경기도": ["가평군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "양평군", "여주시", "연천군", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시"],
		  "충청북도": ["괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "제천시", "증평군", "진천군", "청주시", "충주시"],
		  "충청남도": ["계룡시", "공주시", "금산군", "논산시", "당진시", "보령시", "부여군", "서산시", "서천군", "아산시", "예산군", "천안시", "청양군", "태안군", "홍성군"],
		  "전라남도": ["강진군", "고흥군", "곡성군", "광양시", "구례군", "나주시", "담양군", "목포시", "무안군", "보성군", "순천시", "신안군", "여수시", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"],
		  "경상북도": ["경산시", "경주시", "고령군", "구미시", "군위군", "김천시", "문경시", "봉화군", "상주시", "성주군", "안동시", "영덕군", "영양군", "영주시", "영천시", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군", "포항시"],
		  "경상남도": ["거제시", "거창군", "고성군", "김해시", "남해군", "밀양시", "사천시", "산청군", "양산시", "의령군", "진주시", "창녕군", "창원시", "통영시", "하동군", "함안군", "함양군", "합천군"],
		  "부산광역시": ["강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"],
		  "인천광역시": ["강화군", "계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "옹진군", "중구"],
		  "대구광역시": ["남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"],
		  "광주광역시": ["광산구", "남구", "동구", "북구", "서구"],
		  "울산광역시": ["남구", "동구", "북구", "울주군", "중구"],
		  "세종특별자치시": ["세종특별자치"],
		  "강원특별자치도": ["강릉시", "고성군", "동해시", "삼척시", "속초시", "양구군", "양양군", "영월군", "원주시", "인제군", "정선군", "철원군", "춘천시", "태백시", "평창군", "홍천군", "횡성군"],
		  "전북특별자치도": ["고창군", "군산시", "김제시", "남원시", "무주군", "부안군", "순창군", "완주군", "익산시", "임실군", "장수군", "전주시", "정읍시", "진안군"],
		  "제주특별자치도": ["서귀포시", "제주시"]
	};
	
	//검색 버튼 클릭 시 필터링 함수 
	document.getElementById("searchButton").addEventListener("click", 
		()=>{
			const sectorCategory = document.getElementById("sectorCategorySelect").value;
			const bigAreaCategory = document.getElementById("bigAreaCategory").value;
			const smallAreaCategory= document.getElementById("smallAreaCategory").value;
			const searchkeyword = document.getElementById("searchInput").value;
		
	console.log(sectorCategory)
	console.log(bigAreaCategory)
	console.log(smallAreaCategory)
	console.log(searchkeyword)
		
		//필터링 요청 
		/*
		fetch(`/board/filter?sector=${encodeURIComponent(sectorCategory)}
							&bigArea=${encodeURIComponent(bigAreaCategory)}
							&smallArea=${encodeURIComponent(smallAreaCategory)}
							&keyword=${encodeURIComponent(searchkeyword)}`)
				.then(response => response.json())
				.then(data =>{
					
					updateBoardList(data);
				});
	*/
		});
		
	
	document.getElementById("bigAreaCategory").addEventListener("change", 
		//중분류에서 가져온 소분류 update해서 카테고리 보여주는 함수
		()=>{
			const bigAreaCategory = document.getElementById("bigAreaCategory").value;
			const smallAreaCategory = document.getElementById("smallAreaCategory");
			console.log(smallAreaCategory);
			smallAreaCategory.innerHTML = "<option value='전체' selected> 전체 </option>";  // 초기화 
		 	
		 	
		    if(bigAreaCategory) {
		        smallCategoryOption[bigAreaCategory].forEach(item => {
		            let option = document.createElement("option");
		            option.value = item;
		            option.text = item;
		            smallAreaCategory.appendChild(option);
		        });
		    }
		}
	)
	
	
	
	
	//검색 버튼 누르면 카테고리별로 필터링되어서 목록을 수정해주는 함수
	function updateBoardList(data){
		const tbody = document.querySelector("#boardTable tbody");
		//const boardFirst = document.querySelector(".boardFirst");
		tbody.innerHTML = "";//기존 목록 삭제
	/*
		if(boardFirst){
		boardFirst.innerHTML = ""; //기존 목록 삭제
	}
	*/
		
		
		data.forEach(board=>{
			const row = tbody.insertRow();
			row.insertCell(0).innerText = board.boardIdx;
			row.insertCell(1).innerText = board.boardTitle;
			row.insertCell(2).innerText = board.usesrIdx;
			row.insertCell(3).innerText = board.boardWriteDate;
			row.insertCell(4).innerText = board.boardView;
		//	row.insertCell(5).innerText = board.boardIdx; 추천수 
		})
	}
	
	//페이지 수 그려주기
	function drawPage(pagination, params){
		if( !pagination || !params){
			document.querySelector(".paging").innerHTML = '';
			throw new Error('Missing required parameters...');
		}
		
		let html = '';
		
		//첫 페이지, 이전 페이지
		
	}
	

})