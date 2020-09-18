// This is a clumsy demonstration app of the nonfuctional
// requirement: Wrapability.
//
// Here we are going to see how a simple pizza can improve
// itself by addeding new features like tomatoes or cheese
// without changing the simple base concrete component.
package main

import "fmt"

// Component interface
type pizza interface {
	Price() int
}

// Concrete component
type veggeMania struct {
}

func (p *veggeMania) Price() int {
	return 15
}

// Concrete decorator
type tomatoTopping struct {
	pizza pizza
}

func (c *tomatoTopping) Price() int {
	pizzaPrice := c.pizza.Price()
	return pizzaPrice + 7
}

// Concrete decorator
type cheeseTopping struct {
	pizza pizza
}

func (c *cheeseTopping) Price() int {
	pizzaPrice := c.pizza.Price()
	return pizzaPrice + 10
}

// Client code
func main() {
	// Create a simple concrete component.
	pizza := &veggeMania{}
	fmt.Printf("Price of veggeMania is %d\n", pizza.Price())

	// Add cheese topping.
	pizzaWithCheese := &cheeseTopping{
		pizza: pizza,
	}
	fmt.Printf("Price of veggeMania with cheese topping is %d\n",
		pizzaWithCheese.Price())

	// Add tomato topping.
	pizzaWithCheeseAndTomato := &tomatoTopping{
		pizza: pizzaWithCheese,
	}
	fmt.Printf("Price of veggeMania with tomato and cheese topping is %d\n",
		pizzaWithCheeseAndTomato.Price())
}
