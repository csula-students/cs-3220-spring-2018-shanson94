export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;

	case 'BUY_GENERATOR':


		for (var i = 0; i < state.generators.length; i++) {

			if (action.payload.name == state.generators[i].name) {
				var rt = Math.pow(1+0.05, state.generators[i].quantity);
				var xt = state.generators[i].baseCost*(rt);
				var cost = Math.round(xt*100)/100
				
				if(state.counter >= cost){
					state.counter = state.counter - cost;
					state.generators[i].quantity++;
					return state;
				}
				else
					return state;
			}
		}

		return state;

	case 'BUTTON_CLICK':
		state.counter++;
		return state;

	default:
		return state;
	}
}

