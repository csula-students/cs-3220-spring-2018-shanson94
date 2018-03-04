import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Hello custom element',
		counter: 0,
		generators: [
			{
				type: 'autonomous',
				name: 'Pickaxe',
				description: 'It mines ore',
				rate: 5,
				quantity: 0,
				cost: 10,
				unlock: 0
			},

			{
				type: 'autonomous',
				name: 'Goblin',
				description: 'Goblin miner to help mine ore',
				rate: 10,
				quantity: 0,
				cost: 50,
				unlock: 50
			},

			{
				type: 'autonomous',
				name: 'Machine Miner',
				description: 'Machine built by goblins to mine ore',
				rate: 50,
				quantity: 0,
				cost: 500,
				unlock: 500
			}

		],
		story: []
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
