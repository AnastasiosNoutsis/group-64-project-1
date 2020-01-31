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
			<th>email</th>
			<th>Τμήμα</th>
			<th>Κατάσταση αίτησης</th>
			<th colspan="2">Ενέργειες</th>
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
				<td>${tempUser.email}</td>
				<td>${tempUser.department.dep_name}</td>
				<td>
					<c:if test="${tempUser.application.eggrisi==0}">ΔΕΝ ΕΞΕΤΑΣΤΗΚΕ</c:if>
					<c:if test="${tempUser.application.eggrisi==1}">
						ΕΓΚΡΙΘΗΚΕ, Βαθμολογία:${tempUser.application.vathmos}, Σειρά:${tempUser.application.seira}
						<c:if test="${tempUser.application.result==1}">--> ΔΙΚΑΙΟΥΤΑΙ ΣΙΤΙΣΗ</c:if>
						<c:if test="${tempUser.application.result==-1}">--> ΔΕΝ ΔΙΚΑΙΟΥΤΑΙ ΣΙΤΙΣΗ</c:if>
					</c:if>
					<c:if test="${tempUser.application.eggrisi==-1}">ΑΠΟΡΡΙΦΘΗΚΕ</c:if>
					<c:if test="${tempUser.application.eggrisi==null}">ΔΕΝ ΥΠΟΒΛΗΘΗΚΕ</c:if>
				</td>
				<td>
					<c:if test="${tempUser.enabled == 0}">
						<form action="${emp_username}/${tempUser.username}/enableStudent" method="get">
							<input type="submit" value="Ενεργοποίηση"/>
						</form>
					</c:if>
					<c:if test="${tempUser.enabled == 1}">
						<form action="${emp_username}/${tempUser.username}/enableStudent" method="get">
							<input type="submit" value="Ενεργοποίηση" disabled="true"/>
						</form>
					</c:if>
				</td>
				<td>
					<c:if test="${tempUser.application.eggrisi!=null}">
						<c:if test="${tempUser.application.eggrisi==0}">
							<form action="${pageContext.request.contextPath}/studentApplication/createStudentApplication/${tempUser.username}" method="get">
								<input type="submit" value="Έλεγχος αίτησης"/>
							</form>
						</c:if>
						<c:if test="${tempUser.application.eggrisi!=0}">
							<form action="${pageContext.request.contextPath}/studentApplication/createStudentApplication/${tempUser.username}" method="get">
								<input type="submit" value="Έλεγχος αίτησης" disabled="true"/>
							</form>
						</c:if>
					</c:if>
					<c:if test="${tempUser.application.eggrisi==null}">
						<form action="${pageContext.request.contextPath}/studentApplication/createStudentApplication/${tempUser.username}" method="get">
							<input type="submit" value="Έλεγχος αίτησης" disabled="true"/>
						</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
