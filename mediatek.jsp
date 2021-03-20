<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
	<script src="js/jquery-3.6.0.min.js" type="text/javascript"></script>
	<script src="js/docTypeDynamicFields.js"></script>
</head>
<body>
    <div class="min-h-screen bg-gray-100 text-gray-800 antialiased px-4 py-6 flex flex-row justify-center sm:py-12">
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
			<span class="text-2xl font-light">Add a document</span>
			<div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-left">
				<div class="h-2 bg-green-600 rounded-t-md"></div>
				<div class="py-6 px-8">
					<form action="./addDocServlet" method="GET">
						<label class="block font-semibold">Label<label>
						<input type="text" name="label" required placeholder="Label" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						
						<label class="block mt-3 font-semibold">Description<label>
						<textarea type="text" name="description" placeholder="Description..." class="resize border w-full h-20 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md"></textarea>
						
						<label class="block mt-3 font-semibold">Type<label>
						<select id="docType" type="text" name="type" required class="resize border w-full h-15 px-3 py-2 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
							<option value="">-- Type of the document --</option>
							<option value="1">BOOK</option>
							<option value="2">DVD</option>
							<option value="3">CD</option>
						</select>
						<div class="subBox type_1" hidden>
							<label class="block mt-3 font-semibold">Author<label>
							<input type="text" name="author" placeholder="Author" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						</div>
						<div class="subBox type_1" hidden>
							<label class="block mt-3 font-semibold">Number of pages<label>
							<input type="number" name="pages" min="0" placeholder="Number of pages" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">	
						</div>
						<div class="subBox type_2" hidden>
							<label class="block mt-3 font-semibold">Director<label>
							<input type="text" name="director" placeholder="Director" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						</div>
						<div class="subBox type_2" hidden>
							<label class="block mt-3 font-semibold">Release Date<label>
							<input type="number" name="release_date" min="0" placeholder="Year" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						</div>
						<div class="subBox type_2" hidden>
							<label class="block mt-3 font-semibold">Duration<label>
							<input type="number" name="duration" min="0" placeholder="Minutes" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						</div>
						<div class="subBox type_3" hidden>
							<label class="block mt-3 font-semibold">Artist<label>
							<input type="text" name="artist" placeholder="Director" disabled class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						</div>
						<button class="mt-8 bg-green-700 text-white py-2 px-6 rounded-lg">Add</button>
					</form>
				</div>
        	</div>
			<span class="block mt-3 text-2xl font-light"> <!--Indicates if a new document was created successfully or not-->
				<%
					String createStatus = (String) request.getAttribute("createStatus");
					if(createStatus != null){
						out.print(createStatus); 
					}
            	%>
			</span>
    	</div>
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
            <span class="text-2xl font-light">Delete a document</span>
            <div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-left">
				<div class="h-2 bg-red-500 rounded-t-md"></div>
				<div class="py-6 px-8">
					<form action="./deleteDocServlet" method="GET">
						<label class="block font-semibold">Document<label>
							<input type="number" name="deleteDocID" min="0" required placeholder="Document ID" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
                  		<button class="mt-4 bg-red-600 text-white py-2 px-6 rounded-lg">Delete</button>
					</form>
              	</div>
            </div>
			<span class="block mt-3 text-2xl font-light"> <!--Indicates if the document ID was deleted successfully or not-->
				<%
					String deleteStatus = (String) request.getAttribute("deleteStatus");
					if(deleteStatus != null){
						out.print(deleteStatus);
					}
            	%>
			</span>
        </div>
    </div>
</body>

</html>