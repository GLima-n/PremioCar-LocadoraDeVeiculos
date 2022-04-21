	var senha = $('#form\\:senha');
	var olho= $("#olho");

	olho.mousedown(function() {
	  senha.attr("type", "text")
	});

	olho.mouseup(function() {
	  senha.attr("type", "password");
	  console.log(senha.val());
	});
	
	$("#olho").mouseout(function() { 
	  $("#senha").attr("type", "password");
	});