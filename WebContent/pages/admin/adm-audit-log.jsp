<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>

	<jsp:attribute name="head">
	
		<style>
            .pui-datatable thead th:nth-child(1) { width: 40px;     }
            .pui-datatable thead th:nth-child(2) { width: 40px;     }
            .pui-datatable thead th:nth-child(3) { width: 300px;    }
            .pui-datatable thead th:nth-child(4) { width: 40px;     }
            .pui-datatable thead th:nth-child(5) { width: 15px;     }
                        
            .pui-datatable tbody td:nth-child(1) { text-align: center; } 
            .pui-datatable tbody td:nth-child(2) { text-align: center; } 
            .pui-datatable tbody td:nth-child(3) { text-align: left;   }
            .pui-datatable tbody td:nth-child(4) { text-align: center; }
            .pui-datatable tbody td:nth-child(5) { text-align: center; }
            
            .form-text { width: 100px; }
            .separa    { width: 80px;  }
            
            .view-text { width: 90px !important; font-weight: bold; }

		</style>
	
        <script src="resources/js/reniec-query-service.js"></script>
		<script>

			$( document ).ready(function() {
				
				$('#filter').puitabview();
				$('#list').puitabview();

				$('#eventType').puidropdown({data: getEvents()});
     	 		$('#eventType').puidropdown('selectValue', 'TODOS');
     	 		
				$('#userName').puiinputtext(); 
				$('#beginDate').puiinputtext(); 
                $("#beginDate").datePicker();				
				$('#endDate').puiinputtext(); 
                $("#endDate").datePicker();				


				$('#dialogId').puiinputtext();
				$('#dialogEvent').puiinputtext();
				$('#dialogUser').puiinputtext();
				$('#dialogDate').puiinputtext();
				$('#dialogDescription').puiinputtextarea();
				$('#dialogOSUser').puiinputtext();
				$('#dialogClientHost ').puiinputtext();				

				$('#bt_search').puibutton({icon: 'ui-icon-search'});
				$('#bt_clean').puibutton({icon: 'ui-icon-trash'});

                $('#messages').puigrowl();

                loadAuditLog("TODOS", null, null, null);        
                
                /* BOTON BUSCAR */
                $('#search_form').submit(function(event) {

                	var eventTypeSearch =  $('#eventType').val();
                    var userNameSearch  =  $('#userName').val();
                    var beginDateSearch =  $('#beginDate').val();
                    var endDateSearch   =  $('#endDate').val();

                    loadAuditLog(eventTypeSearch, userNameSearch, beginDateSearch, endDateSearch);        
    
                    event.preventDefault();
                });

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

						$('#remove_dialog').puidialog('hide');

 						$.getJSON('clearAuditLog',
							      function(jsonResponse) {
										if (jsonResponse != null) {
											loadAuditLog("TODOS", null, null, null);

                                            $('#messages').puigrowl('show', [ {
													severity : 'warn',
													summary : "<s:text name='audit.dialog.delete.title'/>",
													detail : "<s:text name='audit.clearall.event'/>"
											} ]);
										}

						});
				});

				/* BOTON NO */
				$('#remove_dialog #bt_remove_no').puibutton({icon : 'ui-icon-close'})
				    .click(function() {
				    	   $('#remove_dialog').puidialog('hide');
				});              

				/********* DIALOGO ELIMINAR**********/
				$('#view_dialog').puidialog({
					resizable : false,
					showEffect : 'fade',
					hideEffect : 'fade',
					minimizable : false,
					maximizable : false,
					modal : true,
					width : 380
				});

				/* BOTON NO */
				$('#view_dialog #bt_close').puibutton({icon : 'ui-icon-close'})
				    .click(function() {
				    	   $('#view_dialog').puidialog('hide');
				});              
			});

            /* BOTON ELIMINAR */
            function clean_log() {
                $('#remove_dialog').puidialog('show');
                $('#remove_dialog #bt_remove_no').focus();
            };
			
	        function loadAuditLog(eventTypeSearch, userNameSearch, beginDateSearch, endDateSearch) {

                jBlock();
                
                $.getJSON('loadAllAuditLog', 
                        { eventType: eventTypeSearch, userName: userNameSearch, beginDate: beginDateSearch, endDate:endDateSearch }, 
                        function(jsonResponse) {

                            if (jsonResponse != null) {
                                if (jsonResponse.jsonErrorMessage == null) {

        		            	 	var optionsTable = {  
        		                            caption: $.parseJSON(jsonResponse.jsonTableData).length + ' ' + "<s:text name='audit.table.title'/>",  
        		                            paginator: {  
        		                                rows: 15, 
        		                                totalRecords: $.parseJSON(jsonResponse.jsonTableData).length
        		                            },  
        		                            columns: [  
        		                                {field:'evento'    , headerText: "<s:text name='audit.column.event'/>"      , sortable:true},  
        		                                {field:'fecha'     , headerText: "<s:text name='audit.column.date'/>"       , sortable:true},
        		                                {field:'descipcion', headerText: "<s:text name='audit.column.description'/>", sortable:true},  
        		                                {field:'usuario'   , headerText: "<s:text name='audit.column.user'/>"       , sortable:true}, 
         		                                {field:'ver', sortable: false,
        		                                    content: 
        		                                        function(option) {  
        		                                        	
        		                                            return '<img src="resources/img/search-icon.png" height="22" width="22" title="Ver Datos" onclick="show_detail()" />';  
        		                                        }
       		                                    } 
        		                            ],  
        		                            datasource: [],
        		                            selectionMode: 'single'
        		                    };
        		
        		                    // Create the table
        		                    $('#tbllocal').puidatatable(optionsTable);
        		                    $('#tbllocal').puidatatable('option', 'datasource', $.parseJSON(jsonResponse.jsonTableData));
        		                    $('#tbllocal').puidatatable('sort', 'fecha', -1);
        		                    
        		                    var rows = $('tr', $('#tbllocal'));
        		                    $('#tbllocal').puidatatable('selectRow', rows.eq(0), false);

        		                    $('#tbllocal').puidatatable('option', 'caption', $.parseJSON(jsonResponse.jsonTableData).length);
        		                    
                                	jUnblock();
                                	
                                } else {
                               	 	document.getElementById("search_form").reset();
                                       $('#messages').puigrowl('show', [ {
                                           severity : 'info',
                                           summary : "<s:text name='page.audit.log.title'/>",
                                           detail : jsonResponse.jsonErrorMessage
                                   	}]);
                                  	jUnblock();
								 }
                            }

                   }); 

                jUnblock();
            };

            function show_detail() {

                var selections = $('#tbllocal').puidatatable('getSelection');

                if (selections.length === 1) {
                
					$('#dialogId').val(selections[0].id);
					$('#dialogEvent').val(selections[0].evento);
					$('#dialogUser').val(selections[0].usuario);
					$('#dialogDate').val(selections[0].fecha);
					$('#dialogDescription').val(selections[0].descipcion);
					$('#dialogOSUser').val(selections[0].usuario_so);
					$('#dialogClientHost ').val(selections[0].ip);				
	
	            	$('#view_dialog').puidialog('show');
	                $('#view_dialog #bt_close').focus();
                }
            }

        </script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.audit.log.title'/></span>
        <form id="search_form">  
	        <div id="filter" style="margin-top: 15px;">
			    <ul>
					<li><a href="#tab1"><s:text name="audit.options.search"/></a></li>
				</ul>
			    <div>
			        <div id="tab1">
		                <table>
		                    <tr>
		                        <td class="form-text"><s:text name="audit.event.type"></s:text></td>
		                        <td>
		                        	<select id="eventType" 
		                        			name="eventType" 
		                        			style="width: 140px;"
		                        			tabindex="1" 
		                        			autofocus="autofocus" >
		                        	</select>
		                        </td>
		                        <td class="separa" rowspan="2" />
		                        <td class="form-text"><s:text name="audit.user.name"></s:text></td>
		                        <td>
		                        	<input type="text" 
		                        		   id="userName" 
		                        		   tabindex="2"/>
		                        </td>
		                        <td rowspan="2">
		                        	<button id="bt_search" 
		                        			type="submit" 
		                        			title="<s:text name='button.search.tip'/>"
		                        			tabindex="5">
		                        		<s:text name="button.search"/>
		                        	</button>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="audit.begin.date"></s:text></td>
		                        <td>
		                        	<input type="text" 
		                        		   id="beginDate" 
		                        		   tabindex="3" />
		                        </td>
		                        <td class="form-text"><s:text name="audit.end.date"></s:text></td>
		                        <td><input type="text" 
		                        		   id="endDate" 
		                        		   tabindex="4" />
		                        </td>
		                    </tr>
		                 </table>
					</div>
				</div>
			</div>
        </form>

		<div id="list" style="margin-top: 20px;">
		    <ul>
		        <li><a href="#tab1"><s:text name="audit.list"/></a></li>
		    </ul>
		    <div>
		        <div id="tab1">
                      	<button id="bt_clean" type="button" 
                      			title="<s:text name='button.clean.tip'/>" 
                      			onclick="clean_log()" >
                      		<s:text name="button.clean"/>
                      	</button>
	        		<div id="tbllocal" style="margin-top: 20px;"></div>
		        </div>
		    </div>
		</div>

        <!-- View data dialog -->
        <div id="view_dialog" title="<s:text name="audit.dialog.view.title"/>">
            <div class="pui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
            	<table id="detail">
            		<tr>
            			<td class="view-text"><s:text name="audit.column.id"/></td>
            			<td><input type="text" id="dialogId" readonly="readonly"/></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.event"/></td>
            			<td><input type="text" id="dialogEvent" readonly="readonly"/></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.user"/></td>
            			<td><input type="text" id="dialogUser" readonly="readonly"/></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.date"/></td>
            			<td><input type="text" id="dialogDate" readonly="readonly"/></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.description"/></td>
            			<td><textarea id="dialogDescription" style="width:255px;" readonly="readonly"></textarea></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.os.user"/></td>
            			<td><input type="text" id="dialogOSUser" readonly="readonly"/></td>
            		</tr>
            		<tr>
            			<td class="view-text"><s:text name="audit.column.client.host"/></td>
            			<td><input type="text" id="dialogClientHost" readonly="readonly"/></td>
            		</tr>
            	</table>
            </div>
            <div class="pui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <button id="bt_close" type="button"><s:text name="button.close"/></button>
            </div>
        </div>

        <!-- Remove confirmation dialog -->
        <div id="remove_dialog" title="<s:text name="audit.dialog.delete.title"/>">
            <p><s:text name="audit.dialog.delete.warning"/></p>
            <div class="pui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                <button id="bt_remove_yes" type="button"><s:text name="dialog.button.si"/></button>
                <button id="bt_remove_no" type="button"><s:text name="dialog.button.no"/></button>
            </div>
        </div>
	</jsp:attribute>
</mdl:model>


