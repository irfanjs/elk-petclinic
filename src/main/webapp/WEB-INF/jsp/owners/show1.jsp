<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>Feedback Information</h2>

  <table>
    <tr>
      <th>Name</th>
      <td><b>${feedback.firstName} ${feedback.lastName}</b></td>
    </tr>
    <tr>
      <th>Address</th>
      <td>${feedback.address}</td>
    </tr>
    <tr>
      <th>City</th>
      <td>${feedback.city}</td>
    </tr>
    <tr>
      <th>Telephone </th>
      <td>${feedback.telephone}</td>
    </tr>
    <tr>
      <th>Details </th>
      <td>${feedback.details}</td>
    </tr>
  </table>
  
    
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
