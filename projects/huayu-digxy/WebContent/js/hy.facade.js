if ("undefined" == typeof(hyFacade )) {
	var jq = $; // JQuery alias define.
	var hyFacade = {
			folding : function( id , foldingWrapperId){
				jq("#"+id).removeClass("ic-arrow-down").addClass("ic-arrow-up").click(function(){
					jq("#"+foldingWrapperId).hide();
					jq("#"+id).removeClass("ic-arrow-up").addClass("ic-arrow-down").click(function(){
						jq("#"+foldingWrapperId).show();
						hyFacade.folding(id , foldingWrapperId);
					});
				});
				
			}
		
	};
};
