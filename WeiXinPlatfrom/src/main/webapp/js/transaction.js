var $select = $('select:not(.filter)');
$select.filter('.bgAqua').next().css('background', 'none').addClass('bgAqua');
$('#transactionDate, #confirmedDate').datepicker();
$('#transactionForm').validate( {
	groups : {
		payment : 'paymentAmount paymentCurrency.id',
		receiving : 'receivingAmount receivingCurrency.id'
	},
	errorElement : "span",
	errorPlacement : function(error, element) {
		element.parent().append(error);
	}
});
$('#transactionForm .required').parent().find('label:first').append('<span class="requireTip">*</span>');
function unitChange(unit, accountName, bank, accountNo, swiftCode) {
	$.getJSON('bank/ajax', {
		unitId : $(':selected', $(unit)).val(),
		r : new Date().getTime()
	}, function(data) {
		$(accountName).html('<option value="-1"></option>');
		$.each(data, function(i, item) {
			$('<option></option>').html(item.accountName + ' - ' + (item.description ? item.description : '')).val(item.id).attr('accountName', item.accountName).attr('bankName', item.name).attr('accountNo', item.accountNo).attr('swiftCode', item.swiftCode).attr('description', item.description).attr('address1', item.address1).attr('address2', item.address2).attr('address3',
					item.address3).appendTo(accountName);
		});
		$(accountName + ' ~ input').val('');
		$(bank).val('');
		$(accountNo).val('');
		$(swiftCode).val('');
	});
}
$('#fromUnit').change(function() {
	unitChange('#fromUnit', '#fromAccountName', '#fromBankName', '#fromAccountNo', '#fromSwiftCode');
	$('#paymentCurrency').val($(':selected', this).attr('currencyId'));
	$('#fromBankChargeCurrency').val($(':selected', this).attr('currencyId'));
	$('#paymentCurrencyText').html($(':selected', this).attr('currencyCode'));
	$('#fromBankChargeCurrencyText').html($(':selected', this).attr('currencyCode'));
});
$('#toUnit').change(function() {
	unitChange('#toUnit', '#toAccountName', '#toBankName', '#toAccountNo', '#toSwiftCode');
	$('#receivingCurrency').val($(':selected', this).attr('currencyId'));
	$('#receivingCurrencyText').html($(':selected', this).attr('currencyCode'));
	$('#toBankChargeCurrency').val($(':selected', this).attr('currencyId'));
	$('#toBankChargeCurrencyText').html($(':selected', this).attr('currencyCode'));
});
$('#paymentAmount,#receivingAmount').keyup(function() {
	$from = $('#paymentAmount');
	$to = $('#receivingAmount');
	if ($from.val() != '' && $to.val() != '') {
		rate = $from.val() / $to.val();
		$('#rate').val(Math.round(rate * Math.pow(10, 8)) / Math.pow(10, 8));
	}
});
$('#fromAccountName').change(function() {
	var $option = $(':selected', this);
	$('#fromBankName').val($option.attr('bankName'));
	$('#fromAccountNo').val($option.attr('accountNo'));
	$('#fromSwiftCode').val($option.attr('swiftCode'));
});
$('#toAccountName').change(function() {
	var $option = $(':selected', this);
	$('#toBankName').val($option.attr('bankName'));
	$('#toAccountNo').val($option.attr('accountNo'));
	$('#toSwiftCode').val($option.attr('swiftCode'));
});

$('#paymentMethod').change(function() {
	var $cheque = $('#chequeNo');
	if ($(this).val() == 'CHQ') {
		$cheque.show();
	} else {
		$cheque.hide();
	}
});

$('#transactionForm').submit(function() {
	var validator = true;
	$toUnit = $('#toUnit');
	if ($('#fromUnit').val() == -1 && $toUnit.val() == -1) {
		$toUnit.parent().append('<span class="error">The From Unit or To Unit is required</span>');
		validator = false;
	}
	var $cheque = $('#chequeNo');
	var $payment = $('#paymentMethod');
	if ($cheque.css('display') != 'none') {
		$('#paymentMethodInput').val($payment.val() + ' ' + $cheque.val());
	} else {
		$('#paymentMethodInput').val($payment.val());
	}
	return validator;
});

$('#email').click(function() {
	$button = $(this);
	$.get('transaction/email_finance/' + $('#transactionId').val(), {
		r : new Date().getTime()
	}, function(data) {
		var $div = $('<div id="emailFinance"></div>').attr('title', $button.html()).html(data).appendTo('body');
		$div.dialog( {
			modal : true,
			close : function() {
				$(this).remove();
			},
			width : 640
		});
		$div.find('button').iconbutton().end().find('button.button-document').click(function() {
			$.post('transaction/send_email/' + $('#transactionId').val(), function(data) {
				if (data == 'success') {
					$('#emailFinance').dialog('close');
					$.alert('Mail send successfully');
				} else {
					$.alert(data);
				}
			});
		}).end().find('button.button-cancel').click(function() {
			$('#emailFinance').dialog('close');
		});
	});
});

$('#voucher,#bov,#sparkasse').click(function() {
	var $button = $(this);
	var id = 'print_' + $button.attr('id');
	$.get('transaction/' + id + '/' + $('#transactionId').val(), {
		r : new Date().getTime()
	}, function(data) {
		var $div = $('<div></div>').attr( {
			'title' : $button.html(),
			'id' : id,
			'class' : 'printframe'
		}).html(data).appendTo('body');
		$div.dialog( {
			modal : true,
			close : function() {
				$(this).remove();
			},
			width : 720
		});
		$div.find('button').iconbutton().end().find('button.button-cancel').click(function() {
			$('#' + id).dialog('close');
		}).end().find('button.button-print').click(function() {
			print();
		}).end().find('input.datepicker').datepicker();
	});
});

// amend bank details
var amendBank = $('#amendBankForm').submit(
		function() {
			if ($('#amend_accountName').val() == '' || $('#amend_bankName').val() == '' || $('#amend_accountNo').val() == '' || $('#amend_balance').val() == ''
					|| $('#amend_currency').val() == '-1' || $('#amend_status').val() == '-1') {
				$.alert('AccountName, Bank Name, Balance, Account No., Currency and Status can not be empty');
				return false;
			}
			$.post(this.action, $(this).serialize(), function(data) {
				if (data == 'success') {
					$('#fromUnit, #toUnit').change();
					amendBank.dialog('close');
				} else {
					$.alert(data);
				}
			});
			return false;
		}).dialog( {
	autoOpen : false,
	modal : true,
	width : 500
});
$('#fromAccountAdd,#toAccountAdd').click(function() {
	var fromOrTo = $(this).attr('id') == 'fromAccountAdd';
	var $unit = fromOrTo ? $('#fromUnit :selected') : $('#toUnit :selected');
	if ($unit.val() == '-1') {
		$.alert('Please select an unit first');
		return;
	}
	$('#amendBankForm').get(0).reset();
	$('#amend_method').val('PUT');
	$('#amend_unitId').val($unit.val());
	$('#amend_unitCode').html($unit.html());
	$('#amend_currency').val($unit.attr('currency'));
	amendBank.dialog("option", 'title', 'Create new Bank').dialog('open');
});
$('#fromAccountEdit,#toAccountEdit').click(function() {
	var fromOrTo = $(this).attr('id') == 'fromAccountEdit';
	var $unit = fromOrTo ? $('#fromUnit :selected') : $('#toUnit :selected');
	var $account = fromOrTo ? $('#fromAccountName :selected') : $('#toAccountName :selected');
	if ($account.val() == '-1') {
		$.alert('Please select a bank first');
		return;
	}
	$('#amendBankForm').get(0).reset();
	$('#amend_method').val('');
	$('#amend_unitId').val($unit.val());
	$('#amend_bankId').val($account.val());
	$('#amend_unitCode').html($unit.html());
	$('#amend_accountName').val($account.attr('accountName'));
	$('#amend_bankName').val($account.attr('bankName'));
	$('#amend_accountNo').val($account.attr('accountNo'));
	$('#amend_swiftCode').val($account.attr('swiftCode'));
	$('#amend_currency').val($account.attr('currency'));
	$('#amend_description').val($account.attr('description'));
	$('#amend_address1').val($account.attr('address1'));
	$('#amend_address2').val($account.attr('address2'));
	$('#amend_address3').val($account.attr('address3'));
	amendBank.dialog("option", 'title', 'Edit Bank details').dialog('open');
});
$('#amend_cancel').click(function() {
	amendBank.dialog('close');
});

// note add & edit
var amendNote = $('#noteForm').submit(function() {
	if ($('#note_message').val() == '') {
		$.alert('Brief Message can not be empty');
		return false;
	}
	$.post(this.action, $(this).serialize(), function(data) {
		if (data == 'success') {
			$.get('note/ajax', {
				transactionId : $('#note_transactionId').val(),
				r : new Date().getTime()
			}, function(data) {
				var $note = $('#noteSpan');
				$note.empty();
				for ( var i in data) {
					var t = data[i];
					var _html = new Date(t.createdTime).format('dd.MM.yyyy HH:mm') + ' [' + (t.operatorCode ? t.operatorCode : 'SYSTEM') + '] - ' + t.message;
					$('<option></option>').attr('value', t.id).attr('message', t.message).html(_html).appendTo($note);
				}
			});
			amendNote.dialog('close');
		} else {
			$.alert(data);
		}
	});
	return false;
}).dialog( {
	autoOpen : false,
	modal : true,
	width : 370
});
$('#addNote').click(function() {
	$('#noteForm').get(0).reset();
	$('#note_method').val('PUT');
	amendNote.dialog("option", 'title', 'Create new Note').dialog('open');
});
$('#editNote').click(function() {
	var $note = $('#noteSpan :selected');
	var noteId = $note.val();
	if (!noteId) {
		$.alert('Please select a note first');
		return;
	}
	$('#noteForm').get(0).reset();
	$('#note_method').val('');
	$('#note_id').val(noteId);
	$('#note_message').val($note.attr('message'));
	amendNote.dialog("option", 'title', 'Edit Note').dialog('open');
});
$('#note_cancel').click(function() {
	amendNote.dialog('close');
});