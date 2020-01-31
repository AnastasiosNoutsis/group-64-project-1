<b>${pageTitle}</b>
<form:form action="saveEmployee" modelAttribute="employee" method="post"
	id="employer_form">
	<table>
		<tr>
			<td>Όνομα χρήστη</td>
			<td><c:if test="${username != null}">
					<form:input path="username" value="${username}" readonly="true" style="background-color:#CCFFCC"/>
					</c:if>
				<c:if test="${username == null}">
					<form:input path="username" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Κωδικός</td>
			<td><form:input path="password" type="password" /></td>
		</tr>
		<tr>
			<td>Ενεργός</td>
			<td><form:input path="enabled" type="number" min="0" max="1"/></td>
		</tr>
		<tr>
			<td>Ρόλος</td>
			<td><c:if test="${username != null}">
						<form:input path="role.authority" value="${role.authority}" readonly="true" style="background-color:#CCFFCC"/>
				</c:if> <c:if test="${username == null}">
					<form:select path="role.authority">
						<c:forEach var="tempRole" items="${roles}">
							<form:option value="${tempRole.authority}">${tempRole.authority}</form:option>
						</c:forEach>
					</form:select>
				</c:if></td>
		</tr>
		<tr>
			<td>AM/ΑΔΤ</td>
			<td><form:input path="am" /></td>
		</tr>
		<tr>
			<td>Όνομα</td>
			<td><form:input path="first_name" type="text"/></td>
		</tr>
		<tr>
			<td>Επώνυμο</td>
			<td><form:input path="last_name" type="text"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><form:input path="email" type="email"/></td>
		</tr>
		<tr>

	</table>
	<button type="submit">Αποθήκευση</button>
</form:form>
