$(function() {
	$('#date').datepicker( {
		maxDate : "+0d"
	});
	var $confirm = $('<div>Which version of excel do you want?</div>').appendTo('body').dialog( {
		resizable : false,
		autoOpen : false,
		modal : true,
		buttons : {
			'97 - 2003' : function() {
				exportXls(true);
			},
			'2007 / 2010' : function() {
				exportXls(false);
			},
			'Cancel' : function() {
				$(this).dialog("close");
			}
		}
	});
	$("#export").click(function() {
		$confirm.dialog('open');
	});
	function exportXls(flag) {
		$('#reportForm').append('<input type="hidden" name="flag" value="true" id="flag">');
		$("#flag").attr("value", flag);
		var $reportForm = $("#reportForm");
		var url = $reportForm.attr('action');
		$reportForm.attr('action', $reportForm.attr('exportAction')).submit().attr('action', url);
		$confirm.dialog("close");
		$('#flag').remove();
	}

	$('#reportForm').find('#date,#dateFrom,#dateTo').addClass('dateValid').end().validate( {
		errorElement : "span"
	});

	$('#tip').dialog( {
		title : 'Tips',
		height : 600,
		width : 250,
		minWidth : 250,
		maxWidth : 250,
		position : 'right',
		autoOpen : false
	});
	$('#tip-button').click(function() {
		var $tip = $('#tip');
		$tip.dialog("isOpen") ? $tip.dialog("close") : $tip.dialog("open");
	});
	$('#print').click(function() {
		print();
	});
	$('td.editable').click(function() {
		if ($('input', this).size() > 0)
			return false;
		var span = $('span', this);
		var str = span.html();
		span.html('');
		$('<input class="editable" type="text" size="15" />').val(parse(str)).appendTo(span).select();
	}).find('span').focusout(function() {
		if ($('input', this).size() == 0)
			return false;
		formatSpan($(this), $('input', this).val());
	});
});

function parse(str) {
	try {
		var result = (str.indexOf('(') == 0 ? -1 : 1) * parseFloat(str.replace(/[,\(\)]/g, ''));
		if (isNaN(result)) {
			return 0;
		}
		return result;
	} catch (e) {
		return 0;
	}
}

function formatSpan(span, val) {
	if (val < 0) {
		span.attr('style', 'color: red')
	} else {
		span.attr('style', '');
	}
	span.html(format(val));
}

function format(decimal) {
	try {
		if (/[^0-9\.-]/.test(decimal) || decimal == 0)
			return '-';
		var str = ('' + Math.abs(decimal)).replace(/^(\d*)$/, "$1.");
		str = (str + "00").replace(/(\d*\.\d\d)\d*/, "$1");
		str = str.replace(".", ",");
		var re = /(\d)(\d{3},)/;
		while (re.test(str))
			str = str.replace(re, "$1,$2");
		str = str.replace(/,(\d\d)$/, ".$1");
		str = str.replace(/^\./, "0.");
		if (decimal < 0)
			str = '(' + str + ')';
		return str;
	} catch (e) {
		return '-';
	}
}