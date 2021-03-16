<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
    <div class="min-h-screen bg-gray-100 text-gray-800 antialiased px-4 py-6 flex flex-row justify-center sm:py-12">
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
			<span class="text-2xl font-light">Add a document</span>
			<div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-left">
				<div class="h-2 bg-green-600 rounded-t-md"></div>
				<div class="py-6 px-8">
					<form action="." method="GET">
						<label class="block font-semibold">Label<label>
							<input type="text" name="label" required placeholder="Label" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<label class="block mt-3 font-semibold">Description<label>
							<textarea type="text" name="description" placeholder="Description..." class="resize border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md"></textarea>
						<label class="block mt-3 font-semibold">Type<label>
							<select type="text" name="type" required class="resize border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md"></select>
						<label class="block mt-3 font-semibold">Author<label>
							<input type="text" name="author" placeholder="Author" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<label class="block mt-3 font-semibold">Number of pages<label>
							<input type="number" name="pages" min="0" placeholder="Number of pages" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<label class="block mt-3 font-semibold">Director<label>
							<input type="text" name="director" placeholder="Director" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<label class="block mt-3 font-semibold">Release Date<label>
							<input type="number" name="release_date" min="0" placeholder="Year" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<label class="block mt-3 font-semibold">Duration<label>
							<input type="number" name="duration" min="0" placeholder="Minutes" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
						<button class="mt-8 bg-green-700 text-white py-2 px-6 rounded-lg">Add</button>
					</form>
				</div>
        	</div>
			<span class="text-2xl font-light"> <!--Indicates if a new document was created successfully or not-->
				<%
					Boolean docIsCreated = (Boolean) request.getAttribute("docIsCreated");
					if(docIsCreated != null){
						String newDocID = request.getParameter("newDocID");
						String label = request.getParameter("label");
						out.print("Document n°" + newDocID + " '" + label + "' has been created !"); 
					}
            	%>
			</span>
    	</div>
        <div class="relative py-3 sm:max-w-xl mx-auto text-center">
            <span class="text-2xl font-light">Delete a document</span>
            <div class="relative mt-4 bg-white shadow-md sm:rounded-lg text-left">
				<div class="h-2 bg-red-500 rounded-t-md"></div>
				<div class="py-6 px-8">
					<form action="." method="GET">
						<label class="block font-semibold">Document<label>
							<input type="number" name="deleteDocID" min="0" required placeholder="Document ID" class="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-1 focus:ring-indigo-600 rounded-md">
                  		<button class="mt-4 bg-red-600 text-white py-2 px-6 rounded-lg">Delete</button>
					</form>
              	</div>
            </div>
			<span class="text-2xl font-light"> <!--Indicates if the document ID was deleted successfully or not-->
				<%
					Boolean docNotFound = (Boolean) request.getAttribute("docNotFound");
					if(docNotFound != null){
						String docID = request.getParameter("deleteDocID");
						out.print("Document not found for ID : " + docID); 
					}
            	%>
			</span>
        </div>
    </div>
</body>
</html>