<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mdl" %>

<mdl:model>

	<jsp:attribute name="head">
	
		<style>
		</style>
	
		<script src="resources/js/reniec-query-service.js"></script>
		<script>

			$(document).ready(function() {

				$('#numberIdForSearch').puiinputtext(); 
				$('#numberId').puiinputtext(); 
                $('#checkDigit').puiinputtext(); 
                $('#names').puiinputtext();
                $('#fatherLastName').puiinputtext();
                $('#motherLastName').puiinputtext();
                $('#addressPlaceCode').puiinputtext();
                $('#addressLocation').puiinputtext();
                $('#marriedStatus').puiinputtext();
                $('#levelInstruction').puiinputtext();
                $('#height').puiinputtext();
                $('#sex').puiinputtext();
                $('#birthDate').puiinputtext();
                $('#birthPlaceCode').puiinputtext();
                $('#birthLocation').puiinputtext();
                $('#fatherNumberId').puiinputtext();
                $('#fatherNames').puiinputtext();
                $('#fatherFatherLastName').puiinputtext();
                $('#fatherMotherLastName').puiinputtext();
                $('#motherNumberId').puiinputtext(); 
                $('#motherNames').puiinputtext(); 
                $('#motherFatherName').puiinputtext();
                $('#motherMotherLastName').puiinputtext();
                $('#registerDate').puiinputtext();
                $('#expeditionDate').puiinputtext();
                $('#expirationDate').puiinputtext();
                $('#restrictions').puiinputtext();
                $('#fullAddress').puiinputtextarea();
                
                $('#data').puitabview();
                
                $('#messages').puigrowl();
                
				$('#numberIdForSearch').focus();

                window.onload = onLoad;

                /* BOTON BUSCAR */
                $('#search_form').submit(function(event) {

                    var numberIdForSearch =  $('#numberIdForSearch').val();

                    jBlock();
                    
                    $.getJSON('queryByAdultId', 
                         {numberId : numberIdForSearch}, 
                         function(jsonResponse) {
                             if (jsonResponse != null) {
                                 if (jsonResponse.jsonErrorMessage == null) {
                                     
                                    showData(jsonResponse.jsonData);
                                    jUnblock();
                                 }
                                 else
                                     {
                             	 		$("#foto").removeAttr("src")
                                	 	$("#firma").removeAttr("src")
                                	 	document.getElementById("search_form").reset();
                        				$('#numberIdForSearch').focus(); 
                                	 	
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

        	 	var data = JSON.parse(jsonData).data;

    			$('#numberId'            ).val(data.numeroDNI                        );
    			$('#checkDigit'          ).val(data.digitoVerificacion               );
    			$('#names'               ).val(data.nombres                          );
    			$('#fatherLastName'      ).val(data.apellidoPaterno                  );
    			$('#motherLastName'      ).val(data.apellidoMaterno                  );
    			$('#addressPlaceCode'    ).val(data.codigoUbigeoDomicilio            );
    			$('#addressLocation'     ).val(data.localidadDomicilio               );
    			$('#fullAddress'         ).val(data.direccionCompleta                );
    			$('#marriedStatus'       ).val(data.estadoCivil                      );
    			$('#levelInstruction'    ).val(data.gradoInstruccion                 );
    			$('#height'              ).val(getHeigth(data.estatura)              );
    			$('#sex'                 ).val(data.sexo                             );
    			$('#levelInstruction'    ).val(data.nivelEducacion                   );
    			$('#birthDate'           ).val(getFormattedDate(data.fechaNacimiento));
    			$('#birthPlaceCode'      ).val(data.codigoUbigeoNacimiento           );
    			$('#birthLocation'       ).val(data.localidadNacimiento              );
    			$('#fatherNumberId'      ).val(data.numeroDNIPadre                   );
    			$('#fatherNames'         ).val(data.nombresPadre                     );
    			$('#fatherFatherLastName').val(data.apellidoPaternoPadre             );
    			$('#fatherMotherLastName').val(data.apellidoMaternoPadre             );
    			$('#motherNumberId'      ).val(data.numeroDNIMadre                   );
    			$('#motherNames'         ).val(data.nombresMadre                     );
    			$('#motherFatherNames'   ).val(data.apellidoPaternoMadre             );
    			$('#motherMotherLastName').val(data.apellidoMaternoMadre             );
    			$('#registerDate'        ).val(getFormattedDate(data.fechaRegistro)  );
    			$('#expeditionDate'      ).val(getFormattedDate(data.fechaExpedicion));
    			$('#expirationDate'      ).val(getFormattedDate(data.fechaExpiracion));
    			$('#restrictions'        ).val(data.restriccion                      );

    			setPhoto(data);
    			setSignature(data);

            }  

            function setPhoto(data) {

  			  	var img = document.getElementById('foto');
          	
    			if ( data.longitudFoto > 0 )
  				  	img.setAttribute("src", 'data:image/jpg;base64,' + data.fotografiaBase64);
    			else
       				if ( data.sexo = '1')  
       					img.setAttribute("src", 'resources/download/male.jpg'); 
       				else
       					img.setAttribute("src", 'resources/download/female.jpg'); 
				  	 
  			}

            function setSignature(data) {
                
  			  	var img = document.getElementById('firma');

    			if ( data.longitudFirma > 0 )
  				  	img.setAttribute("src", 'data:image/jpg;base64,' + data.firmaBase64);
    			else 
            	 	$("#firma").removeAttr("src")
            }

            function  onLoad() {
                var numberIdForSearch = getParameter("id");

                if (numberIdForSearch !=  null)
                {
                    jBlock();

                    $('#numberIdForSearch').val(numberIdForSearch);
                    $('#numberIdForSearch').prop('readonly', true);
                    $('numberIdForSearch').attr('font-weight', 'bold');
                    $('#bt_search').hide();

                    $.getJSON('queryByAdultId', 
                            {numberId : numberIdForSearch}, 
                            function(jsonResponse) {
                                if (jsonResponse != null) {
                                    if (jsonResponse.jsonErrorMessage == null) {

                                    	showData(jsonResponse.jsonData);
                                    	jUnblock();
                                	}
                                    else {
                                	 	  $("#foto").removeAttr("src")
                                   	 	  $("#firma").removeAttr("src")
                                   	 	  document.getElementById("search_form").reset();

                                     	  jUnblock();

                                     	  $('#messages').puigrowl('show', [{
                                               severity : 'info',
                                               summary : "<s:text name='page.search.by.adultId'/>",
                                               detail : jsonResponse.jsonErrorMessage
                                       	  }]);
									}
                                }
                       }); 
                }

            }
      			

		</script>
	</jsp:attribute>

	<jsp:attribute name="body">
        <div id="messages"></div>
        <span id="title"><s:text name='page.search.by.adultId'/></span>
        <form id="search_form">  
<%-- 			<c:set var="role"><%=request.getSession().getAttribute("idLogin").toString();%></c:set>
			<input type="hidden" id="role" value=<%= role %> /> --%>
        	<div style="margin-top: 20px;">
                <table>
                    <tr>
                        <td width="120" class="form-text"><s:text name="datos.numero.dni.busca"></s:text></td>
                        <td><input id="numberIdForSearch"
                        			class="input-code" 
                        			type="text"  
                        			maxlength="8" 
                        			required="required"
                        			onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
                        </td>
                        <td>
                        	<button id="bt_search" type="submit" title="<s:text name='button.search.tip'/>">
                        		<s:text name="button.search"/>
                        	</button>
                        </td>
                    </tr>
                </table>
        	</div>
            <div id="data" style="margin-top: 20px;" >
			    <ul>
			        <li><a href="#tab1"><s:text name="page.tab.identity"/></a></li>
			        <li><a href="#tab2"><s:text name="page.tab.parents"/></a></li>
			    </ul>
			    <div>
			        <div id="tab1">
						<table>
							<tr>
								<td id='images' style="vertical-align: top; width: 20%;">
							        <img id='foto' class="photo"/>
							        <img id='firma' class="signature" />
								</td>
								<td style="vertical-align: top;">
					                <table>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.numero.dni"></s:text></td>
					                        <td>
					                        	<input type="text" id="numberId" readonly="readonly" />
					                        	<input type="text" id="checkDigit" readonly="readonly" style='width:10px;' />
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.nombres"></s:text></td>
					                        <td colspan="3"><input type="text" id="names" readonly="readonly" style="width:465px;" /></td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.apellidos"></s:text></td>
					                        <td><input type="text" id="fatherLastName" readonly="readonly" style="width:225px;"/></td>
					                        <td><input type="text" id="motherLastName" readonly="readonly" style="width:225px;"/></td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.lugar.domicilio"></s:text></td>
					                        <td colspan="3">
					                        	<input type="text" id="addressPlaceCode" readonly="readonly" style='width:50px;'/>
					                        	<input type="text" id="addressLocation" readonly="readonly" style='width:400px;'/>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.direccion"></s:text></td>
					                        <td colspan="3"><textarea id="fullAddress" style='width:464px'></textarea></td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.estado.civil"></s:text></td>
					                        <td><input type="text" id="marriedStatus" readonly="readonly" style="width:220px;"/></td>
					                        <td class="form-text">
					                        	<table>
					                        		<tr>
					                        			<td width="100"><s:text name="datos.sexo"></s:text></td>
					                        			<td><input type="text" id="sex" readonly="readonly" /></td>
					                        		</tr>
					                        	</table>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.nivel.educacion"></s:text></td>
					                        <td><input type="text" id="levelInstruction" readonly="readonly" style="width:220px;"/></td>
					                        <td class="form-text">
					                        	<table>
					                        		<tr>
					                        			<td width="100"><s:text name="datos.estatura"></s:text></td> 
					                        			<td><input type="text" id="height" readonly="readonly" /></td>
					                        		</tr>
					                        	</table>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.fecha.nacimiento"></s:text></td>
					                        <td><input type="text" id="birthDate" readonly="readonly" style="width:220px;"/></td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.lugar.nacimiento"></s:text></td>
					                        <td colspan="3">
					                        	<input type="text" id="birthPlaceCode" readonly="readonly"  style='width:50px;' />
					                        	<input type="text" id="birthLocation" readonly="readonly" style='width:400px; text-align: left;' />
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.fecha.registro"></s:text></td>
					                        <td><input type="text" id="registerDate" readonly="readonly" style="width:220px;"/></td>
					                        <td class="form-text">
					                        	<table>
					                        		<tr>
					                        			<td width="100"><s:text name="datos.fecha.expedicion"></s:text></td>
					                        			<td><input type="text" id="expeditionDate" readonly="readonly" /></td>
					                        		</tr>
					                        	</table>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.fecha.expiracion"></s:text></td>
					                        <td><input type="text" id="expirationDate" readonly="readonly" style="width:220px;"/></td>
					                    </tr>
					                    <tr>
					                        <td class="form-text"><s:text name="datos.restricciones"></s:text></td>
					                        <td><input type="text" id="restrictions" readonly="readonly" style="width:220px;"/></td>
					                    </tr>
					                </table>
								</td>
							</tr>
						</table>
			        </div>
			        <div id="tab2">
		                <table>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.dni.padre"></s:text></td>
		                        <td><input type="text" id="fatherNumberId" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.nombres.padre"></s:text></td>
		                        <td><input type="text" id="fatherNames" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.paterno.padre"></s:text></td>
		                        <td><input type="text" id="fatherFatherLastName" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.materno.padre"></s:text></td>
		                        <td><input type="text" id="fatherMotherLastName" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                    <tr style="height: 25px;"></tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.dni.madre"></s:text></td>
		                        <td><input type="text" id="motherNumberId" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.nombres.madre"></s:text></td>
		                        <td><input type="text" id="motherNames" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.paterno.madre"></s:text></td>
		                        <td><input type="text" id="motherFatherName" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                    <tr>
		                        <td class="form-text"><s:text name="datos.materno.madre"></s:text></td>
		                        <td><input type="text" id="motherMotherLastName" readonly="readonly" style="width: 350px;" /></td>
		                    </tr>
		                </table>
			        </div>
			    </div>
            </div>  
        </form>
        
	</jsp:attribute>
</mdl:model>


