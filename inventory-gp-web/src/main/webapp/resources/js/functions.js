function handlePaymentFormChange(xhr, status, args) {          
	alert('1'); 
}

function setFormatPriceForAll() {

	setFormatPrice('cash-tab\\:txtValue');
	setFormatPrice('cash-tab\\:txtCashOutflow');
	setFormatPrice('cash-tab\\:txtBalanceMoney');
	setFormatPrice('cash-tab\\:txtBalanceCheque');
	setFormatPrice('cash-tab\\:txtBalanceAdvance');
	setFormatPrice('cash-tab\\:txtBalanceProofExpenses');
	setFormatPrice('cash-tab\\:txtTotalOutflow');
	setFormatPrice('cash-tab\\:txtTotalInflow');
	setFormatPrice('txtLastBalance');
	setFormatPrice('txtCurrentBalance');

	var lastValue = $('#txtLastBalance').val();
	var totalOverflow = $('#cash-tab\\:txtTotalOutflow').val();

	totalOverflow = Number(5000);
	
	//lastValue.indeexof

	try {
		lastValue = Number(lastValue.replace(",", ""));
	} catch(err) {
		//no-op
	}

	try {
		totalOverflow = Number(totalOverflow.replace(",", ""));
	} catch(err) {
		//no-op
	}

	var result = lastValue - totalOverflow;

	alert(result);

	var ZERO = 0;
	if(ZERO > result) {
		alert("bl�");
	}

}

/*################# FECHAMENTO CAIXA ##################*/

function setFormatPriceCustom(id) {
	$('#' + id).priceFormat({
	     prefix: '',
	     centsSeparator: ',',
	     thousandsSeparator: '.'
	});  
}

function setFormatPriceForAll() {

	setFormatPriceCustom('cash-tab\\:txtValue');
	setFormatPriceCustom('cash-tab\\:txtCashOutflow');
	setFormatPriceCustom('cash-tab\\:txtBalanceMoney');
	setFormatPriceCustom('cash-tab\\:txtBalanceCheque');
	setFormatPriceCustom('cash-tab\\:txtBalanceAdvance');
	setFormatPriceCustom('cash-tab\\:txtBalanceProofExpenses');
	setFormatPriceCustom('cash-tab\\:txtTotalOutflow');
	setFormatPriceCustom('cash-tab\\:txtTotalInflow');
	setFormatPriceCustom('txtLastBalance');
	setFormatPriceCustom('txtCurrentBalance');
	
	changeValuesColor();
	
}

function changeValuesColor() {
	
	alert("dfdsfsd");
	
	var lastValue = $('#hdnLastBalance').val();
	var currentValue = $('#txtCurrentBalance').val();
	var totalOverflow = $('#cash-tab\\:txtTotalOutflow').val();
	var totalInflow = $('#cash-tab\\:txtTotalInflow').val();
	
	if(lastValue.indexOf(",") != -1) {
		lastValue = lastValue.replace(".", "").replace(",", ".");
	} 	
	lastValue = Number(lastValue);	
				
	if(currentValue.indexOf(",") != -1) {
		currentValue = currentValue.replace(".", "").replace(",", ".");
	} 
	currentValue = Number(currentValue);
	
	if(totalOverflow.indexOf(",") != -1) {
		totalOverflow = totalOverflow.replace(".", "").replace(",", ".");
	} 
	totalOverflow = Number(totalOverflow);
	
	if(totalInflow.indexOf(",") != -1) {
		totalInflow = totalInflow.replace(".", "").replace(",", ".");
	}
	totalInflow = Number(totalInflow); 
				
	var ZERO = 0;
	if(ZERO > (totalInflow + (lastValue - totalOverflow))) {			
		$('#txtCurrentBalance').css('color', '#FF0000');	
	} else {
		$('#txtCurrentBalance').css('color', '#004276');
		isRed = false;
	}
				
	if(0 > lastValue) {
		if($('#hdnFirstTime').val() == "T") {
			$('#txtCurrentBalance').css('color', '#FF0000');
			$('#hdnFirstTime').val("F");
		}
		$('#txtLastBalance').css('color', '#FF0000');
	} else {
		if($('#hdnFirstTime').val() == "T") {
			$('#txtCurrentBalance').css('color', '#004276');
			$('#hdnFirstTime').val("F");
		}
		$('#txtLastBalance').css('color', '#004276');
		alert('xvx');
		$('#txtLastBalance').val("-" + $('#txtLastBalance').val());
	}
}

