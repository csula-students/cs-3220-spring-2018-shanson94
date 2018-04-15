import constants from '../constants';

export default class Generator {
	/**
	 * Create a new generator based on the meta object passing in
	 * @constructor
	 * @param {object} meta - meta object for constructing generator
	 */
	constructor (meta) {
		this.type = meta.type;
		this.name = meta.name;
		this.description = meta.description;
		this.rate = meta.rate;
		this.quantity = meta.quantity;
		this.baseCost = meta.baseCost;
		this.unlockValue = meta.unlockValue;
	}

	/**
	 * getCost computes cost exponentially based on quantity (as formula below)
	 * xt = x0(1 + r)^t
	 * which 
	 * xt is the value of x with t quantity
	 * x0 is base value
	 * r is growth ratio (see constants.growthRatio)
	 * t is the quantity
	 * @return {number} the cost of buying another generator
	 */
	getCost () {
		var rt = Math.pow(1+constants.growthRatio, this.quantity);
		var xt = this.baseCost*(rt);
		var costRound = Math.round(xt*100)/100
		return costRound;
	}

	/**
	 * generate computes how much this type of generator generates -
	 * rate * quantity
	 * @return {number} how much this generator generates
	 */
	generate () {
		var g = this.rate*this.quantity;
		return g;
	}
}
