<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Ad Picker</title>

<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/adpicker.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
</head>




<body id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#page-top">Ad Picker</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="page-scroll"><a href="#page-top">Home</a></li>
				</li>

				<li class="page-scroll"><a href="#abstract">Ads For You</a>
				<li class="page-scroll"><a href="#about">About Us</a></li>
				<li class="page-scroll"><a href="#contact">Contact Us</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="intro-text">

					<span class="name">Start Advertising</span>
				</div>
				<img class="img-responsive" src="img/ad.jpg" alt="">

			</div>
		</div>
	</div>
	</header>

	<!-- abstract Grid Section -->
	<section id="abstract">
	<div class="container">
		<div class="row">
			<div class="col-lg-10 text-center" border="1">
				<h3>Ads For You</h3>
				<hr class="star-primary">
				<form action="AdFinalServlet">
					<table class="table col-xs-6 col-md-4 thumbnail">
						<tr>
							<td>Search :</td>
							<td><input type="text" id="search" name="search" /></td>
							<td><input type="button" id="sbt" name="submit"
								value="Extract data" onclick="fetchSearchData()" /></td>
						</tr>
						<tr>
							<td><input type="hidden" id="searchdata" name="searchdata"  /></td>
							<!-- <td><input type="button" value="GetYouTubedata" /></td> -->
							
						</tr>
						<tr>
							<td colspan="2"></td>
						</tr>
						<tr>				
						</tr>
						<tr>
							<td colspan="2"><ul id="result" style="text-align:left;"></ul></td>
						</tr>
					</table>

					
				</form>

			</div>

			<div class="col-lg-2 text-center" >
				<div id="testInterval" style="height:450px; width:250px ;">
					<img id="imagDefault" src="img/shop.png"  />
				
				</div>
			   
			<!--	<marquee id ="mar" direction="up" height="450" width="250"
					onmouseover="this.stop()" onmouseout="this.start()">
									    
					   	<!--  comment for ajax , its for RequestDispatcher
					      <c:forEach var="element" items="${listData}">        
					      <script type="text/javascript" >
					     	document.getElementById("temp").style.display = 'none';
      						  //$("#temp").hide();
  						  </script>
           	  				<img id="imag" src="${element}"  />    
           	  				           
   						 </c:forEach> 
				</marquee> -->
			</div>

		</div>

	</div>
	</section>

	<!-- About Section -->
	<section class="success" id="about">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>About Us</h2>
				<hr class="star-light">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-lg-offset-2">
				<p>The Ad picker aims at the development of a web site, designed
					to create innovative Artificial Intelligence by giving related ad
					recommendation and search suggestions using advanced Data Mining
					Techniques</p>
			</div>
			<div class="col-lg-4">
				<p>For marketing your new business or launching your new
					product, contact us with your detail!</p>
			</div>

		</div>
	</div>
	</section>

	<!-- Contact Section -->
	<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Contact US</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<form name="sentMessage" id="contactForm" novalidate>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Name</label> <input type="text" class="form-control"
								placeholder="Name" id="name" required
								data-validation-required-message="Please enter your name.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Email Address</label> <input type="email"
								class="form-control" placeholder="Email Address" id="email"
								required
								data-validation-required-message="Please enter your email address.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Phone Number</label> <input type="tel"
								class="form-control" placeholder="Phone Number" id="phone"
								required
								data-validation-required-message="Please enter your phone number.">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="row control-group">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<label>Message</label>
							<textarea rows="5" class="form-control" placeholder="Message"
								id="message" required
								data-validation-required-message="Please enter a message."></textarea>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<br>
					<div id="success"></div>
					<div class="row">
						<div class="form-group col-xs-12">
							<button type="submit" class="btn btn-success btn-lg">Send</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>

	<!-- Footer -->
	<footer class="text-center">
	<div class="footer-above">
		<div class="container">
			<div class="row">
				<div class="footer-col col-md-4">
					<h4> Nilamben Khacharia</h4>
					<h4 style=" text-transform: lowercase;">nilamben.khacharia@gmail.com</h4>
				</div>
				<div class="footer-col col-md-4">
					<h4> Vaishali Wadje</h4>
					<h4 style=" text-transform: lowercase;">wadjevaishali@gmail.com</h4>
				</div>
				<div class="footer-col col-md-4">
					<h4> Nidhi Patel</h4>
					<h4 style=" text-transform: lowercase;">nidhi1280@gmail.com</h4>
				</div>
			</div>
		</div>
	</div>

	<div class="footer-below">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">Copyright &copy; Ad Picker 2015</div>
			</div>
		</div>
	</div>
	</footer>

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-top page-scroll visible-xs visible-sm">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="js/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	 <script src="js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/adpicker.js"></script>
	<script src="js/script.js"></script>

</body>


</html>