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
		</style>
	
        <script src="resources/js/reniec-query-service.js"></script>
		<script>

			$( document ).ready(function() {
				
				$('#names').puiinputtext(); 
				$('#fatherLastName').puiinputtext(); 
				$('#motherLastName').puiinputtext(); 
				$('#fatherLastName').focus(); 

                $('#data').puitabview();
				
                $('#messages').puigrowl();
                
                /* BOTON BUSCAR */
                $('#search_form').submit(function(event) {
//                    var result = $("#search_form")[0].checkValidity();

                      jBlock();
                      
                      var namesSearch          =  encodeURI($('#names').val());
                      var fatherLastNameSearch =  encodeURI($('#fatherLastName').val());
                      var motherLastNameSearch =  encodeURI($('#motherLastName').val());
                        
                      $.getJSON('queryByNames', 
                           {names : namesSearch, fatherLastName: fatherLastNameSearch, motherLastName: motherLastNameSearch}, 
                           function(jsonResponse) {
                               if (jsonResponse != null) {
                                   if (jsonResponse.jsonErrorMessage == null) {
                                      showData(jsonResponse.jsonData);
                                   	  jUnblock();
                                   }
                                   else {
                                  	 	document.getElementById("search_form").reset();

                                  	 	jUnblock();

                                  	 	$('#messages').puigrowl('show', [ {
                                              severity : 'info',
                                              summary : "<s:text name='page.search.by.adultId'/>",
                                              detail : jsonResponse.jsonErrorMessage
                                      	}]);
							 	   }
                               }
                      }); 
    
                    event.preventDefault();
                });        
			});

            function showData(jsonData) {
            	
        	 	var optionsTable = {  
                        caption: $.parseJSON(jsonData).length + ' ' + "<s:text name='table.names.title'/>",  
                        paginator: {  
                            rows: 10, 
                            totalRecords: $.parseJSON(jsonData).length
                        },  
                        columns: [  
                            {field:'numeroDNI'      , headerText: "<s:text name='datos.numero.dni'/>"       , headerClass: 'header-table',  sortable:true,  
                             content: 
                                 function(option) {  
                                       return option.numeroDNI + ' - ' + option.digitoVerificacion;
                                 }
                            },
                            {field:'apellidoPaterno', headerText: "<s:text name='datos.apellido.materno'/>" , sortable:true}, 
                            {field:'apellidoMaterno', headerText: "<s:text name='datos.apellido.paterno'/>" , sortable:true},  
                            {field:'nombres'        , headerText: "<s:text name='datos.nombres'/>"          , sortable:true}, 
                            {field:'mostrarDatos'   , headerText: "<s:text name='datos.es.mostrable'/>"     , sortable:false,
                             content: 
	                             function(option) {  
	                             	var mostrar = '';
	
	                             	switch(option.mostrarDatos)
	                             	{
		                          		case 'N': mostrar = 'NO'; break;
		                          		case 'S': mostrar = 'SI'; break;
	                                }
                                    return mostrar;
	                             }
                             },
                            {field:'existeFoto'     , headerText: "<s:text name='datos.es.imagen'/>"        ,  sortable:false,
                             content: 
	                             function(option) {  
	                             	var mostrar = '';
	
	                             	switch(option.mostrarDatos)
	                             	{
		                          		case 'N': mostrar = 'NO'; break;
		                          		case 'S': mostrar = 'SI'; break;
	                                }
                                    return mostrar;
	                             }
                            },
                            {field:'ver', sortable: false,
                             content: 
                                 function(option) {  
                                       return '<img src="resources/img/go-icon.png" title="Ver Datos" onclick="show_for_id(' + option.numeroDNI + ')" />';  
                                 }
                            }
                        ],  
                        datasource: [],
                        selectionMode: 'single',                        
                            rowSelect: function(event, data) {
                                $('#numeroDNI').val(data.numeroDNI);
                            }  
                    };

                // Create the table
                $('#tbllocal').puidatatable(optionsTable);
                $('#tbllocal').puidatatable('option', 'datasource', $.parseJSON(jsonData));
                $('#tbllocal').puidatatable('sort', 'nombres', 1);
                               
                var rows = $('tr', $('#tbllocal'));
                $('#tbllocal').puidatatable('selectRow', rows.eq(0), false);
            };

            function show_for_id(numeroDNI) {

            	window.location.href = 'searchByAdultIdAction?id=' + numeroDNI;
            }  

        </script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.search.by.names'/></span>
        <form id="search_form">  
        	<div style="margin-top: 20px;">
                <table>
                    <tr>
                        <td width="100" class="form-text"><s:text name="datos.apellido.paterno"></s:text></td>
                        <td>
                        	<input type="text" 
                        		   id="fatherLastName" 
                        		   required="required" 
                        		   style="width: 250px;" 
                        		   tabindex="1"/>
                        </td>
                        <td rowspan="3">
                        	<button id="bt_search" 
                        			type="submit" 
                        			title="<s:text name='button.search.tip'/>"  
                        			tabindex="4">
                        		<s:text name="button.search"/>
                        	</button>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" class="form-text"><s:text name="datos.apellido.materno"></s:text></td>
                        <td>
                        	<input type="text" 
                        		   id="motherLastName" 
                        		   required="required" 
                        		   style="width: 250px;" 
                        		   tabindex="2" />
                        </td>
                    </tr>
                    <tr>
                        <td width="100" class="form-text"><s:text name="datos.nombres"></s:text></td>
                        <td><input type="text" 
                        		   id="names" 
                        		   style="width: 250px;"  
                        		   tabindex="3" />
                        </td>
                    </tr>
                 </table>
                
	           <div id="data" style="margin-top: 20px;">
				    <ul>
				        <li><a href="#tab1"><s:text name="page.tab.matches"/></a></li>
				    </ul>
				    <div>
				        <div id="tab1">
			        		<div id="tbllocal" style="margin-top: 20px;"></div>
				        </div>
				    </div>
				</div>
                
        	</div>
        </form>
	</jsp:attribute>
</mdl:model>


