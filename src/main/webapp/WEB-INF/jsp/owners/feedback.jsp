<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Please provide the Feedback:</h2>

<c:choose>
    <c:when test="${feedback['new']}"><c:set var="method" value="post"/></c:when>
    <c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${feedback['new']}">New </c:if>Feedback:</h2>
<form:form modelAttribute="feedback" method="${method}">

  <table>
    <tr>
      <th>
        First Name: <form:errors path="firstName" cssClass="errors"/>
        <br/>
        <form:input path="firstName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Last Name: <form:errors path="lastName" cssClass="errors"/>
        <br/>
        <form:input path="lastName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Address: <form:errors path="address" cssClass="errors"/>
        <br/>
        <form:input path="address" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        City: <form:errors path="city" cssClass="errors"/>
        <br/>
        <form:input path="city" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Telephone: <form:errors path="telephone" cssClass="errors"/>
        <br/>
        <form:input path="telephone" size="20" maxlength="20"/>
      </th>
    </tr>
    <tr>
      <th>
        Details: <form:errors path="details" cssClass="errors"/>
        <br/>
        <form:input path="details" size="30" maxlength="150"/>
      </th>
    </tr>
    <tr>
      <td>
        <p class="submit"><input id="addfeedback" type="submit" value="Submit"/></p>
          </td>
    </tr>
  </table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>