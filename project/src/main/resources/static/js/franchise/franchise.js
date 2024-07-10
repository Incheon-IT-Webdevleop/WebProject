// franchise.js

// formatMoney 함수 정의
function formatMoney(amount) {
    let formatted = '';

    if (amount >= 100000000) { // 1억 이상
        let billions = Math.floor(amount / 100000000);
        let millions = Math.floor((amount % 100000000) / 10000);
        formatted = `${billions}억`;
        if (millions > 0) {
            formatted += `${millions.toLocaleString()}만`;
        }
    } else if (amount >= 10000) { // 1만 이상 1억 미만
        let millions = Math.floor(amount / 10000);
        let remainder = amount % 10000;
        formatted = `${millions.toLocaleString()}만`;
        if (remainder > 0) {
            formatted += `${remainder.toLocaleString()}`;
        }
    } else { // 1만 미만
        formatted = amount.toLocaleString();
    }

    return formatted + '원';
}

// 페이지가 로드될 때 실행되는 함수
window.onload = function() {
    // Franchise 데이터 가져오기
    let avgSalesElement = document.querySelector('.franchise-description .avg-sales');
    let startupCostElement = document.querySelector('.franchise-description .startup-cost');
    
    // 값 포맷
    let avgSales = parseInt(avgSalesElement.textContent.replace(/,/g, ''));
    let startupCost = parseInt(startupCostElement.textContent.replace(/,/g, ''));
    
    avgSalesElement.textContent = formatMoney(avgSales);
    startupCostElement.textContent = formatMoney(startupCost);
};
