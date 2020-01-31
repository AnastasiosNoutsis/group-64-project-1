
<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
	<c:if test="${param.error != null}">
		<b><i>Μη έγκυρο username/password!</i></b>
	</c:if>
	<table>
		<tr>
			<td>Όνομα χρήστη</td>
			<td><input type="text" name="username" /></td>
		</tr>
		<tr>
			<td>Κωδικός</td>
			<td><input type="password" name="password" /></td>
		</tr>
	</table>
	<button type="submit">Είσοδος</button>
</form:form>
