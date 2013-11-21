<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="giftkart.js"></script>    
    <link rel="stylesheet" type="text/css" href="style.css" media="screen" />
    <title>GiftKart</title>
  </head>

  <body>
  <script>
	  window.onload = function()
	  {
	     var show = '${showRecom}';
	     if(show == 'true'){
	    	 var items = [];
	    	 <c:forEach var="element" items="${listData}">
	    	 	items.push("${element}");
	    	 </c:forEach>
	    	 showRecc(items);
	     }
	  };
  </script>
    <header>
    	<h1>GiftKart</h1>
    </header>
	
   
<div id="fb-root"></div>

<div id="main" style="display:none">
	<div id="leftcolumn">
			<img id="pic" src=""></img>
	</div>
		
	<div id="rightcolumn">
		<div id="name"></div>
		<div id="u_id"></div>
		<div id="email"></div>

	</div>
	<div class="clear">

		<div id="friendsList">
			<ul>
				<li><strong>Friends</strong></li>
					<ul class="friends">	</ul>
			</ul>
		</div>	
		<div id="recommend" style="display:none">
			<ul>
				<li><strong>Gifts</strong></li>
					<ul class="gifts">	</ul>
			</ul>
		</div>
		</div>	
	</div>
</div>
	
<div id="butts">
	<img id='enroll' src="enroll_now.jpg" style="cursor:pointer;display:none;width:180px;height:100px" onclick="getPerm()"/>
</div>

</body>
</html>