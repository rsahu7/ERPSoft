$(function() {

	    $("#email").focusout(function(){
		    	var data = {}
				var email = $("#email").val();
		    	$("#save").prop("disabled", true);
		    	if( !email ) {
		    		 $( "#errmsg" ).text("Enter email-id .");
		    		 $( "#email" ).focus();
		    		 return ;
		    	}else{
		    		var serverurl = "";
		    		if (window.location.href.indexOf("edit") >= 0){
			    		url=window.location.href.split("edit", 2);
			    		serverurl=url[0];
			    	}
		    		var chekEmail="checkEmail?email="+email;
		    		$.ajax({
			             contentType: "application/json",
			             url: serverurl + chekEmail,
			             dataType: 'json',
			             timeout: 600000,
			             success: function (data) {
			            	 if(data){
			            		$("#save").prop("disabled", true);
			     		    	$("#email").css("background-color", "yellow");
			     		    	$( "#errmsg" ).text("Email-Id already exists, enter new ID.");
			            	 }else{
			            		 $("#email").css("background-color", "white");
			            		 $("#save").prop("disabled", false);
			            		 $( "#errmsg" ).text("");
			            	 }

			             },
			             error: function (e) {
			            	 alert("Email-Id already exists, enter new ID.");
			             }
				});
		    }

		});




		$("#birthdate")
				.datepicker(
						{
							onSelect : function(value, ui) {
								var today = new Date(),
									dob = new Date(value),
									age = new Date(today - dob).getFullYear() - 1970;
								document.getElementById('age').value = age;
							},
							changeMonth : true,
							changeYear : true,
							yearRange : "-80:+0",
							maxDate: 0
						});

		$('.datepick').each(function() {
			$(this).datepicker({
				dateFormat: "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
				minDate: 0
			});
		});


		$('.interview').each(function() {
			$(this).validate({
				rules : {
					companyName : {
						required : true,
						minlength : 2,
						maxlength : 15,
						alpha: true
					},
					position : {
						required : true,
						minlength : 2,
						maxlength : 25,
						alpha: true
					},
					department : {
						required : true,
						minlength : 2,
						maxlength : 25,
						alpha: true
					},
					interviewDate : {
						required : true,
						date: true
					},
					status : {
						required : true,
						minlength : 2,
						maxlength : 15,
						alpha: true
					},
					remark : {
						alpha: true
					}
				},
				messages : {
					companyName : {
						required : "Please enter a Company Name",
						minlength : "Name must consist of at least 2 characters",
						maxlength : "Name not more than 15 characters"
					}
				}
			});
		});


		if(window.location.hash) {
			//var hash = window.location.hash.substring(1);
			//Puts hash in variable, and removes the # character
			 $("#Interview").trigger('click');
			 $("#savebuttons").hide();
			}



		$("#empdetailForm").validate({
			ignore: "",
			rules : {
				name :  {
					required : true,
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				birthdate  : {
					required : true,
					date : true
				},
				age              : {
					required : true,
					number : true
				},
				lastname         : {
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				middlename       : {
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				gender           : {
					required : true
				},
				spousename       : {
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				education        : {
					required : true,
					minlength : 2,
					maxlength : 15
				},
				address          : {
					required : true,
					minlength : 5,
					maxlength : 150
				},
				city             : {
					required : true,
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				state            : {
					required : true,
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				email            : {
					required : true,
					minlength : 5,
					maxlength : 50,
					email: true
				},
				contact1         : {
					required : true,
					minlength : 8,
					maxlength : 13,
					number : true
				},
				contact2         : {
					minlength : 8,
					maxlength : 13,
					number : true
				},
				exprience        : {
					minlength : 1,
					maxlength : 5,
					number: true,
	                dollarsscents: true,
					Decimal: true
				},
				designation      : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				department       : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				industry         : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				salary           : {
					minlength : 1,
					maxlength : 6,
					number: true,
	                dollarsscents: true,
					Decimal: true
				},
				prefworkinterest : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				prefworkloc      : {
					minlength : 2,
					maxlength : 30,
					alpha: true
				},
				expectedsal      : {
					minlength : 1,
					maxlength : 5,
	                dollarsscents: true,
					Decimal: true
				},
				worknature       : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				areaofwork       : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				}
			},
			messages : {
				name : {
					required : "Please enter a username",
					minlength : "Name must consist of at least 2 characters",
					maxlength : "Name not more than 15 characters"
				},
				birthdate :{
					required : "Please enter Birthdate"
				},
				age              : {
					required : "Please enter age"
				},
				lastname         : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				middlename       : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				gender           : {
					required : "Select a gender"
				},
				spousename       : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				education        : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				address          : {
					required : "Please enter some value",
					minlength : "Minimum 5 char required",
					maxlength : "Maximum 150 char allowed"
				},
				city             : {
					required : "Please enter some value",
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				state            : {
					required : "Please enter some value",
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 15 char allowed"
				},
				email            : {
					required : "Please enter some value",
					minlength : "Minimum 5 char required",
					maxlength : "Maximum 30 char allowed",
					email 	: "Please enter a valid email address"
				},
				contact1         : {
					required : "Please enter some value",
					minlength : "Minimum 8 char required",
					maxlength : "Maximum 13 char allowed"
				},
				contact2         : {
					minlength : "Minimum 8 char required",
					maxlength : "Maximum 13 char allowed"
				},
				exprience        : {
					minlength : "Minimum 1 char required",
					maxlength : "Maximum 5 char allowed"
				},
				designation      : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				},
				department       : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				},
				industry         : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				},
				salary           : {
					minlength : "Minimum 1 char required",
					maxlength : "Maximum 6 char allowed"
				},
				prefworkinterest : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				},
				prefworkloc      : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 30 char allowed"
				},
				expectedsal      : {
					minlength : "Minimum 1 char required",
					maxlength : "Maximum 5 char allowed"
				},
				worknature       : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				},
				areaofwork       : {
					minlength : "Minimum 2 char required",
					maxlength : "Maximum 50 char allowed"
				}
			},
			invalidHandler: function(form, validator) {
			    var formId = validator.errorList[0].element.closest('div').id;
			    alert("Please fill correct data in tab  " + formId);
			   /* var tab = $('[href="#' + formId + '"]');
			    $('#'+formId).show();
			    $('#'+formId+'.tablinks').css('color','red');*/
			}
		});

	});

function openCity(evt, cityName) {
	// Declare all variables
	var i, tabcontent, tablinks;
	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	// Show the current tab, and add an "active" class to the link that opened the tab
	document.getElementById(cityName).style.display = "block";
	if(cityName == 'job' || cityName == 'attachment' ){
		$("#savebuttons").hide();
	}else{
		$("#savebuttons").show();
	}
	evt.currentTarget.className += " active";
}

function uploadFormData(empId,type)
{
	var myForm = new FormData();
	var currfile = file[1];
	if (type == "profile")
		currfile = file[0];

	if(currfile.files[0] === undefined && type == 'profile'){
		$('#profresult').html('Please select a file.');
		return false;
	}

	if(currfile.files[0] === undefined  && type == 'attach'){
		$('#result').html('Please select a file.');
		return false;
	}

	var allowed_extensions = new Array("jpg","png");
	var file_extension = currfile.files[0].name.split('.').pop();
	if (type == 'profile' && file_extension != 'jpg' && file_extension != 'png')
	{
		$('#profresult').html('Please upload only JPG or PNG file.');
		return false;
	}
	var filelen = currfile.files.length;
	for (var i = 0; i < filelen; i++)
	{
		myForm.append("file",currfile.files[i]);
	}
	myForm.append("empId",empId);
	myForm.append("type",type);
	$.ajax({
		url : "uploadfile",
		data : myForm,
		processData: false,
	    contentType: false,
	    type : "POST",
		success : function(data)
		{
			if(type != 'profile')
			{
				var responsedata = data.split('@@@');
				$('#result').html(responsedata[0]);
				listDocData(data,empId);
			}
			else
			{
				window.location.reload();
			}
		}
	});
}

function deletefile(test, empId)
{
	var myForm = new FormData();
	myForm.append("filename",test);
	myForm.append("empId",empId);
	$.ajax({
		url : "deletefile",
		data : myForm,
		processData: false,
	    contentType: false,
	    type : "POST",
		success : function(data)
		{
			var responsedata = data.split('@@@');
			$('#result').html(responsedata[0]);
			listDocData(data,empId);
		}
	});
}

function listDocData(data,empId)
{
	var attachData = data.split('@@@');
	var table = document.createElement('table');//document.getElementById("attachmentList");
	table.setAttribute('id','attachmentList');
	var row = table.insertRow(0);
	var head1 = row.insertCell(0);
	var head2 = row.insertCell(1);
	var head3 = row.insertCell(2);
	//var head4 = row.insertCell(3);

	head1.innerHTML = "Sr.No.";
	head2.innerHTML = "Name";
	head3.innerHTML = "Delete";
	//var rowCount = $('#attachmentList tr').length;
	for (var i=1;i<attachData.length;i++)
	{
		if (attachData[i] == "")
			continue;
		//var fileData = attachData[i].split('!!!');
		var row = table.insertRow(i);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);

		var deletebutton = document.createElement("input");
		deletebutton.type = "BUTTON";
		deletebutton.name = attachData[i];
		deletebutton.value = "Delete";//attachData[i];
		deletebutton.setAttribute('onclick','deletefile("'+attachData[i]+'","'+empId+'")');

		cell1.innerHTML = i;
		cell2.innerHTML = "<a class='downloadlink' href='downloadfile/"+empId+"/"+attachData[i]+"/'>"+attachData[i]+"</a>";
		cell3.appendChild(deletebutton);

	}
	var attachmentdiv = document.getElementById("attachmentListDiv");
	attachmentdiv.innerHTML = "";
	attachmentdiv.appendChild(table);
}