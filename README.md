bookapp-cqrs
============
This is a small example bookstore application with a basic CQRS/DDD driven approach.
It uses Axon Framework for server-side CQRS and an embedded Vert.X eventbus to propagate events directly to the client.
The client uses AngularJS to handle the events.

The example consists currently of just two aggregates so it isn't actually a good example for Domain Driven Development.
But it shows how to implement a Command/Event driven application interface with Axon and way to extend this approach to
web clients without using the very non-eventdriven REST API.

Technologies used:
* Vert.X
* Axon Framework
* CQRS/DDD
* AngularJS
* jQueryUI
* SpringMVC
* maven
* JPA/EclipseLink


