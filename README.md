# GymService
### A guide for using gym web server

To launch the application:

1. Git clone https://github.com/dema4638/library.git
2. Git clone https://github.com/augkik/contacts.git
3. cd GymService
4. docker-compose up
5. [Optional] to rebuild application: docker-compose-up --build

### Follow these instructions:

#### GET
```aidl
URI:
/memberContact
```
To get all the members and their contacts.

```aidl
URI:
/memberContact/12345
```
To get one member with particular id.

#### PUT
```aidl
URI:
/memberContact/1111

Body:
{"surname": "Smith", "name": "Will", "number": "863721367", "email": "WillSmith@gmail.com", "membershipStart" : "2020-03-01T22:00:00Z[UTC]","membershipEnd":"2020-03-30T22:00:00Z[UTC]","plan":"Premium"}
```
To update member's information or add new member, when member with given id is not found in the database.

#### DELETE
```aidl
URI:
/memberContact/12345
```
To delete the member from registry with specific id.

#### POST
```aidl
URI:
/memberContact

Body:
{"surname": "Smith", "name": "Will", "number": "863721367", "email": "WillSmith@gmail.com", "membershipStart" : "2020-03-01T22:00:00Z[UTC]","membershipEnd":"2020-03-29T22:00:00Z[UTC]","plan":"Premium", "id":156458}
```
To add new member to the gym database.
