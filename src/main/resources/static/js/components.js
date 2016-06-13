var Config = function(){
	return {
		load_components : function(){
			$('[data-toggle="tooltip"]').tooltip();
			
			$('.datepicker').datepicker({format: 'dd/mm/yyyy',});
			
			 $('.money').mask("#####0.00", {reverse: true});
		}
	}
}();
