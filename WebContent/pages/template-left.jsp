<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
 	.pui-panel { width: 212px; height: 55px;}
	.user {
	    word-wrap: normal;
	    height: 55px;
	    border: none;
	}
</style>

<script> 
	$(document).ready(function() {
		
		$('#pm1').puimenu();
		$('#panel').puipanel();

		var user = ${session['user']};

        if (user.user != 'admin') {

    		$("#userLogged").val(user.names);
    		
    		$('#mnuAdmTitle').hide();
           	$('#mnuAdm1').hide();
           	$('#mnuAdm2').hide();
           	$('#mnuAdm3').hide();
       	}
        else {
    		$("#userLogged").val(user.names);

    		$('#mnuSchTitle').hide();
           	$('#mnuSch1').hide();
           	$('#mnuSch2').hide();
           	$('#mnuSch3').hide();
           	$('#mnuSch4').hide();
        }       	
    }); 
</script> 

<div id="panel" >
	<table>
		<tr>
			<td class="ddl-menu-icon"></td>
			<td><input type="text" id="userLogged" readonly="readonly" class="user"/></td>
		</tr>
	</table>
</div>
<div>
	<ul id="pm1">  
	    <li id="mnuSchTitle"><h3><s:text name='menu.search.title'/></h3></li>  
	    <li id="mnuSch1"> <a href="searchByAdultIdAction"><s:text name='menu.search.by.adultId'/></a></li>  
	    <li id="mnuSch2"> <a href="searchByChildIdAction"><s:text name='menu.search.by.childId'/></a></li>  
	    <li id="mnuSch3"> <a href="searchByNamesAction"><s:text name='menu.search.by.names'/></a></li>  
	    <li id="mnuSch4"> <a href="searchByBatch"><s:text name='menu.search.by.batch'/></a></li>   
	    
	    <li id="mnuAdmTitle"><h3><s:text name='menu.admin.title'/></h3></li>  
	    <li id="mnuAdm1"> <a href="adminParameters"><s:text name='menu.admin.parameters'/></a></li>  
	    <li id="mnuAdm2"> <a href="adminUsers"><s:text name='menu.admin.users'/></a></li>
	    <li id="mnuAdm3"> <a href="adminAuditLog"><s:text name='menu.admin.auditlog'/></a></li>
	      
	    <li><h3><s:text name='menu.tools.title'/></h3></li>  
	    <li> <a href="toolsChangePassword"><s:text name='menu.tools.chgpasswd'/></a></li>  
	    <li> <a href="toolsLogout"><s:text name='menu.tools.exit'/></a></li>  
	</ul> 
</div>

