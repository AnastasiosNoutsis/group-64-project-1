<b>${pageTitle}</b>
<form:form action="saveDepartment" modelAttribute="department" method="post" id="department_form">
	<form:hidden path="id"/>
	<table>
		<tr>
			<td>Όνομα τμήματος</td>
			<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<form:input path="dep_name"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_EMPLO_MANAGER')">
					<form:input path="dep_name" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr>
		<tr>
			<td>Όριο δωρεάν σίτισης <b>%</b></td>
			<td><form:input path="orio_dorean_sitisis" type="number" min="0" max="100"/></td>
		</tr>
		<tr>
			<td>Χωρητικότητα</td>
			<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<form:input path="xoritikotita" type="number" min="0" max="1000"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_EMPLO_MANAGER')">
					<form:input path="xoritikotita" type="number" min="0" max="1000" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr> 
		<tr>
			<td>Πόλη</td>
			<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<form:input path="location"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_EMPLO_MANAGER')">
					<form:input path="location" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr>
		<tr>
			<td>Υπεύθυνος υπάλληλος</td>
			<td><form:select path="employeeInCharge.username" >
					<form:option value="" label="---"/>
					<c:forEach var="tempEmp" items="${employees}">
						<form:option value="${tempEmp.username}">${tempEmp.first_name}&nbsp;${tempEmp.last_name}</form:option>
					</c:forEach>
				</form:select>
			</td>
		</tr>
	</table>
	<button type="submit">Αποθήκευση</button>
</form:form>
