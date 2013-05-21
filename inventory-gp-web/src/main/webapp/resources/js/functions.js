function setFormatPriceCustom(id) {
	$('#' + id).priceFormat({
	     prefix: '',
	     centsSeparator: ',',
	     thousandsSeparator: '.'
	});  
}