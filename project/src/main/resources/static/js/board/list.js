/*<![CDATA[*/
document.addEventListener("DOMContentLoaded", (event) =>{
	
	window.onload = () => {
		//페이지가 로드 되었을 때, 한 번만 함수를 실행
		findBoardList();
	}
	
	
	

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
	
	//게시글 리스트 조회
	function findBoardList(){
		//1. PagingResponse의 멤버인 List<T> 타입의 list를 의미함
		const list = /*[[${response.list}]]*/ [];
		
		//2. PagingResponse의 멤버인 pagination을 의미
		const pagination = /*[[ ${response.pagination} ]]*/{};
		
		//3. @ModelAttribute를 이용해서 뷰(HTML)로 전달한 BoardListRequest 타입의 객체인 params를 의미
		const params = /*[[ ${params}]]*/"";
		
		//4. 리스트에 출력되는 게시글 번호를 처리하기 위해 사되는 변수(리스트에서 번호는 페이징 정보를 이용해서 계산해야 한다)
		let num = pagination.totalRecordCount - ((params.nowpage -1) * params.recordSize);
		
		//5. 리스트 데이터 렌더링
		drawList(list, num);
		
		//6. 페이지 번호 렌더링
		drawPage(pagination, params);
	}
	
	
	// 리스트 데이터 렌더링하는 HTML 
	function drawList(list, num){
		
		//1. 렌더링 할 HTML을 저장할 변수
		let html = '';
		console.log(list)
		//2. 리스트 데이터 그리기 (board_idx, board_title, board_nickname, 작성날짜, 조회수,)
		//(전체 데이터 수 - ((현재 페이지 번호 -1) * 페이지당 출력할 데이터 개수))
		list.forEach(row=>{
			html += `
				<tr>
					<td>${row.boardIdx}</td>
					<td>${row.boardTitle}</td>
					<td>${row.boardNickname}</td>
					<td>${dayjs(row.boardWriteDate).format('MM-DD HH:,mm')}</td>
					<td>${row.boardView}</td>
				</tr>
			`;
		})
		
		//3. id가 'list'인 요소를 찾아 HTML렌더링
		document.getElementById('list').innerHTML = html;
	}
	
	
	
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
		//1. 필수 파라미터가 없는 경우, 페이지네이션 HTML을 초기화
		if( !pagination || !params){
			document.querySelector(".paging").innerHTML = '';
			throw new Error('Missing required parameters...');
		}
		
		//2. 렌더링 할 HTML을 저장할 변수
		let html = '';
		
		//3. 이전 페이지가 있는 경우, 즉 시작페이지(startPage)가 1이 아닌 경우 첫 페이지 버튼과 이전 페이지 버튼을 HTML에 추가
		if(pagination.existPrevPage){
			html += `
				<a href="javascript:void(0);" onclick="movePage(1)" class="pageBtnFirst">첫페이지</a>
				<a href="javascript:void(0);" onclick="movePage(${pagination.startPage -1})" class="pageBtnPrev">이전페이지</a>
			`;
		}
		
		//4. startPage와 endPage 사이의 페이지번호(i)를 넘버링 하는 로직
		// 페이지 번호(i) 와 현제 페이지번호가 동일한 경우, 페이지번호를 활성화(on)처리
		html += '<p>';
		for (let i = pagination.startPage; i <= pagination.endPage; i++){
			html += (i !== params.page)
				? `<a href="javascript:void(0);" onclick="movePage(${i});> ${i}</a>`
				: `<span class="on">${i}</span>`
		}
		
		//5. 현재 위치한 페이지 뒤에 데이터가 더 있는 경우, 다음 페이지 버튼과 끝 페이지 버튼을 HTML에 추가
		if(pagination.existNextPage){
			html += `
			<a hre="javascript:void(0);" onclick="movePage(${pagination.endPage +1});" class=pageBtnNext> 다음 페이지</a>
			<a hre="javascript:void(0);" onclick="movePage(${pagination.totalPageCount});" class=pageBtnLast> 마지막 페이지</a>
			`;
		}
		
		//6. class가 "paging"인 요소를 찾아 HTML 렌더링 
		document.querySelector('.paging').innerHTML = html;
		
	}
	
	
	//페이지 이동
	function movePage(nowpage){
		//1. drawPage의 각 버튼에 선언된 onclick이벤트를 토해 전다받는 nowpage를 기준으로 객체 생성
		const queryParams = {
			nowpage : (nowpage) ? nowpage : 1,
			recordSize : 10,
			pageSize : 10 
		}
		
		 /*
		 * 2. location.pathname : 리스트 페이지의 URI("/post/list.do")를 의미
		 *    new URLSearchParams(queryParams).toString() : queryParams의 모든 프로퍼티(key-value)를 쿼리 스트링으로 변환
		 *    URI + 쿼리 스트링에 해당하는 주소로 이동
         *    (해당 함수가 리턴해주는 값을 브라우저 콘솔(console)에 찍어보시면 쉽게 이해하실 수 있습니다.)
		 *  라는데 존나 뭐라고 하는지 1도 모르겠음
		                 */
		 location.href = location.pathname + '?' + new URLSearchParams(queryParams).toString();
		
		
	}
	
	

})
/*<![CDATA[*/