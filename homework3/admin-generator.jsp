<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Goblin Mine</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Goblin Mine</h1>

	<nav>
		<a href="admin-info.html">Info</a> |
  		<a href="admin-generators.html">Generators</a> |
  		<a href="admin-events.html">Elements</a>
  	</nav>

  	<div class="container">

	<div class="fill">
	<form method="post">
		<label for="name">Generator Name</label>
		<input type="text" id="name"><br>

		<label for="name">Generator Rate</label>
		<input type="text" id="name"><br>

		<label for="name">Base Cost</label>
		<input type="text" id="name"><br>

		<label for="name">Unlock At</label>
		<input type="text" id="name"><br>

		<label for="name">Description</label>
		<input type="text" id="name"><br>

		<input type="submit" value="Submit"><br>
	</form>
	</div>



	<table>
		<tr>
			<th>Name</th>
			<th>Rate</th>
			<th>Cost</th>
			<th>Unlock At</th>
			<th>Description</th>
		</tr>
		
		<c:forEach items="${generatorEntries}" var="generator">
		<tr>
			<td>${generator.getName()}</td>
			<td>${generator.getRate()}</td>
			<td>${generator.getBaseCost()}</td>
			<td>${generator.getUnlockAt()}</td>
			<td>${generator.getDescription()}</td>
			<td>
				<a href='EditGeneratorServlet?id=${generator.getId()}'>Edit</a> | <a href='DeleteGeneratorServlet?id=${generator.getId()}'>Delete</a>
			</td>
		</tr>
		</c:forEach>

	</table>



</body>
</html>