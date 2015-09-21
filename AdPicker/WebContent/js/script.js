var input = ' ';
function fetchSearchData() {

	var search = document.getElementById("search").value;
	$.get("https://www.googleapis.com/youtube/v3/search", {
		part : 'snippet',
		order : 'viewCount',
		q : search,
		statistics : true,
		type : 'video',
		froMine : true,
		maxResults : 10,
		videoDefinition : 'high',
		key : 'AIzaSyAKRqRiIARFPkuJveoNLGCmZtABp2g413k'
	}, function(data) {
		var output;
		$.each(data.items, function(i, item) {
			console.log(item);
			vTitle = item.snippet.title;
			output = '<li>' + vTitle + '</li>';
			input += vTitle + "###";
			// append to html
			$('#result').append(output);

			// append to input.json
		});

		$.ajax({
			url : 'AdFinalServlet',
			type : 'GET',
			dataType : 'json',
			data : {
				search : input
			},
			success : function(rs) {

				var img = $('<img id="dynamic1">'); // Equivalent:
				// $(document.createElement('img'))
				img.attr('src', rs.one);
				img.appendTo('#testInterval'); // for marquee put marquee id for append
				var img1 = $('<img id="dynamic2">');
				img1.attr('src', rs.two);
				img1.appendTo('#testInterval');
				var img2 = $('<img id="dynamic3">');
				img2.attr('src', rs.three);
				img2.appendTo('#testInterval');
				$('#temp').hide();
			}
		});
	});
}
