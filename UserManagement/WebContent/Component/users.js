$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateUserForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hiduIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "UserAPI", 
		 type : type, 
		 data : $("#formUser").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hiduIdSave").val($(this).data("uid"));
	$("#firstName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lastName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#gendedr").val($(this).closest("tr").find('td:eq(3)').text());
	$("#occupation").val($(this).closest("tr").find('td:eq(4)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(5)').text());
	$("#username").val($(this).closest("tr").find('td:eq(6)').text());
	$("#password").val($(this).closest("tr").find('td:eq(7)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "UserAPI", 
	 type : "DELETE", 
	 data : "uId=" + $(this).data("uid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onUserDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateUserForm()
	{
	// First Name
	if ($("#firstName").val().trim() == "")
	{
	return "Insert First Name.";
	}
	// Last Name
	if ($("#lastName").val().trim() == "")
	{
	return "Insert Last Name.";
	}

	// Email-------------------------------
	if ($("#email").val().trim() == "")
	{
	return "Insert Email.";
	}
	
	// Gender-------------------------------
	if ($("#gender").val().trim() == "")
	{
	return "Insert Gender.";
	}
	
	// Occupation-------------------------------
	if ($("#occupation").val().trim() == "")
	{
	return "Insert Occupation.";
	}
	
	// Phone-------------------------------
	if ($("#phone").val().trim() == "")
	{
	return "Insert Phone Number.";
	}
	// is numerical value
	var tmpPhone = $("#phone").val().trim();
	if (!$.isNumeric(tmpPhone))
	{
	return "Phone Number cannot contain letters.";
	}
	
	// Username------------------------
	if ($("#username").val().trim() == "")
	{
	return "Insert Username.";
	}
		
	//Password
	if ($("#password").val().trim() == "")
	{
	return "Insert Password.";
	}
	return true;
}

function onUserSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hiduIdSave").val(""); 
	 $("#formUser")[0].reset(); 
}

function onUserDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}