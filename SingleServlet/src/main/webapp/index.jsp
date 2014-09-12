<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="./css/jquery.fileupload-ui.css" rel="stylesheet" />
<script type="text/javascript" src="./js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="./js/jquery.fileupload.js"></script>
<script type="text/javascript" src="./js/jquery.fileupload-ui.js"></script>


<body>
	<img alt="Image" src="./images/sports-cars-wallpapers.jpg" width="200"
		height="200">
	<form action="./signUp" method="post">
		<p>
			First Name<input type="text" name="firstName" />
		</p>
		<p>
			EmailId:<input type="text" name="emailId">
		</p>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br> File upload
	<form action="./upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <br /> <input type="submit"
			value="Upload File" />
	</form>
	<br> Jquery File upload
	<form action="./upload" method="post" id="file_upload_1"
		enctype="multipart/form-data">
		<div id="drop_zone_1">
			<div class="fakeupload">
				<input type="text" name="fakeupload1" id="fakeupload1"
					readOnly="readonly" /> <span class="browseBtn">Browse</span> <input
					type="file" name="file_1" id="file_1" size="50"
					onchange="this.form.fakeupload1.value = this.value;" />
			</div>

			<table id="files_1" style="" class="mind"></table>
		</div>
		<div style="display: none">
			<input type="submit" value="Upload" />
		</div>
		<img alt="uploaded image" id="uploadImg_1" src="">
	</form>
	<br> Get json
	<input type="button" value="getJson" onclick="getData()">
	<div id="result"></div>
	<br>
	<h3>Dyna method controller</h3>
	Get Dispatch json
	<input type="button" value="getJson" onclick="getDispatchData()">
	<div id="result1"></div>
	<br> Get Student json
	<input type="button" value="getJson" onclick="getStudentData()">
	<div id="result2"></div>
	<br> StudentSignup
	<form action="./student" method="post">
		<input type="hidden" name="parameter" value="studentSignUP">
		<p>
			First Name<input type="text" name="firstName" />
		</p>
		<p>
			EmailId:<input type="text" name="emailId">
		</p>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<h3>Dyna response controller</h3>
	<c:forEach var="error" items="${errors}">
     ${error.key}  - : ${error.value}
</c:forEach>
	<br> StudentSignup Forward
	<form action="./studentDyna" method="post">
		<input type="hidden" name="parameter" value="studentFroward">
		<p>
			First Name<input type="text" name="firstName" />
		</p>
		<p>
			EmailId:<input type="text" name="emailId">
		</p>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br> StudentSignup Redirect
	<form action="./studentDyna" method="post">
		<input type="hidden" name="parameter" value="studentRedirect">
		<p>
			First Name<input type="text" name="firstName" />
		</p>
		<p>
			EmailId:<input type="text" name="emailId">
		</p>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br> Get Student json
	<input type="button" value="getJson" onclick="getStudentDynaData()">
	<div id="result3"></div>
	
	<br> StudentSignup json validations
	<form action="./studentDyna" method="post">
		<input type="hidden" name="parameter" value="studentRedirectJSONValidate">
		<p>
			First Name<input type="text" name="firstName" />
		</p>
		<p>
			EmailId:<input type="text" name="emailId">
		</p>
		<p>
			<input type="submit" value="submit">
		</p>
	</form>
</body>
<script type="text/javascript">
	function getData() {
		$.getJSON('./json', function(data) {
			alert(data);
			$("#result").html(data.name);
		});
	}
	function getDispatchData() {
		$.getJSON('./mixCont', {
			parameter : 'getJson'
		}, function(data) {
			alert(data);
			$("#result1").html(data.name);
		});
	}
	function getStudentData() {
		$.getJSON('./student', {
			parameter : 'getStudentJson'
		}, function(data) {
			alert(data);
			$("#result2").html(data.name);
		});
	}
	function getStudentDynaData() {
		$.getJSON('./studentDyna', {
			parameter : 'studentJSON'
		}, function(data) {
			if (data.error) {
				alert(data.firstName);
				alert(data.emailId);
			} else {
				alert(data);
				$("#result3").html(data.name);
			}
		});
	}
	var initFileOtpUpload = function(suffix, callback) {
		$('#file_upload_' + suffix)
				.fileUploadUI(
						{
							namespace : 'file_upload_' + suffix,
							fileInputFilter : '#file_' + suffix,
							dropZone : $('#drop_zone_' + suffix),
							uploadTable : $('#files_' + suffix),
							downloadTable : $('#files_' + suffix),
							buildUploadRow : function(files, index) {
								var tableObject = document
										.getElementById('files_' + suffix);
								if (tableObject.rows.length > 0) {
									tableObject.deleteRow(0);
								}
								$('#drop_zone_' + suffix).show();
								return $('<tr><td>'
										+ files[index].name
										+ '<\/td>'
										+ '<td class="file_upload_progress"><div><\/div><\/td>'
										+ '<\/tr>');
							},
							buildDownloadRow : function(file) {
								$('#drop_zone_' + suffix).show();
								if (callback)
									callback(file);
								var tableObject = document
										.getElementById('files_' + suffix);
								$("#uploadImg_" + suffix).attr('src',
										file.filePath);
								$("#uploadImg_" + suffix)
										.attr('alt', file.name);
								if (tableObject.rows.length > 0) {
									tableObject.deleteRow(0);
								}
								return $('');
							}
						});
	};
	initFileOtpUpload(1);
</script>
</html>
