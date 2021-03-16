<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
    <div class="min-h-screen bg-gray-100 text-gray-800 antialiased px-4 py-6 flex flex-col justify-center sm:py-12">
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
          	<span class="text-2xl font-light">Login to your account</span>
          	<div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-left">
            	<div class="h-2 bg-green-600 rounded-t-md"></div>
            	<div class="py-6 px-8">
            		<form action="./loginServlet" method="POST">
                		<label class="block font-semibold">Username or Email<label>
                			<input type="text" name="login" required placeholder="Email" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
                		<label class="block mt-3 font-semibold">Password<label>
                			<input type="password" name="password" required placeholder="Password" class=" border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
                		<button class="mt-4 bg-green-700 text-white py-2 px-6 rounded-lg">Login</button>
					</form>
				</div>
            </div>
			<span class="text-2xl font-light"> <!--Display a message if user is invalid-->
				<%
					Boolean userNotFound = (Boolean) request.getAttribute("userNotFound");
					if(userNotFound != null){
						String login = request.getParameter("login");
						out.print("User not found or wrong password for : " + login); 
					}
            	%>
			</span>
		</div>
    </div>
</body>
</html>