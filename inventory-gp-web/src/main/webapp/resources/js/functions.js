function setFormatPriceCustom(id) {
	$('#' + id).priceFormat({
	     prefix: '',
	     centsSeparator: ',',
	     thousandsSeparator: '.'
	});  
}

function saveCompletedHandle(xhr, status, args) { 
	if(args.saved == undefined) {
		alert('� preciso retornar o valor do atributo saved');
	} else if(args.saved) {
    	cfmNewRegister.show();
    }
 }