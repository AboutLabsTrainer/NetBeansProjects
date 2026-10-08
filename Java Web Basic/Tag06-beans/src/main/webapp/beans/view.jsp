<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="person" class="de.ibb.beans.PersonBean" scope="request"/>
<jsp:setProperty name="person" property="*"/>
<jsp:forward page="result.jsp"><jsp:param name="source" value="forwarded"/></jsp:forward>
