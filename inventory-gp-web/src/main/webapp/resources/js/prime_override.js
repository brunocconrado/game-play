function formatCurrency(id) {
	$(id).priceFormat({
		prefix: '',
		centsSeparator: ',',
	    thousandsSeparator: '.'
	});
	
	loading.hide();
}

function formatNumber(id) {
	$(id).priceFormat({
		prefix: '',
		centsSeparator: '',
	    thousandsSeparator: ''
	});
	
	loading.hide();
}

var timeOuts= new Array();  
var init = 0;

function isTyping() {
	clearTimeouts();
	timeOuts[init] = setTimeout('recalcTotal()', 500);
	init++;
}

function clearTimeouts() {  
	for(key in timeOuts ){  
		clearTimeout(timeOuts[key]);  
	}  
}

function putBehind() {
    var z = $("#loading").css('z-index') - 1;
    $("#mask").css({'z-index':z});
}

jQuery.fn.loading = function() {
    var o = $(this[0]);
    var h = $(document).height();
    var w = $(window).width();
    
    o.css({'width':w,'height':h});
    o.show();
    
    setTimeout('putBehind()', 10);
};

jQuery.fn.changePosition = function() {
    var o = $(this[0]);
    var h = $(window).height();
    var w = $(window).width();
    
    var top = (h - o.height()) / 2;
    var left = (w - o.width()) / 2;
    
    o.css({'top':top, 'left':left});
};

PrimeFaces.locales['pt_BR'] = {
                closeText: 'Fechar',
                prevText: 'Anterior',
                nextText: 'Pr&oacute;ximo',
                currentText: 'Come&ccedil;o',
                monthNames: ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
                dayNames: ['Domingo','Segunda','Ter&ccedil;a','Quarta','Quinta','Sexta','S&aacute;bado'],
                dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S&aacute;b'],
                dayNamesMin: ['D','S','T','Q','Q','S','S'],
                weekHeader: 'Semana',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: '',
                timeOnlyTitle: 'S&oacute; Horas',
                timeText: 'Tempo',
                hourText: 'Hora',
                minuteText: 'Minuto',
                secondText: 'Segundo',
                currentText: 'Data Atual',
                ampm: false,
                month: 'M&egrave;s',
                week: 'Semana',
                day: 'Dia',
                allDayText : 'Todo Dia'
            };

//############################## MODAL #######################################

function showModalDialog(idModal) {
	 showModalDialog(idModal, 1110);
}

function showModalDialog(idModal, zindex) {
	
	var width = $(window).width();
	var height = $(window).height(); 
	
	$('.ui-dialog-docking-zone').after("<div id='"+ idModal + 
			"' class='ui-widget-overlay' style='width: " + width +
			"px; height: " + height + "px; z-index: " + zindex + "; display: block;'></div>");
}

function hideModalDialog(idModal) {
	
	idModal = "#" + idModal;
	
	$(idModal).remove();
}
