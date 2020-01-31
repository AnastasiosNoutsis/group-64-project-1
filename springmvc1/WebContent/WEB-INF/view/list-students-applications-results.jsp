<b>${pageTitle}</b>
<div id="content">
	<!--  add our HTML table here -->
	<table>
		<tr>
			<th>Όνομα χρήστη</th>
			<th>Ενεργός</th>
			<th>ΑΜ</th>
			<th>Όνομα</th>
			<th>Επίθετο</th>
			<th>Τμήμα</th>
			<th>Α/Α αίτησης</th>
			<th>Κατάσταση αίτησης</th>
			<th>Βαθμός</th>
			<th>Σειρά</th>
			<th>Αποτέλεσμα</th>
		</tr>
		<!-- loop over and print our users -->
		<c:forEach var="tempUser" items="${students}">
			<tr>
				<td>${tempUser.username}</td>
				<td>
					<c:if test="${tempUser.enabled == 0}">OXI</c:if>
					<c:if test="${tempUser.enabled == 1}">ΝΑΙ</c:if>
				</td>
				<td>${tempUser.am}</td>
				<td>${tempUser.first_name}</td>
				<td>${tempUser.last_name}</td>
				<td>${tempUser.department.dep_name}</td>
				<td>${tempUser.application.app_id}</td>
				<td>
					<c:if test="${tempUser.application.eggrisi==0}">ΔΕΝ ΕΞΕΤΑΣΤΗΚΕ</c:if>
					<c:if test="${tempUser.application.eggrisi==1}">ΕΓΚΡΙΘΗΚΕ</c:if>
					<c:if test="${tempUser.application.eggrisi==-1}">ΑΠΟΡΡΙΦΘΗΚΕ</c:if>
					<c:if test="${tempUser.application.eggrisi==null}">ΔΕΝ ΥΠΟΒΛΗΘΗΚΕ</c:if>
				</td>
				<td>${tempUser.application.vathmos}</td>
				<td>${tempUser.application.seira}</td>
				<td>
					<c:if test="${tempUser.application.result==-1}">ΔΕΝ ΔΙΚΑΙΟΥΤΑΙ ΣΙΤΙΣΗ</c:if>
					<c:if test="${tempUser.application.result==1}">ΔΙΚΑΙΟΥΤΑΙ ΣΙΤΙΣΗ</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
