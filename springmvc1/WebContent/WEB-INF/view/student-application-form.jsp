<table>
	<tr>
		<td><b>ΚΑΤΑΣΤΑΣΗ ΑΙΤΗΣΗΣ:</b></td>
		<td><b>
			<c:if test="${studentApplicationTEMP.eggrisi==0}">ΑΝΑΜΟΝΗ ΓΙΑ ΕΞΕΤΑΣΗ</c:if>
			<c:if test="${studentApplicationTEMP.eggrisi==1}">
				<c:if test="${studentApplicationTEMP.result==1}">
					ΕΓΚΥΡΗ, Βαθμολογία:${studentApplicationTEMP.vathmos}, Σειρά:${studentApplicationTEMP.seira} --> ΔΙΚΑΙΟΥΧΟΣ ΣΙΤΙΣΗΣ
				</c:if>
				<c:if test="${studentApplicationTEMP.result==-1}">
					ΕΓΚΥΡΗ, Βαθμολογία:${studentApplicationTEMP.vathmos}, Σειρά:${studentApplicationTEMP.seira} --> ΜΗ ΔΙΚΑΙΟΥΧΟΣ ΣΙΤΙΣΗΣ
				</c:if>
			</c:if>
			<c:if test="${studentApplicationTEMP.eggrisi==-1}">ΑΚΥΡΗ</c:if>
			</b>
		</td>
</table>

<b>${pageTitle}</b>
<form:form action="../saveStudentApplication" modelAttribute="studentApplication" method="post">
	<form:hidden path="app_id"/>

	<form:hidden path="eggrisi"/>
	<form:hidden path="vathmos"/>
	<form:hidden path="seira"/>
	<form:hidden path="result"/>
	
	<table>
		<tr>
			<td>Όνομα χρήστη</td>
			<td><form:input path="student.username" readonly="true" style="background-color:#CCFFCC"/></td>
		</tr>
		<tr>
			<td>ΑΜ</td>
			<td><form:input path="student.am" readonly="true" style="background-color:#CCFFCC"/></td>
		</tr>
		<tr>
			<td>Τμήμα</td>
			<td><input type="text" value="${department}" readonly="true" style="background-color:#CCFFCC"/></td>
		</tr>
		<tr>
			<td>Όνομα</td>
			<td><form:input path="student.first_name" readonly="true" style="background-color:#CCFFCC"/></td>
		</tr>
		<tr>
			<td>Επώνυμο</td>
			<td><form:input path="student.last_name" readonly="true" style="background-color:#CCFFCC"/></td>
		</tr>
		<tr>
			<td>Όνομα πατρός</td>
			<td><c:if test="${state==1}">
					<form:input path="onoma_patros" type="text" readonly="true" style="background-color:#CCFFCC"/>
				</c:if>
				<c:if test="${state==0}">
					<form:input path="onoma_patros" type="text" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Όνομα μητρός</td>
			<td><c:if test="${state==1}">
					<form:input path="onoma_mitros" type="text" readonly="true" style="background-color:#CCFFCC"/>
				</c:if>
				<c:if test="${state==0}">
					<form:input path="onoma_mitros" type="text" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Εργάζεται ο πατέρας;</td>
			<td><c:if test="${state==1}">
				<form:select path="pateras_ergazetai" readonly="true" style="background-color:#CCFFCC">
					<form:option value="0" disabled="true" >ΟΧΙ</form:option>
					<form:option value="1" disabled="true" >ΝΑΙ</form:option>
				</form:select>
				</c:if>
				<c:if test="${state==0}">
				<form:select path="pateras_ergazetai">
					<form:option value="0">ΟΧΙ</form:option>
					<form:option value="1">ΝΑΙ</form:option>
				</form:select>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Εργάζεται η μητέρα;</td>
			<td><c:if test="${state==1}">
				<form:select path="mitera_ergazetai" readonly="true" style="background-color:#CCFFCC">
					<form:option value="0" disabled="true" >ΟΧΙ</form:option>
					<form:option value="1" disabled="true" >ΝΑΙ</form:option>
				</form:select>
				</c:if>
				<c:if test="${state==0}">
				<form:select path="mitera_ergazetai">
					<form:option value="0">ΟΧΙ</form:option>
					<form:option value="1">ΝΑΙ</form:option>
				</form:select>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Οικογενειακό εισόδημα</td>
			<td><c:if test="${state==1}">
					<form:input path="oikogeneiako_eisodima" type="number" min="0" readonly="true" style="background-color:#CCFFCC"/>
				</c:if>
				<c:if test="${state==0}">
					<form:input path="oikogeneiako_eisodima" type="number" min="0"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Πόσα αδέλφια σπουδάζουν;</td>
			<td><c:if test="${state==1}">
					<form:input path="adelfia_spoudazoun" type="number" min="0" max="10" readonly="true" style="background-color:#CCFFCC"/>
				</c:if>
				<c:if test="${state==0}">
					<form:input path="adelfia_spoudazoun" type="number" min="0" max="10" />
				</c:if>			
			</td>
		</tr>
		<tr>
			<td>Φοιτείτε στην πόλη σας;</td>
			<td><c:if test="${state==1}">			
					<form:select path="entopiotita" readonly="true" style="background-color:#CCFFCC">
						<form:option value="0" disabled="true">ΟΧΙ</form:option>
						<form:option value="1" disabled="true">ΝΑΙ</form:option>
					</form:select>
				</c:if>
				<c:if test="${state==0}">
					<form:select path="entopiotita" >
						<form:option value="0">ΟΧΙ</form:option>
						<form:option value="1">ΝΑΙ</form:option>
					</form:select>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2"><b>Στοιχεία επικοινωνίας</b></td>
		</tr>
		<tr>
			<td>Διεύθυνση</td>
			<td>
				<sec:authorize access="hasRole('ROLE_STUDENT')==true">
					<form:input path="address_epik" type="text"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STUDENT')==false">
					<form:input path="address_epik" type="text" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr>
		<tr>
			<td>Τηλέφωνο</td>
			<td>
				<sec:authorize access="hasRole('ROLE_STUDENT')==true">
					<form:input path="til_epik" type="text"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STUDENT')==false">
					<form:input path="til_epik" type="text" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>
				<sec:authorize access="hasRole('ROLE_STUDENT')==true">
					<form:input path="email_epik" type="email"/>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_STUDENT')==false">
					<form:input path="email_epik" type="email" readonly="true" style="background-color:#CCFFCC"/>
				</sec:authorize>
			</td>
		</tr>
	</table>
	<sec:authorize access="hasRole('ROLE_STUDENT')==true">
		<button type="submit">Αποθήκευση</button>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_STUDENT')==false">
		<table>
		<tr>
			<td><form:input type="button" value="Εγκρίνεται" path=""
					onclick="window.location.href='../${pageContext.request.remoteUser}/approveApplication/${username}'; return true;"/>
				</form>
			</td>
			<td><form>
				<input type="button" value="Απορρίπτεται" path=""
					onclick="window.location.href='../${pageContext.request.remoteUser}/disapproveApplication/${username}'; return true;"/>
				</form>
			</td>
		</tr>
		</table>
	</sec:authorize>
</form:form>