# OPP.Assignment1

### By Tal Tosiano & Adi Marocco

#### In this file we'll explain in short view about our exercise

## Obserever Design-Patten
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing. so that the observer design pattern enables a subscriber to register with and receive notifications from a provider.
Indeed, this is reflected in our assignment, we had to create an observer (GroupAdmin) that alerts registered users (ConcereteMember) to any changes made to the UndoableStringBuilder.

## Classes

### GroupAdmin
GroupAdmin class implements the Sender interface and contain some methods for registering and unregistering ConcreteMember objects, methods about the status of an UndoableStringBuilder object.

### ConcreteMember
ConcreteMember class implements the Member interface and contain a method for updating the status of the Member which set by the status of the UndoableStringBuilder object.

|function in GroupAdmin |  Description | 
| ------------ | ------------ | 
| notifyRegister() | notify all the register members about every change |  
| register() | add new member to the hash |   
| unregister() | remove member from the registers hash | 
| insert() | inserts a string into character and notify all the registers |  
| append() | append a string (obj) to character (our usb) and notify all the register |  
| delete() | delete the characters in a substring of this sequence and notify all the registers |  
| undo() | erases the last change done to the document and notify all the registers | 

|function in ConcreteMember |  Description | 
| ------------ | ------------ | 
| update() | updating our usb to the modified string |  

## Tests

### you can clone our project here :point_down: :slightly_smiling_face:	:point_down: : 
```
https://github.com/taltosiano/OPP.Assignment1.git

```


