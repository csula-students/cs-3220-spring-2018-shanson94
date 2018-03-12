import Story from '../models/story';

export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: initial DOM rendering of story itself

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"

			this.innerHTML = `<div class = "story"><h1>Story</h1></br></div>`;
			var storyPar = window.document.createElement('p');
			for (var i = 0; i < store.state.story.length; i++){
			const story = new Story(store.state.story[i]);
				if(story.state == "visible"){
					var storyText = window.document.createTextNode(story.description);
				}
			storyPar.appendChild(storyText);
			const br = window.document.createElement('br');
			storyPar.appendChild(br);	
		}
			this.appendChild(storyPar);
			
		}

		connectedCallback () {
			this.innerHTML = `<div class = "story"><h1>Story</h1></br></div>`;
			var storyPar = window.document.createElement('div');
			for (var i = 0; i < store.state.story.length; i++){
			const story = new Story(store.state.story[i]);
				if(story.state == "visible"){
					var storyText = window.document.createElement('div');
					storyText.innerHTML = story.description;
				}
			storyPar.appendChild(storyText);
			const br = window.document.createElement('br');
			storyPar.appendChild(br);	
		}
			this.appendChild(storyPar);

			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {for (var i = 0; i < state.story.length; i++){
			const story = new Story(state.story[i]);
				if(story.isUnlockYet(state.counter) == true){
					story.unlock();
					state.story[i] = story;
				}
		}
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

