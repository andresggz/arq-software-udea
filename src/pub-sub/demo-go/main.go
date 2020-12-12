package main

func main() {
	shirtItem := newItem("Nike Shirt")

	firstSubscriber := &customer{id: "abc@gmail.com"}
	secondSubscriber := &customer{id: "xyz@gmail.com"}

	shirtItem.subscribe(firstSubscriber)
	shirtItem.subscribe(secondSubscriber)

	shirtItem.updateAvailability()
}
