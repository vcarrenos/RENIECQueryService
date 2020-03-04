$(document).ready(function() {
	
	$("input[type='text'],input[type='password']").on('change invalid', function() {
	    var textbox = $(this).get(0);
	    
	    if (textbox.value == '') {
	        textbox.setCustomValidity('Este campo es obligatorio. Debe ingresarlo.');
	    }
	    else if(textbox.validity.typeMismatch){
	        textbox.setCustomValidity('No es un valor aceptado. Vuelva a ingresarlo.');
	    }
	    else {
	        textbox.setCustomValidity('');
	    }
	});	

	$("input[type='text'], textarea").on('blur', function() {
	    var regexURL   = /^(http|https|ftp):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/i
	    var regexEMail = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/
	    var textbox = $(this).get(0);

    	//Se utiliza la funcion test() nativa de JavaScript
	    if (!(regexEMail.test(textbox.value.trim()) || regexURL.test(textbox.value.trim()))) {
		    textbox.value = textbox.value.toUpperCase();
	    }
	 
	});

});

$.fn.center = function () {

	this.horizontalCenter();
	this.verticalCenter();
	
    return this;
}		


$.fn.verticalCenter = function () {
	
    this.css("position","absolute");
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
                                                $(window).scrollTop()) + "px");

    return this;
}		

$.fn.horizontalCenter = function () {
	
    this.css("position","absolute");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
                                                $(window).scrollLeft()) + "px");
    return this;
}		


$.fn.datePicker = function () {
	this.datepicker({
		showAnim       : $.datepicker.fadeIn,
	    autoSize       : true,
	    showButtonPanel: false,
	    dateFormat     : 'dd/mm/yy',
	    dayNamesMin    : ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
	    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
		monthNames     : ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
	});
};				



function jBlock() {

    $.blockUI({ message: '<h3 style = "font-family: Optima, Segoe, "Segoe UI", Candara, Calibri, Arial, sans-serif;">Espere un momento...</h3>', 
        css    : { 
                    border: 'none', 
                    padding: '5px', 
                    backgroundColor: '#000000', '-webkit-border-radius': '10px', '-moz-border-radius': '10px', 
                    color: '#FFFFFF',
                    opacity: .5                                     
                  },
        overlayCSS: { backgroundColor: '#D6D6D6' },
        theme: false
    });
}

function jUnblock() {
	
    $.unblockUI();
}

function getParameter(key) {  
	
    key = key.replace(/[\[]/, '\\[');  
    key = key.replace(/[\]]/, '\\]');  
    var pattern = "[\\?&]" + key + "=([^&#]*)";  
    var regex = new RegExp(pattern);  
    var url = unescape(window.location.href);  
    var results = regex.exec(url);  
    if (results === null) {  
        return null;  
    } else {  
        return results[1];  
    }  
};  			

function getFamilyTies() {
	
    var map = [	{'value': '1', 'label': 'PADRE'      }, 
    			{'value': '2', 'label': 'MADRE'      },
    			{'value': '3', 'label': 'DECLARANTE' },
    			{'value': '4', 'label': 'DESAMPARADO'}
    	];
    
    var json = $.parseJSON(JSON.stringify(map));
    
	return json;
}

function getEvents() {
	
    var map = [	{'value': 'TODOS'         , 'label': 'Todos         '},
               	{'value': 'LOGIN'         , 'label': 'LOGIN         '},
               	{'value': 'LOGOUT'        , 'label': 'LOGOUT        '},
               	{'value': 'CHANGEPASSWORD', 'label': 'CHANGEPASSWORD'},
               	{'value': 'QUERYBYADULTID', 'label': 'QUERYBYADULTID'},
               	{'value': 'QUERYBYNAMES'  , 'label': 'QUERYBYNAMES  '},
               	{'value': 'QUERYBYCHILDID', 'label': 'QUERYBYCHILDID'},
               	{'value': 'QUERYBYBATCH'  , 'label': 'QUERYBYBATCH  '},
               	{'value': 'QUERYPERSON'   , 'label': 'QUERYPERSON   '},
               	{'value': 'CREATE'        , 'label': 'CREATE        '},
               	{'value': 'DELETE'        , 'label': 'DELETE        '},
               	{'value': 'EDIT'          , 'label': 'EDIT          '},
               	{'value': 'EXPORT'        , 'label': 'EXPORT        '}
    	];
    
    var json = $.parseJSON(JSON.stringify(map));
    
	return json;
}

function getHeigth(heigth) {
	
	if ( heigth != 0 )
		return heigth.toFixed(2) + ' cm.';
	else
		return "NO DEFINIDA";
}

function getFormattedDate(date) {
	
	if (date == null)
		return "NO DEFINIDA";
	
	var d = new Date(date.replace('T00', 'T05'));
	
    return $.datepicker.formatDate('dd/mm/yy', d);
};                       

function getPhotographyPath(dniNumber) {
	
	return "resources/download/" 
	        + dniNumber.substring(0, 3) + "/"
	        + dniNumber.substring(3, 6) + "/"
	        + dniNumber + '/' 
	        + dniNumber + ".foto.jpg";
}

function getSignaturePath(dniNumber) {
	
	return "resources/download/" 
	        + dniNumber.substring(0, 3) + "/"
	        + dniNumber.substring(3, 6) + "/"
	        + dniNumber + '/' 
	        + dniNumber + ".firma.jpg";
}
