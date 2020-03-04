<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>

	<jsp:attribute name="head">
	
		<style>
            .pui-datatable thead th:nth-child(1) { width: 80px;      }
            .pui-datatable thead th:nth-child(2) { width: 200px;     }
            .pui-datatable thead th:nth-child(3) { width: 200px;     }
            .pui-datatable thead th:nth-child(4) { width: 200px;     }
            .pui-datatable thead th:nth-child(5) { width: 50px;      }
            .pui-datatable thead th:nth-child(6) { width: 50px;      }
            .pui-datatable thead th:nth-child(7) { width: 50px;      }
                        
            .pui-datatable tbody td:nth-child(1) { text-align: center; } 
            .pui-datatable tbody td:nth-child(2) { text-align: left;   } 
            .pui-datatable tbody td:nth-child(3) { text-align: left;   }
            .pui-datatable tbody td:nth-child(4) { text-align: left;   }
            .pui-datatable tbody td:nth-child(5) { text-align: center; }
            .pui-datatable tbody td:nth-child(6) { text-align: center; }
            
			td {
			    width: 160px;
			    vertical-align: middle;
			    text-align: left;
			}
            
		</style>
        <script src="resources/js/reniec-query-service.js"></script>
		<script src="resources/js/jquery-ui-1.11.0.custom/jquery.cookie.js"></script>
		<script>

			$( document ).ready(function() {

                $('#uploadView').puitabview();
                $('#messages').puigrowl();
                
                $('#uploadFile').puibutton({icon: 'ui-icon-circle-arrow-n'});
                $('#bt_send').puibutton({icon: 'ui-icon-extlink'}); 
                
                $('#chk_data').puicheckbox();
                $('#chk_photo').puicheckbox();
                $('#chk_sign').puicheckbox();
                
                $('#chk_data').puicheckbox('check');
                
				document.getElementById("upload").onchange = function() {
					
						document.getElementById("uploadFile").value = 
								this.value.replace(/C:\\fakepath\\/i, '');
				};
				
                /* BOTON UPLOAD */
   	            $('#formUpload').submit(function(event) {

   	                var tipoSubconsulta = ''; 
   	                
   	                if ( $('#chk_data').puicheckbox('isChecked') )
   	                	tipoSubconsulta = '1';
   	                	
   	                if ( $('#chk_photo').puicheckbox('isChecked') )
   	                	tipoSubconsulta += '2';

   	                if ( $('#chk_sign').puicheckbox('isChecked') )
   	                	tipoSubconsulta += '3';
   	                
   	             	$('#subqueryType').val(tipoSubconsulta);

   	                blockUIForDownload();
	            }); 

			});

			var fileDownloadCheckTimer;
			function blockUIForDownload() {
				
				jBlock();
				fileDownloadCheckTimer = window.setInterval(function() {
					
 					$.getJSON('verifyDownloadFinish', 
		   	              function(jsonResponse) {
			   	              if (jsonResponse.downloadFinish)
		   	            		finishDownload();
	      	        });   	            	
					
				}, 1000);
			}

			function finishDownload() {
				window.clearInterval(fileDownloadCheckTimer);
				jUnblock();
			}
		</script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.search.by.batch'/></span>
		    <s:form id="formUpload" name="formUpload"
		        action="processFile" theme="simple" method="POST" enctype="multipart/form-data">
				<s:hidden id="subqueryType" name="subqueryType" value="1" />
				<div id="uploadView">
				    <ul>
				        <li><a href="#tab1"><s:text name="page.tab.upload"/></a></li>
				    </ul>
				    <div>
				    	<table>
				    		<tr><td colspan="4" style="height: 7px;"></td></tr>
				    		<tr>
				    			<td colspan="3">
				    				<input type="text" id="uploadFile" disabled="disabled" class="input-file-text" />
				    			</td>
				    			<td style="padding-left:20px;">
									<label  class="custom-file-input" >
										<input type="file" id="upload" name="upload" />
									</label>
				    			</td>
				    		</tr>
				    		<tr><td colspan="4" style="height: 10px;"></td></tr>
				    		<tr>
				    			<td style="text-align: left; font-weight: bold; font-size: 12px; height: 35px;" colspan="4">
				    				<s:text name='querybybatch.subquery'/>
				    			</td>
				    		</tr>
				    		<tr>
		                        <td style="width:70px; padding-left:20px;">
		                        	<input type="checkbox" name="active" id="chk_data" />
		                        	<label for="chk_data" class="ui-widget"><s:text name='querybybatch.subquery.data'/></label>
		                        </td>
		                        <td style="width:90px;">
		                        	<input type="checkbox" name="active" id="chk_photo" />
		                        	<label for="chk_photo" class="ui-widget"><s:text name='querybybatch.subquery.photo'/></label>
		                        </td>
		                        <td style="width:70px;">
		                        	<input type="checkbox" name="active" id="chk_sign" />
		                        	<label for="chk_sign" class="ui-widget"><s:text name='querybybatch.subquery.sign'/></label>
		                        </td>
				    		</tr>
				    		<tr><td colspan="4" style="height: 20px;"></td></tr>
				    		<tr>
				    			<td colspan="4" style="text-align: center;">
	            					<button id="bt_send" type="submit"><s:text name="button.send"/></button>
				    			</td>
				    		</tr>
				    		<tr><td colspan="4" style="height: 20px;"></td></tr>
				    	</table>
				    </div>
				</div>
		    </s:form>
	</jsp:attribute>
</mdl:model>

