<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<br/>
<openmrs:require privilege="Manage Radiology" otherwise="/login.htm" redirect="/module/radiology/listDepartment.form" />
<%@ include file="../localHeader.jsp" %>
<script type="text/javascript">
	jQuery(document).ready(function() 
		{ 
			jQuery("#myTable").tablesorter({sortList: [[0,0]]}); 
		} 
	); 
</script>
<a href="editDepartment.form">Add new department</a>
<table id="myTable" class="tablesorter">
	<thead>
		<tr> 
			<th>No</th>
			<th>Name</th>
			<th>Description</th>
			<th>Role</th>
			<th width="100px;"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="department" items="${departments}" varStatus="index">
			<c:choose>
				<c:when test="${index.count mod 2 == 0}">
					<c:set var="klass" value="odd"/>
				</c:when>					
				<c:otherwise>
					<c:set var="klass" value="even"/>
				</c:otherwise>
			</c:choose>
			<tr class="${klass}">
				<td>${index.count}</td>
				<td>
					<a href="editDepartment.form?id=${department.id}">${department.name}</a>
				</td>
				<td>
					${department.description}
				</td>
				<td>
					${department.role.role}
				</td>
				<td>
					<center>
						<a href="deleteDepartment.form?id=${department.id}">
							<img border="0" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/delete.gif"/>
						</a>
					</center>
				</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %>  