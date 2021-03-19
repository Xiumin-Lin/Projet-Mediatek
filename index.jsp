<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mediatek</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
    <div class="min-h-screen bg-gray-100 text-gray-800 antialiased px-4 py-6 flex flex-col justify-center sm:py-12">
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
          	<span class="text-2xl font-light">Welcome to the Mediatek</span>
          	<div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-center">
            	<div class="h-2 bg-green-600 rounded-t-md"></div>
            	<div class="py-6 px-8"> <!--displays buttons depending on whether the user is logged in or not-->
					<%@ page import = "mediatek2021.Utilisateur" %>
					<%
						Utilisateur user = (Utilisateur) session.getAttribute("user");
						if(session.isNew() || user == null){
							out.print("Please log in to use the Mediatek App v.2021 !");
							out.print("<form action='./login.jsp'>");
							out.print("<button class='mt-4 bg-green-700 text-white py-2 px-6 rounded-lg'>Login</button>");
							out.print("</form>");
						}else if(user != null){
							out.print("Hi " + user.login() + " !");
							Boolean isAdmin = (Boolean) user.data()[3];
							if(isAdmin){
								out.print("<form action='./mediatek.jsp'>");
								out.print("<button class='mt-4 bg-indigo-500 text-white py-2 px-6 rounded-lg'>Manage Mediatek</button>");
								out.print("</form>");
							}
							out.print("<form action='./logoutServlet'>");
							out.print("<button class='mt-4 bg-red-500 text-white py-2 px-6 rounded-lg'>Logout</button>");
							out.print("</form>");
						}
					%>
				</div>
            </div>
		</div>
    </div>
</body>
</html>