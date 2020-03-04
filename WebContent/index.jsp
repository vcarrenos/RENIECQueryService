<!--%
	response.sendRedirect("/pages/form.jsp");
%-->
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
	<head>
		<link rel="stylesheet" href="resources/js/jquery-ui-1.11.0.custom/jquery-ui.css" />
		<link rel="stylesheet" href="resources/js/primeui-1.1/themes/bootstrap/theme.css" />
		<link rel="stylesheet" href="resources/js/primeui-1.1/development/primeui-1.1.css" />
		<link rel="stylesheet" href="resources/css/default.css" />
		<style>
			#user { text-transform: none;
					font-size: 15px !important; 
					font-weight: bold !important;
					width:180px;
			}
			
			#password { font-size: 15px !important; 
						font-weight: bold !important;
						width:180px;
			}
			
			body { background-color: #C1C1C1; }
			
			.pui-dialog .pui-dialog-titlebar {
			    background-color: #E5D1BE;
			}
		</style>
 		<script src="resources/js/jquery-ui-1.11.0.custom/external/jquery/jquery.js"></script>
 		<script src="resources/js/jquery-ui-1.11.0.custom/jquery-ui.js"></script>
		<script src="resources/js/primeui-1.1/development/primeui-1.1.js"></script>
		<script src="resources/js/reniec-query-service.js"></script>
		<script>
/*  			var onloadCallback = function() {
				
				grecaptcha.render('html_element', {
					'sitekey' : '6LccMBUTAAAAADOqCcEzJAMpl0SVIytBKwUGeZTG',
					'callback' : function(response) {
		
						$("#captchaRSP").val("1");
						console.log($("#captchaRSP").val() + "///" + response);
					}
				});
			}; */ 

			$(document).ready(function() {
				
				$('#msgNoLogon').puigrowl();
				$('#user').puiinputtext();
				$('#password').puiinputtext();

				$('#bt_login').puibutton({
					icon : 'ui-icon-check'
				}).click(function(event) {
					
						/* if ($("#captchaRSP").val() == 0) {
			
							$('#msgNoLogon').puigrowl('show', [{
								severity : 'error',
								summary : '<s:text name="login.title"/>',
								detail  : '<s:text name="login.no.select.captcha"/>',
							}]);
						}
						else */ {
							
								var usr = $('#user').val();
								var pwd = $('#password').val();
				
								$.getJSON('verifyLogonAuthorization', 
									{user : usr, password : pwd }, 
									function(jsonResponse) {
										if (jsonResponse.accessGranted == false) {
											$('#msgNoLogon').puigrowl('show', [ {
												severity : 'error',
												summary : '<s:text name="login.title"/>',
												detail : jsonResponse.jsonErrorMessage
					
											} ]);
										} else {
											self.location.href = 'main-menu';
										}
								 });
						}
	
	 					event.preventDefault(); 
				});
				

  				$('#dlgLogon').puidialog({
					showEffect : 'fade',
					hideEffect : 'fade',
					minimizable : false,
					maximizable : false,
					resizable : false,
					modal : true,
					visible : true,
					closeOnEscape : false,
					width : '625px',
					height : '389px',
				}); 

		
			});
		
			function onBlur(e) {
				e.stopImmediatePropagation();
				e.preventDefault();
			};
		
		</script>
 		<%-- <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer ></script> --%> 
	</head>
	<body>
<!-- 			<input type=hidden id="captchaRSP" name="captchaRSP" value="1" /> -->
		<div id="dlgLogon" title="Acceso al Sistema" > 
			<table id='tbllogon'>
				<tr style="height: 120px;" />
				<tr>
					<td class="form-login-left"><s:text name="login.label.username" /></td>
					<td><input type="text" id="user" required='required' onblur="onBlur(event);" autofocus="autofocus"/></td>
				</tr>
				<tr style="height: 15px;">
					<td class="form-login-left"><s:text name="login.label.password" /></td>
					<td><input type="password" id="password" required='required' /></td>
				</tr>
				<tr style="height: 10px;" />
	<%--  					<tr class="form-login-left">
							<td colspan="2" style="padding-left: 200px;"><span id="html_element"></span></td>
						</tr> --%>
				<tr>
					<td colspan="2" style="padding-left: 250px;">
						<button id="bt_login" type="button" title="<s:text name='login.button.tip'/>">
							<s:text name="login.button.text" />
						</button>
					</td>
				</tr>
				<tr style="padding-top: 80px;" />
			</table>
		</div>
		<div id="msgNoLogon"></div>
	</body>
</html>

