$('#currency').change(function() {
	$balance = $('#currentBalance');
	$.post('bankreport/changecurrency', {
		balance : $balance.attr('val'),
		currencyId : $(this).val()
	}, function(data) {
		$balance.html(data);
	});
});