/**
 * 
 */
$(function() {
	$('#main_container').on('click', '.input-wrepper > input', function() {
		document.querySelectorAll('.field-err').forEach(el => el.classList.remove('field-err'));
	});
	
	
	$('#main_container').on('change','input[type=radio]', function() {
		if($(this).is('#T_gender')) {
			$(this).attr('disabled',true);
			var text = "U Dogvilu su svi jednaki i „Simulacrum Inc“ podjednako uvažava prava svih ljudi, bez obzira na pol i seksualno opredeljenje, ali moramo da ti se obraćamo u jednom od preostala dva roda.";
			$(this).parent().attr('data-info', text).addClass('field-info');
		} else {
			$('#T_gender').parent().removeClass('field-info');
		}
	});
	
	$('#main_container').on('click', '#terms_bttn', function(){
		var visible = $('#terms_and_conditions').is(':visible');		
		$('#terms_and_conditions').toggle( !visible );
		if(!visible) {
			/* Ako je skrolovao do kraja dokumenta*/
			$('#terms_and_conditions').scroll(function() {
				if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
					if($('#terms_check').val() < 3)
						$('#terms_check').val('2');
				}	
			});
			/* Ako je proveo na dokumentu 1 minut... */
			setTimeout(function () {
				if($('#terms_check').val() > 1)
					$('#terms_check').val('3'); 
			}, 60000);
			
		}
	});	

});

function loadFragment(uri, target) {
	$.ajax({
	    url: uri,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( fragment ) {
		$(target).html(fragment);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	});
}

function validateAndSubmit(form_name) {
	$form = $('form[name='+form_name+']');
	$('.input-holder > .field-err').each(function() {
		$( this ).removeClass('field-err');
	});
	var valid = $form.ajaxFormValidation();	
	console.log('Forma: '+form_name+' je validna: '+valid);
	if(valid) {
		$form.processSignup();
	}
}

$.fn.ajaxFormValidation = function() {
	var result = false;
	var uri = $(this).attr('data-valid-action');
	var formData = $(this).serialize();	
	$.ajax({
		url: uri,
	 	data: formData,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        type: 'POST',
        async: false,
	    dataType: 'json'
	})
	.done(function() {		
		result = true;
	})
	.fail(function( xhr, status, errorThrown ) {
		var err = xhr.responseJSON;
		$('.input-wrepper:first').attr('data-error', err.defaultMessage).addClass('field-err');
		result = false;
	});
	return result;
}

$.fn.processSignup = function() {
	var action = $(this).attr('action');
	$.ajax({
	    url: action,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( fragment ) {
		$("#main_container").html(fragment);
		
		  var progress = $(".progress");
		  var bar = progress.parent();		  
		  var perc = (progress.width() * 100 / bar.width());
		  if(perc<101) $(".progress").css({ width : (perc+20) +"%" });			
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	});
}

$.fn.submitForm = function() {
	var action = $(this).attr('action');
	var data = $(this).serialize();	
	
	$.ajax({
		url: action,
	 	data: data,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        type: 'POST',
	    dataType: 'html'
	})
	.done(function(html) {
		alert(html);
		$("#main_container").html(html)
	})
	.fail(function( xhr, status, errorThrown ) {
		var err = xhr.responseJSON;
		console.log(xhr);
	});
}