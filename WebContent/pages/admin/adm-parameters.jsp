<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>

	<jsp:attribute name="head">
	
		<style>
            .pui-datatable thead th:nth-child(1) { width: 30px;      }
            .pui-datatable thead th:nth-child(2) { width: 80px;      }
            .pui-datatable thead th:nth-child(3) { width: 200px;     }
            .pui-datatable thead th:nth-child(4) { width: 5px;       }
            .pui-datatable thead th:nth-child(5) { width: 5px;       }
                        
            .pui-datatable tbody td:nth-child(1) { text-align: left; } 
            .pui-datatable tbody td:nth-child(2) { text-align: left; } 
            .pui-datatable tbody td:nth-child(3) { text-align: left; }
		</style>
	
		<script src="resources/js/reniec-query-service.js"></script>
		<script>
		
			$(document).ready(function() {
				
				$('#detail').puitabview();
				$('#list').puitabview();
				
				$('#code').puiinputtext(); 
                $('#description').puiinputtext(); 
                $('#value').puiinputtext();

                enable_controls(false, false);
                
                $('#messages').puigrowl();   
                
                /* BOTON NUEVO */
                $('#bt_new').puibutton({icon: 'ui-icon-circle-plus'})
                      .click(function() {
                    	  
		                    $('#code').val('');
		                    $('#description').val('');;
		                    $('#value').val('');
		                    
		                    enable_controls(false, true);
		
		                    $('#bt_save').show();
		                    $('#bt_cancel').show();
		
		                    $('#code').focus();
                });
                

                /* BOTON GUARDAR */
                $('#parameter_form').submit(function(event) {
                    var result = $("#parameter_form")[0].checkValidity();
                    
                    if(result) {
                        var paramId   = encodeURI($('#code').val());
                        var paramName = encodeURI($('#description').val());
                        var paramItem = encodeURI($('#value').val());

                        if (validate(paramId, paramItem)) {
                        
	                        $.getJSON('saveParameter', 
	                             {parameterCode : paramId, parameterDescription : paramName, parameterValue : paramItem}, 
	                             function(jsonResponse) {
	                                 if (jsonResponse != null) {
	                                     loadParameters();
	
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
	                        $('#description').val(selections[0].descripcion);
	                        $('#value').val(selections[0].valor);
                        }

                        enable_controls(false, false);
				});
				$('#bt_cancel').hide();

				/********* DIALOGO ELIMINAR**********/
				$('#remove_dialog').puidialog({
					resizable : false,
					showEffect : 'fade',
					hideEffect : 'fade',
					minimizable : false,
					maximizable : false,
					modal : true,
					width : 335
				});

				/* BOTON SI */
				$('#remove_dialog #bt_remove_yes').puibutton({icon : 'ui-icon-check'})
					.click(function() {
						var selections = $('#tbllocal').puidatatable('getSelection');
						var paramId = selections[0].codigo;

						$('#remove_dialog').puidialog('hide');

 						$.getJSON('removeParameter',
							      {parameterCode : paramId},
							      function(jsonResponse) {
										if (jsonResponse != null) {
                                            loadParameters();

                                            $('#messages').puigrowl('show', [ {
													severity : 'warn',
													summary : "<s:text name='dialog.button.remove'/>",
													detail : "<s:text name='dialog.remove.ok'/>"
											} ]);
										}

						});
				});

				/* BOTON NO */
				$('#remove_dialog #bt_remove_no').puibutton({icon : 'ui-icon-close'})
				    .click(function() {
				    	   $('#remove_dialog').puidialog('hide');
				});
				
				loadParameters();
			});
			
			function enable_controls(editing, enabled) {
                $('#code').prop('readonly', !enabled || editing);
                $('#description').prop('readonly', !enabled);
                $('#value').prop('readonly', !enabled);      
			}
			
            /* BOTON EDITAR */
            function edit_row() {
                $('#bt_new').hide();
                $('#bt_save').show();
                $('#bt_cancel').show();

                enable_controls(true, true);
                
                $('#description').focus();
             };

            /* BOTON ELIMINAR */
            function remove_row() {
                $('#remove_dialog').puidialog('show');
                $('#remove_dialog #bt_remove_no').focus();
            };
            
            function loadParameters() {
                
                jBlock();
                
                $.getJSON('loadAllParameters', 
                        {},
                        function(jsonResponse) {
                            
                          var optionsTable = {  
                                  caption: $.parseJSON(jsonResponse.jsonTableData).length + ' ' + "<s:text name='parameters.table.title'/>",  
                                  paginator: {  
                                      rows: 10, 
                                      totalRecords: $.parseJSON(jsonResponse.jsonTableData).length
                                  },  
                                  columns: [  
                                      {field:'codigo',      headerText: "<s:text name='parameters.column.code'/>",   sortable:true},  
                                      {field:'descripcion', headerText: "<s:text name='parameters.column.description'/>",  sortable:true},  
                                      {field:'valor',       headerText: "<s:text name='parameters.column.value'/>", sortable:true}, 
                                      {field:'edit',        sortable: false,
                                       content: 
                                           function(option) {  
                                                 return '<img src="resources/img/edit-icon.png" title="Editar" onclick="edit_row()" >';
                                           }
                                      }/*,
                                       {field:'delete',      sortable: false,
                                       content: 
                                           function(option) {  
                                                 return '<img src="resources/img/delete-icon.png" title="Eliminar" onclick="remove_row()" />';  
                                           } 
                                      }*/
                                  ],  
                                  datasource: [],
                                  selectionMode: 'single',  
                                  rowSelect: function(event, data) {  
                                          $('#code').val(data.codigo);
                                          $('#description').val(data.descripcion);
                                          $('#value').val(data.valor);
                                  }, 
                              };
                          
                          // Create the table
                          $('#tbllocal').puidatatable(optionsTable);
                          $('#tbllocal').puidatatable('option', 'datasource', $.parseJSON(jsonResponse.jsonTableData));
                          $('#tbllocal').puidatatable('sort', 'codigo', 1);
                          
                          var rows = $('tr', $('#tbllocal'));
                          $('#tbllocal').puidatatable('selectRow', rows.eq(0), false);
                });
                jUnblock();
            }

            function validate(paramId, paramItem) {

            	var message = '';
            	
                switch(paramId) {

	                case 'APP_COMPANY_KEY'				:
		                if (paramItem.trim().length != 24)
			                message = 'La clave de cifrado debe tener 24 caracteres como m\u00EDnimo.';
		                
		                if (!paramItem.match(/^[a-zA-Z0-9]*$/))
			                message += 'La clave de cifrado no debe contener caracteres especiales.';
		                break;
	                case 'APP_TEST_MQ_HOST' 			:
	                case 'APP_PROD_MQ_HOST' 			:
	                case 'APPL_PROXY_HOST' 				:
		                if (!paramItem.match(/^\d+\.\d+\.\d+\.\d$/))
			                message = 'El valor ingresado parece no ser de una IP.';
		                break;
	                case 'APP_TEST_MQ_PORT_NUMBER' 		:
	                case 'APP_TEST_MQ_EXPIRATION_TIME'	:
	                case 'APP_PROD_MQ_PORT_NUMBER' 		:
	                case 'APP_PROD_MQ_EXPIRATION_TIME'	:
	                case 'APPL_PROXY_PORT'		 		:
	                case 'APP_DELAY_QUERY_BATCH'		:
		                if (!paramItem.match(/^[^0-9]*$/))
			                message = 'Debe ingresar un valor num\u00E9rico.';
		                break;
	                case 'APP_MQ_ENVIRONMENTQUERY_ACTIVE' :
		                if (!paramItem.match(/^[^1-2]*$/))
			                message = 'Debe ingresar un valor num\u00E9rico entre 1 y 2.';
		                break;
	                case 'APPL_PROXY_SET' :
		                if (!paramItem.match(/^[^0-1]*$/))
			                message = 'Debe ingresar un valor num\u00E9rico entre 0 y 1.';
		                break;
                }

                if (message.length > 0) {
                    
                    $('#messages').puigrowl('show', [ {
							severity : 'warn',
							summary : "<s:text name='parameters.invalid.value '/>",
							detail : message
					} ]);

                    $('#value').focus();

					return false;
                }

                return true;

            }                        
            
        </script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.parameters.title'/></span>
        <form id="parameter_form">
	        <div id="detail" style="margin-top: 15px;">
			    <ul>
                    <li><a href="#tab1"><s:text name="parameters.details"/></a></li>
                </ul>
			    <div>
			        <div id="tab1">
			            <div>
			                <table>
			                    <tr>
			                        <td class="form-text"><s:text name="parameters.column.code"></s:text></td>
			                        <td><input type="text" id="code" required='required'/></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="parameters.column.description"></s:text></td>
			                        <td><input type="text" id="description" required='required' style="width:500px;" /></td>
			                    </tr>
			                    <tr>
			                        <td class="form-text"><s:text name="parameters.column.value"></s:text></td>
			                        <td><input type="text" id="value" required='required' style="width:500px;"/></td>
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
                <li><a href="#tab1"><s:text name="parameters.list"/></a></li>
            </ul>
		    <div>
		        <div id="tab2">
			        <div id="tbllocal"></div>
			    </div>
			</div>
		</div>
        
        <!-- Remove confirmation dialog -->
        <div id="remove_dialog" title="<s:text name="dialog.remove.title"/>">
            <p><s:text name="dialog.remove.warning"/></p>
            <div class="pui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <button id="bt_remove_yes" type="button"><s:text name="dialog.button.si"/></button>
                <button id="bt_remove_no" type="button"><s:text name="dialog.button.no"/></button>
            </div>
        </div>
        
	</jsp:attribute>
</mdl:model>


