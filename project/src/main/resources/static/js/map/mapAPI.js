   function initMap() {
            var container = document.getElementById('map');
            var currentPolygon = null;
            var options = {
                center: new kakao.maps.LatLng(37.57032009405621, 126.97880973527991),
                level: 11
            };
            var map = new kakao.maps.Map(container, options);

            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                var latlng = mouseEvent.latLng;
                var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
                message += '경도는 ' + latlng.getLng() + ' 입니다';
                console.log(message);
            });

            // JSON 파일 로드
            fetch('data/emd.json')
            .then(response => response.json())
            .then(data => {
                data.features.forEach(feature => {
                    if (feature.geometry === null) {
                        console.warn('Geometry 값이 null 입니다.', feature);
                        return; // null 값인 행정구역 스킵하고 넘어가기

                    }
                    var coordinatesArray = feature.geometry.coordinates;

                    coordinatesArray.forEach(coordinateSet => {
                        var coordinates = coordinateSet.map(coord => new kakao.maps.LatLng(coord[1], coord[0]));

                        // json에 저장된 경계구역 꼭지점 좌표들로 폴리곤 그리기
                        var name = feature.properties.EMD_KOR_NM; // 읍면동 이름 가져오기
                        console.log(name + " 좌표 이상있는 구문");

                        // 폴리곤 생성 (초기에는 보이지 않도록 설정)
                        var polygon = new kakao.maps.Polygon({
                            map: map,
                            path: coordinates,
                            strokeWeight: 2,
                            strokeColor: 'rgba(0, 0, 0, 0)', // 초기 경계선 색상 투명
                            strokeOpacity: 0.8,
                            fillColor: 'rgba(0, 0, 0, 0)', // 초기 채우기 색상 투명
                            fillOpacity: 0.3
                        });

                        // 마우스 오버 이벤트
                        kakao.maps.event.addListener(polygon, 'mouseover', function(mouseEvent) {
                            if (currentPolygon !== polygon) {
                                if (currentPolygon) {
                                    currentPolygon.setOptions({
                                        fillColor: 'rgba(0, 0, 0, 0)', // 초기 채우기 색상으로 복원
                                        strokeColor: 'rgba(0, 0, 0, 0)' // 초기 경계선 색상으로 복원
                                    });
                                }
                                polygon.setOptions({
                                    fillColor: '#ADD8E6', // 경계지역 연한 하늘색
                                    strokeColor: '#0000FF' // 경계선 진한 파란색
                                });
                                currentPolygon = polygon;
                            }
                        });

                        // 마우스 아웃 이벤트
                        kakao.maps.event.addListener(polygon, 'mouseout', function() {
                            if (currentPolygon === polygon) {
                                polygon.setOptions({
                                    fillColor: 'rgba(0, 0, 0, 0)', // 투명으로 복원
                                    strokeColor: 'rgba(0, 0, 0, 0)' // 투명으로 복원
                                });
                                currentPolygon = null; // 현재 폴리곤 null로 변경
                            }
                        });
                    });
                });
            })
            .catch(error => console.error('행정구역 경계정보 로딩 실패: ', error));
        }