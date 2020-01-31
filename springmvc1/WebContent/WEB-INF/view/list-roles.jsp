<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
			<th>Ρόλος</th>
			<th>Περιγραφή</th>
			<th colspan="2">Ενέργειες</th>
		</tr>
		<!-- loop over and print our roles -->
		<c:forEach var="tempRole" items="${roles}">
			<tr>
				<td>${tempRole.authority}</td>
				<td>${tempRole.description}</td>
				<td><form>
						<input type="button" value="Αλλαγή"
							onclick="window.location.href='${tempRole.authority}'; return false;" />
					</form></td>
				<td><form action="${tempRole.authority}/deleteRole" method="get">
						<input type="submit" value="Διαγραφή" />
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<form>
		<input type="button" value="Δημιουργία ρόλου"
			onclick="window.location.href='addRole'; return false;" />
	</form>
</div>
