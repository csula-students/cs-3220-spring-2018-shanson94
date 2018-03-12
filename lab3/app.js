class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}

const pubSub = new PubSub();

	pubSub.subscribe(counter =>{
		counter ++;
		window.incrementalGame.state.counter = counter;
		document.getElementById("oreCounter").innerText = window.incrementalGame.state.counter;
	})

document.querySelector('#clicker').addEventListener('click', function(){
	pubSub.publish(window.incrementalGame.state.counter);
});