<%
	if(session.getAttribute("user_name")==null)
	{
		response.sendRedirect("index.jsp");
	}
%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Bootstrap demo</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<link href="css/custom.css" rel="stylesheet">
		<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
	</head>
	<body>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
		<div class="main-screen page-content-wrapper ">
			
				<div class="container-fluid  ">
					<form method="post" enctype="multipart/form-data" action="FileUploadServlet">
					<div class="row g-5 mt-5 mx-5 justify-content-md-center">
					
						
						<div class="col-md-4 mx-2 box ">
							<h2 class="text-center p-2">Upload PDF File</h2>
							<input type="file" name="file" id="upload" accept=".pdf" required hidden>
							<label for="upload" class="uploadlabel">
								<span><i class="fa fa-cloud-upload"></i></span>
								<p>Click To Upload</p>
							</label>  		
							<div id="filewrapper">
								<h6>uploaded Documents</h6>	
							</div>
						</div>
						<div class="col-md-4 mx-2 box" >
							
							<h2 class="text-center p-2">Upload Text File</h2>
							<input type="file" name="file" id="upload1" accept=".txt, .xls, .xlsx" required hidden>
							<label for="upload1" class="uploadlabel">
								<span><i class="fa fa-cloud-upload"></i></span>
								<p>Click To Upload</p>
							</label>  
							<div id="filewrapper1">
								<h6>uploaded Documents</h6>
							</div>
						</div>
						<button type="submit" value="Submit" class="col-md-4 btn btn-primary btn-lg">Upload</button>
					</div>
					</form>
					<div class="row g-5 mt-2 mx-5 justify-content-md-center">	
						<a href="hometable.jsp" class="col-md-4 btn btn-primary btn-lg">View Previous Result</a>
					</div>
				</div>
		</div>
<script>
    const button = document.querySelector(".button"),
    text = document.querySelector(".text");

    button.addEventListener("click", ()=>{
      button.classList.add("progress");
      text.innerText = "Uploading...";

      setTimeout(()=>{
       button.classList.remove("progress"); //remove progress after 6s
       text.innerText = "Uploaded";
      },6000); //1000ms = 1s (6000 = 6s)

    });
</script>
<script src="js/Sscript.js"></script>

  </body>
</html>