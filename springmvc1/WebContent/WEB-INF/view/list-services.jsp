<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
			<th>Υπηρεσία</th>
			<th>Περιγραφή</th>
			<th>Ρόλοι με δικαίωμα χρήσης</th>
			<th colspan="2">Ενέργειες</th>
		</tr>
		<!-- loop over and print our services -->
		<c:forEach var="tempService" items="${services}">
			<tr>
				<td>${tempService.service_name}</td>
				<td>${tempService.description}</td>
				<td><c:set var="list_of_roles_string" value="" /> 
					<c:forEach var="tempRole" items="${tempService.roles}">
						<c:set var="list_of_roles_string" value="${list_of_roles_string} ${tempRole.authority}" />
					</c:forEach> 
					<c:set var="list_of_roles_string" value="${fn:trim(list_of_roles_string)}" />
					[${fn:replace(list_of_roles_string, " ", ", ")}]
				</td>
				<td><form>
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempService.service_name}'; return false;" />
					</form></td>
				<td><form action="${tempService.service_name}/deleteService" method="get">
						<input type="submit" value="Διαγραφή" />
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<form>
		<input type="button" value="Δημιουργία υπηρεσίας"
			onclick="window.location.href='addService'; return false;" />
	</form>
</div>
