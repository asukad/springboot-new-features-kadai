const stripe = Stripe('pk_test_51PZr0JLJ2LcWpnob4luZBrJD98cHEpLTAAelVVkNEnMIh6ccM0AWvTYjZYQdqaGB4NDX6K49ol4HynL66ijeO91p00UClyBd21');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
	stripe.redirectToCheckout({
		sessionId: sessionId
	})
});