<%@ attribute name="head" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="menu" fragment="true" %>
<%@ attribute name="body" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>

<!DOCTYPE html>

<html>
<meta http-equiv="Refresh" 
  content="${pageContext.session.maxInactiveInterval}; url=index.jsp"/>  
  <head>
 		<title>Consultas de Datos RENIEC</title>
  
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.structure.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.structure.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.theme.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.theme.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/primeui-1.1/themes/bootstrap/theme.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/primeui-1.1/development/primeui-1.1.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" />
        
		<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/external/jquery/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.11.0.custom/jquery.blockUI.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/primeui-1.1/development/primeui-1.1.js"></script>
			
     	<jsp:invoke fragment="head"/>
  </head>
 
  <body>
	<div style="width: 100%; height: auto;">
	    <div>
			<jsp:include page="/pages/template-top.jsp" flush="true"/>
	    	<jsp:invoke fragment="header"/>
	    </div>
	    <div style="float: left; width: 20%; height: auto;">
 			<jsp:include page="/pages/template-left.jsp" flush="true"/> 
	    	<jsp:invoke fragment="menu"/>
	    </div>
	    <div style="float: right; width: 80%; height: auto;">
	    	<jsp:invoke fragment="body"/>
	    </div>
	    <div style="clear: both;">
 			<jsp:include page="/pages/template-bottom.jsp" flush="true"/> 
	    	<jsp:invoke fragment="footer" />
	    </div>
	</div>    
  </body>

</html>

