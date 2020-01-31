<form:form action="saveRole" modelAttribute="role" method="POST">
	<table>
		<tr>
			<td>Ρόλος</td>
			<td>
			<c:if test="${authority != null}">
					<form:input id="ro" path="authority" value="${authority}" readonly="true" style="background-color:#CCFFCC"/>
					</c:if>
				<c:if test="${authority == null}">
					<form:input id="ro" path="authority" />
				</c:if>
			<td>
		</tr>
		<tr>
			<td>Περιγραφή</td>
			<td><form:input id="desc" path="description" /></td>
		</tr>
	</table>
	<button type="submit">Αποθήκευση</button>
</form:form>

<!-- onClick='alert(document.characterSet);alert(document.getElementById("ro").value);alert(document.getElementById("desc").value);return false;' -->