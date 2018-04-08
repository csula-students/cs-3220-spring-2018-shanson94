import Generator from './models/generator.js';

// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	// TODO: increment counter based on the generators in the state
	// hint: read how many "generators" in store and iterate through them to
	//       count how many value to increment to "resource"
	// hint: remember to change event through `store.dispatch`
	var globalRate = 0;
	for (var i = 0; i < store.state.generators.length; i++) {
		const generator = new Generator(store.state.generators[i]);
		globalRate = globalRate + generator.generate();
	}

	store.dispatch({
		type: 'INCREMENT',
		payload: globalRate
	});



	// TODO: triggers stories from story to display state if they are passed
	//       the `triggeredAt` points
	// hint: use store.dispatch to send event for changing events state
	store.dispatch({
		type: 'CHECK_STORY',
	});

	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
}
