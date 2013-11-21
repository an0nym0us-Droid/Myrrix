
			var myform;
			var fromGetPerm=false;
 			 window.fbAsyncInit = function() {
				    FB.init({
				      appId      : '547881965266446', // Set YOUR APP ID
				      channelUrl : 'http://bvpgiftkart.appspot.com/channel.html', // Channel File
				      status     : true, // check login status
				      cookie     : true, // enable cookies to allow the server to access the session
				      xfbml      : true  // parse XFBML
				    });
 					fromGetPerm=false;
				    FB.Event.subscribe('auth.authResponseChange', function(response) {
				     if (response.status === 'connected'){
						
				        getUserInfo();
				     }    
				     else if (response.status === 'not_authorized'){
				     }else{
				    	 
				     }
				    }); 
				    FB.getLoginStatus(function(response) {
				    	  if (response.status === 'connected') {
							  fromGetPerm = true;
//							  getUserInfo();
				    	  } else if (response.status === 'not_authorized') {
//				    	    	showEnroll(true);
//				    	    	document.getElementById("sal").innerHTML="";
				    		    getPerm();
				    	  } else {
				    	    	getPerm();
				    	  }
				    });
    		};
		    
		    function getPerm(){
		    	FB.login(function(response) {
			           if (response.authResponse){
			               // getUserInfo();
			                var login = document.getElementById("enroll");
					        login.style.display="none";
			           } else{
			             console.log('User cancelled login or did not fully authorize.');
			           }
			    },{scope: 'email,user_about_me,user_activities,user_birthday, user_events, user_friends, user_groups, user_interests, user_likes, user_location, user_photos, user_subscriptions, friends_likes, friends_interests'});
		    	fromGetPerm=true;
		    }

		    function readUser() {
		    	
		    }

			function getData(id){
				var dataCSV = id + "";
				var queryURL = '/'+id+'/likes?limit=2000';
				// console.log(queryURL);
				FB.api(queryURL, function(response) {
					// console.log(JSON.stringify(response.data));
					var likes = response.data;
					$.each(likes, function() {
						if(this.category === "Movie" || this.category === "Tv show")
						dataCSV += "," + this.id;
					});
					console.log(dataCSV);
					if(dataCSV == id) {
						alert("No data found!");
//						fromGetPerm = false;
					}
					
					else if(fromGetPerm)
						makeForm(dataCSV);
					//showRecc(dataCSV);
				});
			}

			function showRecc(items) {
				$('#friendsList').hide();
				$('#recommend').show();
				
				$.each(items, function(index,val) {
					 $('.gifts').append("<li>"+items[index]+"</li>");
				});
				$('#recommend').append("<a href='#' id=backButton onclick='goBack();'>back</a>");
			}
			function goBack(){
				self.location = "/Myrrix";
			}
	  		function getUserInfo() {
			        FB.api('/me', function(response) {
			        	$('#name').append(response.name + " ("+response.username+")");
			        	// $('#u_id').append(response.id);
			        	// $('#email').append(response.email);
			        	$('#friendsList > ul').prepend("<li><a href='#' onclick='getData("+response.id+"); return false;'>For me</a></li>");

			        });

			        FB.api('/me/friends?limit=10', function(response) {
			        	$.each(response.data, function() {
			        		$('.friends').append("<li><a href='#' onclick='getData(" + this.id + ");return false;'>" + this.name + "</a></li>");
			        	});
			        });

			
			        
		      //   	FB.api('/me/likes?limit=2000', function(response) {
			     //  	  $.each(response.data, function() {
			     //  	  	$('.likes').append("<li><a href='#''>" + this.name + "</a></li>");
			     //  	  });
			     //      if(fromGetPerm){
				    //       makeForm(response.id);
			     //     }
		    		// });
			  

			    	// FB.api('/me/interests?limit=20', function(response) {
				    //   	  $.each(response.data, function() {
				    //   	  	$('.interests').append("<li>" + this.name + "</li>");
				    //   	  });
			    	// });
			      
			        FB.api('me?fields=picture.type(normal)', function(response) {
			            $('#pic').attr('src',response.picture.data.url);
			      	});
			      	$('#main').show();
	    	}
  		
  		function showEnroll(flag){
  			var enroll = document.getElementById("enroll");
  			if(flag)
	        	enroll.style.display="block";
  			else
  		        enroll.style.display="none";
  		}
   
		  // Load the SDK asynchronously
		  (function(d){
		     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
		     if (d.getElementById(id)) {return;}
		     js = d.createElement('script'); js.id = id; js.async = true;
		     js.src = "//connect.facebook.net/en_US/all.js";
		     ref.parentNode.insertBefore(js, ref);
		   }(document));
 
		  function makeForm(id){
			  myform=document.createElement("form");
			  myform.setAttribute("id", "myform");
			  myform.setAttribute('action', "/Myrrix/helloworld");
			  myform.setAttribute('method', "post");
			  
			  var tname=document.createElement('input');
			  tname.setAttribute('type','text');
			  tname.setAttribute('name', "data");
			  tname.setAttribute('value',id);
			  myform.appendChild(tname);
			  document.body.appendChild(myform);
			  $('#myform').hide();
			  document.forms["myform"].submit();
			  
			  
		  }
