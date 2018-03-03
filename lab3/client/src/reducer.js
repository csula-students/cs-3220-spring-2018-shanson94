export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;

	case 'BUY_GENERATOR':

		if(data-id==="0"){
			state.type = 'GENERATOR';
			state.name = 'Pickaxe';
			state.baseCost = state.getCost();
			state.quantity++;
			state.counter = state.counter - state.baseCost;
			window.state.counter = state.counter;
			state.unlockValue = state.baseCost; 
		}
		return state;

	default:
		return state;
	}
}

