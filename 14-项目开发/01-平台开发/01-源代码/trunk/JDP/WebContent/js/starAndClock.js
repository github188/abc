		$(document).ready(function () {
			var newDate = new Date();
			/*newDate.setDate(newDate.getDate());*/

			setInterval( function() {
				var year = newDate.getFullYear();
				var month = newDate.getMonth()+1;
				var day = newDate.getDate();
				var seconds = new Date().getSeconds();
				var minutes = new Date().getMinutes();
				var hours = new Date().getHours();
				$("#year").html(year);
				$("#month").html(( month < 10 ? "0" : "" ) + month);
				$("#day").html(( day < 10 ? "0" : "" ) + day);
				$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
				$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
				$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
				},1000);
		    var stars = 800;
		    var $stars = $('.stars');
		    var r = 800;
		    for (var i = 0; i < stars; i++) {
		        if (window.CP.shouldStopExecution(1)) {
		            break;
		        }
		        var $star = $('<div/>').addClass('star');
		        $stars.append($star);
		    }
		    window.CP.exitedLoop(1);
		    $('.star').each(function () {
		        var cur = $(this);
		        var s = 0.2 + Math.random() * 1;
		        var curR = r + Math.random() * 300;
		        cur.css({
		            transformOrigin: '0 0 ' + curR + 'px',
		            transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360+ 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
		        });
		    });
		});