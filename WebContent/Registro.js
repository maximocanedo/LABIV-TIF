
async function cargarProvincias() {
	$.ajax({
	      type: "GET",
	      data: fetch("/api/provinces/list"),
	      contentType: "application/json; charset=utf-8",
	      dataType: "json",
	      success: function (data) {
	         $("#provincia")
	         .empty()
	         .append($("<option></option>")
	         .val("0")
	         .html(":.Seleccione.:"));
	          
	         $.each(data.d, function (key, value) {
	            var option = $(document.createElement('option'));
	            option.html(value.id);
	            option.val(value.nombre);
	            $("#provincia")
	            .append(option);
	         });
	      }
	   });
}
 
  
