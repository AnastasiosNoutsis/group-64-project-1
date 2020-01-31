<b>${pageTitle}</b>
<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th>Όνομα χρήστη</th>
		</sec:authorize>
			<th>Ενεργός</th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th>Ρόλος</th>
		</sec:authorize>
			<th>ΑΜ</th>
			<th>Όνομα</th>
			<th>Επίθετο</th>
			<th>email</th>
			<th>Τμήμα</th>
			<th colspan="2">Ενέργειες</th>
		</tr>
		<!-- loop over and print our users -->
		<c:forEach var="tempUser" items="${students}">
			<tr>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>${tempUser.username}</td>
			</sec:authorize>
				<td>${tempUser.enabled}</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>${tempUser.role.authority}</td>
			</sec:authorize>
				<td>${tempUser.am}</td>
				<td>${tempUser.first_name}</td>
				<td>${tempUser.last_name}</td>
				<td>${tempUser.email}</td>
				<td>${tempUser.department.dep_name}</td>
				<td><form action="editStudent" method="get">
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempUser.username}'; return false;" />
					</form>
				</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><form action="${tempUser.username}/deleteStudent" method="get">
						<input type="submit" value="Διαγραφή" />
					</form>
				</td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<form>
		<input type="button" value="Δημιουργία χρήστη"
			onclick="window.location.href='addStudent'; return false;" />
	</form>
	</sec:authorize>
</div>
