package main

import "fmt"

type item struct {
	subscribers []subscriber
	name        string
	inStock     bool
}

func newItem(name string) *item {
	return &item{
		name: name,
	}
}

func (i *item) updateAvailability() {
	fmt.Printf("Item %s is now in stock\n", i.name)
	i.inStock = true
	i.notifyAll()
}

func (i *item) subscribe(s subscriber) {
	i.subscribers = append(i.subscribers, s)
}

func (i *item) unsubscribe(s subscriber) {
	i.subscribers = removeFromslice(i.subscribers, s)
}

func (i *item) notifyAll() {
	for _, subscriber := range i.subscribers {
		subscriber.update(i.name)
	}
}

func removeFromslice(subscribers []subscriber, subscriberToRemove subscriber) []subscriber {
	subscribersLength := len(subscribers)
	for i, subscriber := range subscribers {
		if subscriberToRemove.getID() == subscriber.getID() {
			subscribers[subscribersLength-1], subscribers[i] = subscribers[i], subscribers[subscribersLength-1]
			return subscribers[:subscribersLength-1]
		}
	}
	return subscribers
}
