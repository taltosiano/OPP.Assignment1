# OPP.Assignment1

## Created By 
|Tal Tosiano |  208846600 | 
| ------------ | ------------ | 
| Adi Marocco | 2065002544 | 

  
#### In this file we'll explain in short view about our exercise

## Obserever Design-Patten  :goggles: :mailbox_with_mail: 
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing. so that the observer design pattern enables a subscriber to register with and receive notifications from a provider.
Indeed, this is reflected in our assignment, we had to create an observer (GroupAdmin) that alerts registered users (ConcereteMember) to any changes made to the UndoableStringBuilder.

## Classes

### GroupAdmin
GroupAdmin class implements the Sender interface and contain some methods for registering and unregistering ConcreteMember objects, methods about the status of an UndoableStringBuilder object.

### ConcreteMember
ConcreteMember class implements the Member interface and contain a method for updating the status of the Member which set by the status of the UndoableStringBuilder object. In this class, we saved the list of registers by a Hash-map type data structure.

#### `Expansion of the efficiency in using Hash-Map & why did we implement the methods through this structure`
This type of data structure takes more memory than an Array-list, but is much more efficient in terms of managing the methods on the subscribers. For that matter, to reach a registered member - the running time is O(1) , in List data structure is O(n) because we need to go through all the members of the list

### JvmUtilities
This class came structured and ready in the assignment, what we needed was to check the memory size of the objects we used. This way we can also verify the effectiveness of the code we wrote.

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
we use Junit 5 testing

### you can clone our repositorty here :point_down: :slightly_smiling_face:	:point_down: 
```
https://github.com/taltosiano/OPP.Assignment1.git

```


