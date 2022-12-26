# OPP.Assignment1

### By Tal Tosiano & Adi Marocco

#### In this file we'll explain in short view about our exercise

## Obserever Design-Patten
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing. so that the observer design pattern enables a subscriber to register with and receive notifications from a provider. It is suitable for any scenario that requires push-based notification. The pattern defines a provider (also known as a subject or an observable) and zero, one, or more observers. Observers register with the provider, and whenever a predefined condition, event, or state change occurs, the provider automatically notifies all observers by calling one of their methods. In this method call, the provider can also provide current state information to observers
Indeed, this is reflected in our assignment, we had to create an observer (GroupAdmin) that alerts registered users (ConcereteMember) to any changes made to the UndoableStringBuilder.

## Classes

### GroupAdmin
GroupAdmin class implements the Sender interface and contain some methods for registering and unregistering ConcreteMember objects, methods about the status of an UndoableStringBuilder object.

### ConcreteMember
ConcreteMember class implements the Member interface and contain a method for updating the status of the Member which set by the status of the UndoableStringBuilder object.

## Tests
