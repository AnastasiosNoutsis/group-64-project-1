<b>${pageTitle}</b>
<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th>Όνομα χρήστη</th>
			<th>Ενεργός</th>
			<th>Ρόλος</th>
		</sec:authorize>
			<th>ΑΜ</th>
			<th>Όνομα</th>
			<th>Επίθετο</th>
			<th>email</th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th colspan="2">Ενέργειες</th>
		</sec:authorize>
		</tr>
		<!-- loop over and print our users -->
		<c:forEach var="tempUser" items="${employees}">
			<tr>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>${tempUser.username}</td>
				<td>${tempUser.enabled}</td>
				<td>${tempUser.role.authority}</td>
			</sec:authorize>
				<td>${tempUser.am}</td>
				<td>${tempUser.first_name}</td>
				<td>${tempUser.last_name}</td>
				<td>${tempUser.email}</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><form>
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempUser.username}'; return false;" />
					</form></td>
				<td><form action="${tempUser.username}/deleteEmployee" method="get">
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
			onclick="window.location.href='addEmployee'; return false;" />
	</form>
	</sec:authorize>
</div>
