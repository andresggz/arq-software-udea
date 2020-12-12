package main

type publisher interface {
	subscribe(s subscriber)
	unsubscribe(s subscriber)
	notifyAll()
}
