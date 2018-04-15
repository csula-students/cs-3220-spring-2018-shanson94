export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			this.innerHTML = `Ore: ${this.store.state.counter}`;
		}

		connectedCallback () {
			this.innerHTML = 'Ore: 0';
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
