<form:form action="saveService" modelAttribute="service" method="POST">
	<table>
		<tr>
			<td>Υπηρεσία</td>
			<td><c:if test="${service_name != null}">
					<form:input path="service_name" value="${service_name}" readonly="true" style="background-color:#CCFFCC"/>
					</c:if>
				<c:if test="${service_name == null}">
					<form:input path="service_name" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Περιγραφή</td>
			<td><form:input path="description" /></td>
		</tr>
		<tr>
			<td>Ρόλοι</td>
			<td><form:select path="roles" multiple="true" size="5">
				<form:options items="${roles}" itemValue="authority" itemLabel="authority"/>
				</form:select>
			</td>
		</tr>
		<tr><td colspan="2"><input type="submit" value="Αποθήκευση" /></td>
		</tr>
	</table>
	
</form:form>
