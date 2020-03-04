<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>
    <jsp:attribute name="head">
		<style>
			#user {
					text-transform: none;
				}
		</style>

		<script src="resources/js/reniec-query-service.js"></script>
		<script>
			$(document).ready(function()  {  
		        $('#messages').puigrowl();
                $('#user').puiinputtext(); 
                $('#oldpassword').puiinputtext(); 
                $('#newpassword').puiinputtext(); 
                $('#repassword').puiinputtext(); 
                $('#bt_save').puibutton({icon: 'ui-icon-check'}); 
                
                $('#change_password_form').submit(function(event) {
                    var result = $("#change_password_form")[0].checkValidity();
                    
                    if(result) {
                        var usr=$('#user').val();
                        var pwd=$('#oldpassword').val();
                        var newpwd=$('#newpassword').val();
                        var repwd=$('#repassword').val();
                        
                        $.getJSON('changePassword', 
                                { user: usr, password: pwd, newPassword: newpwd, newRePassword: repwd}, 
                                  function(jsonResponse) {
                                      if ( jsonResponse.accessGranted == false ) {
                                         $('#messages').puigrowl('show', [{
                                             severity: 'error', 
                                             summary: '<s:text name="login.change.title"/>', 
                                             detail: jsonResponse.jsonErrorMessage                                             
                                         }]);
                                      } 
                                      else  {
                                          jBlock();

                                          $('#messages').puigrowl('show', [{
                                              severity: 'info', 
                                              summary: '<s:text name="login.change.title"/>', 
                                              detail: '<s:text name="login.change.ok"/>'                                            
                                          }]);
                                          
                                          setTimeout(function(){
                                        	    self.location.href = '/RENIECQueryService'; 
                                        	    },2000);

                                          jUnblock();
                                      }
                          });
                    }
                    event.preventDefault();
                });                 
		    }); 			

			function onBlur(e) {
				e.stopImmediatePropagation();
				e.preventDefault();
			};
		    

		</script>
    </jsp:attribute>

    <jsp:attribute name="body">
        <div id="messages"></div>
        <span id="chart-title"><s:text name='page.change.password'/></span>
	    <form id="change_password_form">
		    <table style="margin-top: 50px; margin-left: 50px; ">
	            <tr>
	                <td rowspan="4"><img src="resources/img/login.png" width="150" height="150"></td>
	                <td class="form-password-left"><s:text name="login.label.username"/></td>
	                <td><input type="text" id="user" required='required' autofocus="autofocus" onblur="onBlur(event);" style="width: 180px;" /> </td>
	            </tr>
	            <tr>
	                <td class="form-password-left"><s:text name="login.label.oldpassword"/></td>
	                <td><input type="password" id="oldpassword" required='required' style="width: 180px;" /></td>
	            </tr>
                <tr>
                    <td class="form-password-left"><s:text name="login.label.newpassword"/></td>
                    <td><input type="password" id="newpassword" required='required' style="width: 180px;" /></td>
                </tr>
                <tr>
                    <td class="form-password-left"><s:text name="login.label.repassword"/></td>
                    <td><input type="password" id="repassword" required='required' style="width: 180px;"/></td>
                </tr>
	            <tr>
	                <td colspan="3" style="text-align: center;">
	                <button id="bt_save" type="submit" title="<s:text name='login.change.tip'/>" 
	                		style="margin-top: 25px;" >
	                    <s:text name="login.change.text" />
	                </button>
	            </td>
	            </tr>
		    </table>
	    </form>  
    </jsp:attribute>
</mdl:model>
