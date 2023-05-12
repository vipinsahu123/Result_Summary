<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" contant="IE=edage">
        <meta name="viewport" content="width-device-width,intial-scale=1.0">
        <Title>Result Report processing 1.0</Title>
        <link rel="stylesheet" href="css/substyle.css">
		
    </head>
    <body>
        <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
        <header>
           <h2 class="logo">RRP</h2>
           <nav class="navigation">
             <a href="#">Home</a>
             <a href="#">About</a>    
           </nav>
		</header>
		<div class="wrapper" >
			<div class="from-box login">
				<h2>Login</h2>
				<form method="post" action="Login">
					<div class="input-box">
						<span class="icon">
							<ion-icon name="mail"></ion-icon>
						</span>
						<input type="email" name="email" required>
						<label>Email</label>
					</div>
					<div class="input-box">
						<span class="icon">
							<ion-icon name="lock-closed"></ion-icon>
						</span>
						<input type="password" name="pass" required>
                        <label>Password</label>
					</div>
					<div class="remember-forgot">
						<label><input type="checkbox">Remember me</label>
                        <a href="#">Forgot Password?</a>
					</div>
					<button type="submit" class="btn"> Login </button>
					<div class="login-register">
						<p> Don't have an account <a href="#" class="register-link">Register</a></p>
					</div>
				</form>
			</div>


			<div class="from-box register">
				<h2>Registeration</h2>
				<form method="post" action="Register">
					<div class="input-box">
						<span class="icon">
							<ion-icon name="person-outline"></ion-icon>
						</span>
						<input type="text" required name="name">
						<label>Username</label>
					</div>
					<div class="input-box">
						<span class="icon">
							<ion-icon name="mail"></ion-icon>
						</span>
						<input type="email" required name="email">
						<label>Email</label>
					</div>
					<div class="input-box">
						<span class="icon">
							<ion-icon name="lock-closed"></ion-icon>
						</span>
						<input type="password" required name="pass">
						<label>Password</label>
					</div>
					<div class="remember-forgot">
						<label><input type="checkbox">
							I agree to the terms & conditions</label>     
					</div>
					<button type="submit" class="btn"> Register</button>
					<div class="login-register">
						<p> Already have an account <a  href="#" class="login-link">login</a></p>
					</div>
				</form>
			</div>
		</div>
		<script src="js/subscript.js"></script>
		<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<link rel="stylesheet" href="alert/dist/sweetalert.css">
		<script type="text/javascript">
			var status = document.getElementById("status").value;
			if(status == "success"){
				swal("congrats","Account Created Successfully","success");
			}
			else if(status == "failed"){
				swal("Sorry","Wrong Email or Password","error");
			}
			else if(status == "exception"){
				swal("Sorry","Email is invalid or already taken","error");
			}
		</script>
		
    </body>
</html>