<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<h2>Add Feedback:</h2>

<spring:url value="/owners" var="formUrl"/>
<form:form modelAttribute="feedback" action="${fn:escapeXml(formUrl)}" method="get">
  <table>
    <tr>
      <th>
        Search by Last Name or click 'Find Owners' to search all: <form:errors path="*" cssClass="errors"/>
        <br/>
        <form:input path="lastName" size="30" maxlength="80" />
      </th>
    </tr>
    <tr>
      <td><p class="submit"><input id="findowners" type="submit" value="Find Owners"/></p></td>
    </tr>
  </table>
</form:form>

<br/>
<a href='<spring:url value="/owners/new1" htmlEscape="true"/>'>Add Feedback</a>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
