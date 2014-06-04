Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"H+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};


var dateReg = new RegExp(
		"^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
jQuery.validator.addMethod("dateValid", function(value, element) {
	return value == "" || dateReg.test(value);
}, "Please enter a valid date");

jQuery.extend( {
	alert : function(msg) {
		var $alert = $('#ui-alert');
		if ($alert.size() == 0) {
			$('<p id="ui-alert"><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span><span>' + msg + '</span></p>').appendTo(
					'body').dialog( {
				resizable : false,
				modal : true,
				buttons : {
					'Confirm' : function() {
						$(this).dialog("close");
					}
				}
			});
		} else {
			$alert.find(':nth-child(2)').html(msg);
			$alert.dialog('open');
		}
	}
});

jQuery.extend( {
	confirm : function(msg, confirmFunction) {
		var $confirm = $('#ui-confirm');
		if ($confirm.size() == 0) {
			$('<p id="ui-confirm"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><span>' + msg + '</span></p>').appendTo(
					'body').dialog( {
				resizable : false,
				modal : true,
				buttons : {
					'Yes' : function() {
						$(this).dialog("close");
						if (confirmFunction)
							confirmFunction();
					},
					'No' : function() {
						$(this).dialog("close");
					}
				}
			});
		} else {
			$confirm.find(':nth-child(2)').html(msg);
			$confirm.dialog('open');
		}
	}
});

$('#menu li').hover(function() {
	$(this).addClass('menu-hover');
}, function() {
	$(this).removeClass('menu-hover');
});

var delayTime;
$('#menu > ul > li').hover(function() {
	var $this = $(this);
	delayTime = setTimeout(function() {
		$this.find('ul').slideDown(200);
	}, 100);
}, function() {
	clearTimeout(delayTime);
	$(this).find('ul').slideUp(100);
});

$('tbody tr:not(.filter):odd').find('td:first-child').andSelf().addClass('odd');
$('tbody tr:not(.filter):even').find('td:first-child').andSelf().addClass('even');

var btnClasses = [ 'search', 'close', 'refresh', 'help', 'notice', 'cancel', 'plus', 'check', 'disk', 'pencil', 'document', 'star', 'wrench', 'print' ];

jQuery.fn.extend( {
	iconbutton : function() {
		return this.each(function() {
			var $this = $(this);
			for ( var i in btnClasses) {
				if ($this.hasClass('button-' + btnClasses[i])) {
					$this.button( {
						icons : {
							primary : 'ui-icon-' + btnClasses[i]
						}
					});
					return;
				}
			}
			$this.button();
		});
	}
});

$('input.button, a.button, button').iconbutton();

$('select:not(.filter)').combobox();

$('#search h2 a').click(function() {
	$(this).parent().next().slideToggle(200);
	return false;
});
$('#search .button-cancel').click(function() {
	$(this.form).slideToggle(200);
});

//TODO
//$('form.recordForm .required').parent().prev().append('<span class="requireTip">*</span>');
//$("form.recordForm").validate( {
//	errorPlacement : function(error, element) {
//		element.parent().append(error);
//	}
//});



$("#searchForm").validate( {
	errorElement : "span"
});

$('input[type="text"], input[type="password"], textarea').focusin(function() {
	$(this).addClass('focus');
}).focusout(function() {
	$(this).removeClass('focus');
});
$('form:not(.filter) :input:not(.filter):not:visible:first').focus();

$.datepicker._defaults.dateFormat = 'yy-mm-dd';
var dates = $("#dateFrom, #dateTo").datepicker(
		{
			changeMonth : true,
			changeYear : true,
			onSelect : function(selectedDate) {
				var option = this.id == "dateFrom" ? "minDate" : "maxDate", instance = $(this).data("datepicker"), date = $.datepicker.parseDate(
						instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
				dates.not(this).datepicker("option", option, date);
			}
		});
$('a.confirm-delete').click(function() {
	$this = $(this);
	$.confirm($this.attr('confirm') || 'Do you confirm to delete this record?', function() {
		location.href = $this.attr('href');
	});
	return false;
});