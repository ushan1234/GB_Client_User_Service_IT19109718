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
var status = validateItemForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "UserAPI", 
type : type, 
data : $("#formItem").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onItemSaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidItemIDSave").val($(this).data("idusers")); 
 $("#fname").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#lname").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#age").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#pnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#password").val($(this).closest("tr").find('td:eq(6)').text());
 $("#category").val($(this).closest("tr").find('td:eq(7)').text());
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UserAPI", 
		 type : "DELETE", 
		 data : "idusers=" + $(this).data("idusers"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// first name
if ($("#fname").val().trim() == "") 
 { 
 return "Insert First Name."; 
 } 
// Last Name
if ($("#lname").val().trim() == "") 
 { 
 return "Insert Last Name."; 
 } 
//AGE-------------------------------
if ($("#age").val().trim() == "") 
 { 
 return "Insert Age."; 
 } 
//Phone-------------------------------
if ($("#pnumber").val().trim() == "") 
 { 
 return "Insert Phone Number."; 
 } //Address-------------------------------
if ($("#address").val().trim() == "") 
{ 
return "Insert Address."; 
} //Email-------------------------------
if ($("#email").val().trim() == "") 
{ 
return "Insert Email."; 
} 
//Password------------------------
if ($("#password").val().trim() == "") 
 { 
 return "Insert Password."; 
 } 
//Category------------------------
if ($("#category").val().trim() == "") 
 { 
 return "Insert Category."; 
 } 
return true; 
}


function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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
$("#hidItemIDSave").val(""); 
$("#formItem")[0].reset(); 
}


function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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
