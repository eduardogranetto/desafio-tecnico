var LembreMe;
$(function(){
	 LembreMe = function(){
		var $panel_backlogs, $panel_annotations;
		var $text;
		var $modal, $modal_title, $modal_body;
		return {
			init: function(){
				
				$text = $('#text');
				$panel_backlogs = $('#panel_backlogs');
				$panel_annotations = $('#panel_annotations');
				$modal = $('#modal');
				$modal_title = $('#modal_title');
				$modal_body = $('#modal_body');
				
				$(document).ajaxComplete(function(){
					Config.load_components();
				});
				
				LembreMe.load();
				return this;
				
			},
			backlog : function(){
				LembreMe.modal({
					title : 'Backlog...',
					html : 'Carregando...',
					load : 'show',
					callback : function(){
						$.get('/backlog/form', {text: $text.val()}).done(function(data){
							LembreMe.modal({
								html : data
							});
						});
					}  
				});
			},
			annotation : function(){
				LembreMe.modal({
					title : 'Anotação...',
					html : 'Carregando...',
					load : 'show',
					callback : function(){
						$.get('/annotation/form', {text: $text.val()}).done(function(data){
							LembreMe.modal({
								html : data
							});
						});
					}
				});
			},
			modal : function(opts){
				if(opts){
					if(opts.title)
						$modal_title.html(opts.title);
					if(opts.load)
						$modal.modal(opts.load);
					if(opts.html)
						$modal_body.html(opts.html);
					if(opts.callback){
						opts.callback();
					}
				}
				return this;
					
				
			},
			message : function(opts){
				if(!opts.type){
					opts.type = 'success';
				}
				$.notify({
					message: opts.message,
				},{
					type: opts.type,
					delay: 3000,
					placement: {
						from: "top",
						align: "center"
					}

				});
				return this;
			},
			load : function(){
				
				$panel_backlogs.html('<p>Carregando...</p>')
				$panel_annotations.html('<p>Carregando...</p>')
				
				$.get('/backlog/home').done(function(data){
					$panel_backlogs.html(data);
				});
				
				$.get('/annotation/home').done(function(data){
					$panel_annotations.html(data);
				});
			}
		}
	}();
	LembreMe.init();
});