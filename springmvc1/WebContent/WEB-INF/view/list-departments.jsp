<b>${pageTitle}</b>
<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
			<th>ID</th>
			<th>Όνομα τμήματος</th>
			<th>Όριο δωρεάν σίτισης <b>%</b></th>
			<th>Χωρητικότητα</th>
			<th>Εγγεγραμμένοι</th>
			<th>Έδρα - πόλη</th>
			<th>Υπεύθυνος υπάλληλος</th>
			<th colspan="2">Ενέργειες</th>
		</tr>
		<!-- loop over and print our users -->
		<c:forEach var="tempDep" items="${departments}">
			<tr>
				<td>${tempDep.id}</td>
				<td>${tempDep.dep_name}</td>
				<td>${tempDep.orio_dorean_sitisis}</td>
				<td>${tempDep.xoritikotita}</td>
				<td>${fn:length(tempDep.students)}</td>
				<td>${tempDep.location}</td>
				<td>${tempDep.employeeInCharge.first_name}&nbsp;${tempDep.employeeInCharge.last_name}</td>
				<td><form>
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempDep.id}'; return false;" />
					</form>
				</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><form action="${tempDep.id}/deleteDepartment" method="get">
						<input type="submit" value="Διαγραφή" />
					</form>
				</td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<form>
		<input type="button" value="Δημιουργία τμήματος"
			onclick="window.location.href='addDepartment'; return false;" />
	</form>
	</sec:authorize>
</div>
