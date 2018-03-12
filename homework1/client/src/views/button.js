export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
		}

		connectedCallback () {
			console.log('ExampleComponent#onConnectedCallback');
			this.innerHTML = '<button>Mine Ore</button>';
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUTTON_CLICK'
				});
			});
		}

		disconnectedCallback () {
			console.log('ExampleComponent#onDisconnectedCallback');
			this.store.unsubscribe(this.onStateChange);
		}
	};
}