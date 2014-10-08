/**
 * 留言板
 */
$(document).ready(function() {
	$("#messagePostBtn").bind("click", message.send);
});

var message = {
		send : function(){
			var text = $(this).prev().val();
			var ownerId = $("#messageDashOwner").val();

			$.ajax({
				url : sendMessage,
				type : "post",
				dataType: 'json',
		        data: {text: text, ownerId: ownerId},
				success : function(r) {
					
				},
				error: function(e){
					alert("error occured! Status:" + e.status  
	                        + ' --Status Text:' + e.statusText 
	                        + " --Error Result:" + e); 
				}
			});
		}
};