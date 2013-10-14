bookapp-cqrs [![Build Status](https://travis-ci.org/rvillars/bookapp-cqrs.png?branch=master)](https://travis-ci.org/rvillars/bookapp-cqrs)
============
This is a small example bookstore application with a basic CQRS/DDD driven approach.
It uses Axon Framework for server-side CQRS and an embedded Vert.X eventbus to propagate events directly to the client.
The client uses AngularJS to handle the events.

The example consists currently of just two aggregates so it isn't actually a good example for Domain Driven Development.
But it shows how to implement a Command/Event driven application interface with Axon and a way to extend this approach to
web clients without using the very non-eventdriven REST API.

For a short introduction in CQRS see [this Prezi](http://prezi.com/bbbqharguddg/?utm_campaign=share&utm_medium=copy&rc=ex0share): 

Technologies used:
* Embedded Vert.X
* Axon Framework
* CQRS/DDD
* AngularJS
* jQueryUI
* SpringMVC
* maven
* JPA/EclipseLink


