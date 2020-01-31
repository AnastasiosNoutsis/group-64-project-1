<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
			<th>Όνομα χρήστη</th>
			<th>Ενεργός</th>
			<th>Ρόλος</th>
			<td>ΑΜ</td>
			<th>Όνομα</th>
			<th>Επίθετο</th>
			<th>email</th>
			<th colspan="2">Ενέργειες</th>
		</tr>
		<!-- loop over and print our users -->
		<c:forEach var="tempUser" items="${users}">
			<tr>
				<td>${tempUser.username}</td>
				<td>${tempUser.enabled}</td>
				<td>${tempUser.role.authority}</td>
				<td>${tempUser.am}</td>
				<td>${tempUser.first_name}</td>
				<td>${tempUser.last_name}</td>
				<td>${tempUser.email}</td>
				<td><form action="editUser" method="get">
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempUser.username}'; return false;" />
					</form></td>
				<td><form action="${tempUser.username}/deleteUser" method="get">
						<input type="submit" value="Διαγραφή" />
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<form>
		<input type="button" value="Δημιουργία χρήστη"
			onclick="window.location.href='addUser'; return false;" />
	</form>
</div>
