<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Goblin Mine</title>
	<link rel="stylesheet" href="app.css">
	<script>
		state = ${state}; // State is passed from the Controrller as a JSON string
	    
	    window.defaultState = state; // Set the state in app.bundle.js to be the JSON string passed in
	</script>
</head>
<body>
	<h1>Goblin Mine</h1>

	<game-story-book></game-story-book>

	<div class="container">
		<div class="fill">
			<game-counter></game-counter>
		</div>

		<game-button></game-button>
	</div>

	
	<div class="row">
		<game-generator class="generator" data-id="0"></game-generator>
		<game-generator class="generator" data-id="1"></game-generator>
		<game-generator class="generator" data-id="2"></game-generator>
	</div>



	<script src="dest/app.bundle.js"></script>

</body>
</html>