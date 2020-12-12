package main

type subscriber interface {
	update(string)
	getID() string
}
