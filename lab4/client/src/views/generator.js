export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.connectedCallBack();
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			this.innerHTML = `<div>
				<div class="center">${this.store.state.generators[this.dataset.id].name}</div>
				<div class="center">Quantity: ${this.store.state.generators[this.dataset.id].quantity}</div>

				<p class="center">${this.store.state.generators[this.dataset.id].description}</p>
				<p class="center">Rate: ${this.store.state.generators[this.dataset.id].rate}</p>
				<button class="center">Buy ${this.store.state.generators[this.dataset.id].name}</button>
			</div>`;

			this.querySelector('button').addEventListener('click', () => {
				this.store.dispatch({
					type:'BUY_GENERATOR',
					payload: {
						name: this.store.state.generators[this.dataset.id].name,
					}
				});
			});
		}

		connectedCallBack () {
			this.innerHTML = `<div>
				<div class="center">${this.store.state.generators[this.dataset.id].name}</div>
				<div class="center">Quantity: ${this.store.state.generators[this.dataset.id].quantity}</div>

				<p class="center">${this.store.state.generators[this.dataset.id].description}</p>
				<p class="center">Rate: ${this.store.state.generators[this.dataset.id].rate}</p>
				<button class="center">Buy ${this.store.state.generators[this.dataset.id].name}</button>
			</div>`;

			this.querySelector('button').addEventListener('click', () => {
				this.store.dispatch({
					type:'BUY_GENERATOR',
					payload: {
						name: this.store.state.generators[this.dataset.id].name,
					}
				});
			});
			this.store.subscribe(this.onStateChange);

		}

		disconnectedCallback () {
			
			this.store.unsubscribe(this.onStateChange);
		}

	};
}
