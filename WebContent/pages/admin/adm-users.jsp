<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>

	<jsp:attribute name="head">
	
		<style>
            .pui-datatable thead th:nth-child(1)  { width: 40px;        }
            .pui-datatable thead th:nth-child(2)  { width: 150px;       }
            .pui-datatable thead th:nth-child(3)  { width: 150px;       }
            .pui-datatable thead th:nth-child(4)  { width: 120px;       }
            .pui-datatable thead th:nth-child(5)  { width: 50px;        }
            .pui-datatable thead th:nth-child(6)  { width: 30px;        }
            .pui-datatable thead th:nth-child(7)  { width: 50px;        }
            .pui-datatable thead th:nth-child(8)  { width: 50px;        }
            .pui-datatable thead th:nth-child(9)  { width: 30px;        }
                        
            .pui-datatable tbody td:nth-child(1)  { text-align: left;   } 
            .pui-datatable tbody td:nth-child(2)  { text-align: left;   } 
            .pui-datatable tbody td:nth-child(3)  { text-align: left;   }
            .pui-datatable tbody td:nth-child(4)  { text-align: left;   }
            .pui-datatable tbody td:nth-child(5)  { text-align: center; }
            .pui-datatable tbody td:nth-child(6)  { text-align: right;  }
            .pui-datatable tbody td:nth-child(7)  { text-align: center; }
            .pui-datatable tbody td:nth-child(8)  { text-align: center; }
            .pui-datatable tbody td:nth-child(9)  { text-align: center; }
            
            
		</style>
	
		<script src="resources/js/reniec-query-service.js"></script>
		<script>
		
			$(document).ready(function() {
				
				$('#detail').puitabview();
				$('#list').puitabview();

				$('#code').puiinputtext(); 
                $('#first').puiinputtext(); 
                $('#last').puiinputtext();
                $('#surname').puiinputtext();
                $('#birth').puiinputtext();
                $('#birth').datePicker();
                $('#quota').puiinputtext();
                $('#expiration').puiinputtext();
                $('#expiration').datePicker();
                $('#active').puicheckbox();

                enable_controls(false, false);
                
                $('#messages').puigrowl();   
                
                /* BOTON NUEVO */
                $('#bt_new').puibutton({icon: 'ui-icon-circle-plus'})
                      .click(function() {
                    	  
		                    $('#code').val('');
		                    $('#first').val('');
		                    $('#last').val('');
		                    $('#surname').val('');
		                    $('#birth').val('');
		                    $('#quota').val('');
		                    $('#expiration').val('');
		                    $('#active').puicheckbox('uncheck');

//		                    $('#birth').datePicker();
//		                    $('#expiration').datePicker();
		                    
		                    enable_controls(false, true);

		                    $('#bt_save').show();
		                    $('#bt_cancel').show();
		
		                    $('#code').focus();
                });
                

                /* BOTON GUARDAR */
                $('#user_form').submit(function(event) {
                    var result = $("#user_form")[0].checkValidity();
                    
                    if(result) {
	                    var code      = $('#code').val();
	                    var nameFirst = encodeURI($('#first').val());
	                    var nameLast  = encodeURI($('#last').val());
	                    var names     = encodeURI($('#surname').val());
	                    var birthDate = $('#birth').val();
	                    var quota = $('#quota').val();
	                    var expirationDate = $('#expiration').val();
	                    var enabled   = $('#active').puicheckbox('isChecked');
                        
                        $.getJSON('saveUser', 
                             {userCode: code, userNameFirst: nameFirst, userNameLast: nameLast, userNames: names, 
                              userQuota: quota, userExpirationDate:expirationDate,  userBirthDate: birthDate, 
                              userEnabled: enabled}, 
                             function(jsonResponse) {
                                  
                                 if (jsonResponse != null) {
                                     
                                     loadUsers();

                                     $('#messages').puigrowl('show', [ {
                                             severity : 'warn',
                                             summary : "<s:text name='dialog.save.title'/>",
                                             detail : "<s:text name='dialog.save.ok'/>"
                                     } ]);
                                     
                                 }
                        }); 
    
                        $('#bt_new').show();
                        $('#bt_save').hide();
                        $('#bt_cancel').hide();
                        
                        enable_controls(false, false);
                    }
                    event.preventDefault();
                });                
                
				$('#bt_save').puibutton({icon : 'ui-icon-disk'});
				$('#bt_save').hide();

				/* BOTON CANCELAR */
				$('#bt_cancel').puibutton({icon : 'ui-icon-close'})
				    .click(function() {

						$('#bt_new').show();
						$('#bt_save').hide();
						$('#bt_cancel').hide();
						
                        var selections = $('#tbllocal').puidatatable('getSelection');

                        if (selections.length === 1) {
	                        $('#code').val(selections[0].codigo);
	                        $('#first').val(selections[0].paterno);
	                        $('#last').val(selections[0].materno);
	                        $('#surname').val(selections[0].nombres);
	                        $('#birth').val(selections[0].nacimiento);
		                    $('#quota').val(selections[0].cuota);
		                    $('#expiration').val(selections[0].expiracion);
	                        $('#active').puicheckbox((selections[0].activo? 'check': 'uncheck'));
                        }

                        enable_controls(false, false);
				});
				$('#bt_cancel').hide();

				loadUsers();
			});
			
			function enable_controls(editing, enabled) {
                $('#code').prop('readonly', !enabled || editing);
                $('#first').prop('readonly', !enabled);
                $('#last').prop('readonly', !enabled);      
                $('#surname').prop('readonly', !enabled);      
                $('#birth').prop('readonly', !enabled);      
                $('#quota').prop('readonly', !enabled);
                $('#expiration').prop('readonly', !enabled);
                $('#active').puicheckbox((enabled? 'enable' : 'disable'));       

//                $('#birth').datepicker((enabled? 'enabled' : 'disable'));
//                $('#expiration').datepicker((enabled? 'enabled' : 'disable'));

			}
			
            /* BOTON EDITAR */
            function edit_row() {
                $('#bt_new').hide();
                $('#bt_save').show();
                $('#bt_cancel').show();

                enable_controls(true, true);
                
                $('#name_first').focus();
             };

            function loadUsers() {
                
                jBlock();
                
                $.getJSON('loadAllUsers', 
                        function(jsonResponse) {
                            
                          var optionsTable = {  
                                  caption: $.parseJSON(jsonResponse.jsonTableData).length + ' ' + "<s:text name='users.table.title'/>",  
                                  paginator: {  
                                      rows: 10, 
                                      totalRecords: $.parseJSON(jsonResponse.jsonTableData).length
                                  },  
                                  columns: [  
                                      {field:'codigo'    , headerText: "<s:text name='users.column.code'/>"      ,   sortable:true},  
                                      {field:'paterno'   , headerText: "<s:text name='users.column.first'/>"     ,   sortable:true},  
                                      {field:'materno'   , headerText: "<s:text name='users.column.last'/>"      ,   sortable:true},  
                                      {field:'nombres'   , headerText: "<s:text name='users.column.names'/>"     ,   sortable:true},  
                                      {field:'nacimiento', headerText: "<s:text name='users.column.birthdate'/>" ,   sortable:true}, 
                                      {field:'cuota'     , headerText: "<s:text name='users.column.quota'/>"     ,   sortable:true}, 
                                      {field:'expiracion', headerText: "<s:text name='users.column.expira'/>"    ,   sortable:true, 
                                      content:
                                          function(option) {  
	                                       		 return (option.expiracion === '99/99/9999'? "NUNCA" : option.expiracion);  
                                     	   }
                                      }, 
                                      {field:'activo'    , headerText: "<s:text name='users.column.enabled'/>"   , sortable:true, 
                                       content:
                                           function(option) {  

                                           		return "<input type='checkbox' id='active'" + option.codigo + " checked=option.activo disabled='disabled' class='pui-chkbox-icon pui-c ui-icon ui-icon-check' />" 
                                      	   }
                                      }, 
                                      {field:'edit',    sortable: false,
                                       content: 
                                           function(option) {  
                                                return '<img src="resources/img/edit-icon.png" title="Editar" onclick="edit_row()" >';
                                           }
                                      }
                                  ],  
                                  datasource: [],
                                  selectionMode: 'single',  
                                  rowSelect: function(event, data) {  
                                      
                                          $('#code').val(data.codigo);
                                          $('#first').val(data.paterno);
                                          $('#last').val(data.materno);
                                          $('#surname').val(data.nombres);
                                          $('#birth').val(data.nacimiento);
                                          $('#quota').val(data.cuota);
                                          $('#expiration').val((data.expiracion === '99/99/9999'? 'NUNCA' : data.expiracion) );
                                          $('#active').puicheckbox((data.activo? 'check' : 'uncheck'));       

                                          if (data.nacimiento === '99/99/9999') 
                                              $('#birth').datepicker('disable');
                                          else 
                                              $('#birth').datepicker('enable');
                                          
                                          if (data.expiracion === '99/99/9999') 
                                              $('#expiration').datepicker('disable');
                                          else
                                        	  $('#expiration').datepicker('enable');
                                          
                                  }, 
                                  
                              };
                          
                          // Create the table
                          $('#tbllocal').puidatatable(optionsTable);
                          $('#tbllocal').puidatatable('option', 'datasource', $.parseJSON(jsonResponse.jsonTableData));
                          $('#tbllocal').puidatatable('sort', 'paterno', 1);

          				  var rows = $('tr', $('#tbllocal'));
                          $('#tbllocal').puidatatable('selectRow', rows.eq(0), false);
                });
                jUnblock();
            }                        
            
        </script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.users.title'/></span>
        <form id="user_form">  
	        <div id="detail" style="margin-top: 15px;">
			    <ul>
					<li><a href="#tab1"><s:text name="users.details"/></a></li>
                </ul>
			    <div>
			        <div id="tab1">
			            <div>
			                <table>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.code"></s:text></td>
			                        <td><input type="text" id="code" required='required' tabindex="1" style="width:150px;"
			                        		   onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.first"></s:text></td>
			                        <td><input type="text" id="first" required='required' tabindex="2" style="width:400px;" /></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.last"></s:text></td>
			                        <td><input type="text" id="last" required='required' tabindex="3" style="width:400px;"/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.names"></s:text></td>
			                        <td><input type="text" id="surname" required='required' tabindex="4" style="width:400px;"/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.birth"></s:text></td>
			                        <td><input type="text" id="birth" required='required' tabindex="5" style="width:150px;"/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.quota"></s:text></td>
			                        <td><input type="text" id="quota" required='required' tabindex="6" style="width:150px;"/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.expiration"></s:text></td>
			                        <td><input type="text" id="expiration" required='required' tabindex="7" style="width:150px;"/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="users.column.enabled"></s:text></td>
			                        <td><input type="checkbox" name="active" id="active"  tabindex="8"/></td>
			                    </tr>
			                </table>
	            		</div>  
				        <div id='action_buttons' style="margin-top: 20px;">
				            <button id="bt_new" type="button" title="<s:text name='button.new.tip'/>"><s:text name="button.new"/></button>
				            <button id="bt_save" type="submit" title="<s:text name='button.save.tip'/>"><s:text name="button.save"/></button>
				            <button id="bt_cancel" type="button" title="<s:text name='button.cancel.tip'/>"><s:text name="button.cancel"/></button>
				        </div>
	        		</div>
	        	</div>
	        </div>      
        </form>
        <div id="list" style="margin-top: 15px;">
		    <ul>
                <li><a href="#tab1"><s:text name="users.list"/></a></li>
            </ul>
		    <div>
		        <div id="tab2">
			        <div id="tbllocal"></div>
			    </div>
			</div>
		</div>
	</jsp:attribute>
</mdl:model>


