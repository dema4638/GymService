# GymService
### A guide for using gym web server

To launch the application:

1. Git clone https://github.com/dema4638/library.git
2. Git clone https://github.com/augkik/contacts.git
3. cd GymService
4. docker-compose up
5. [Optional] to rebuild application: docker-compose-up --build

### Follow these instructions to make SOAP requests:

#### GET
To see all members and their contacts:
```aidl
<?xml version = "1.0"?>
<SOAP-ENV:Envelope
   xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
   SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Body>
   <m:getAllMembersAndContacts xmlns:m = "http://gymService/">
      </m:getAllMembersAndContacts>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

To get the member ant their contacts with specific id:
```aidl
<?xml version = "1.0"?>
<SOAP-ENV:Envelope
   xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
   SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Body>
   <m:getMemberAndContacts xmlns:m = "http://gymService/">
   	<id>12345</id>
      </m:getMemberAndContacts>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

#### POST
To create a new member ant their constacts:
```aidl
<?xml version = "1.0"?>
<SOAP-ENV:Envelope
   xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
   SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Body>
   <m:postMemberAndContacts xmlns:m = "http://gymService/">
   	<id>123</id>
   	<membershipStartDate>2019-03-01</membershipStartDate>
   	<membershipEndDate>2020-03-01</membershipEndDate>
    <plan>premium</plan>
    <name>Will</name>
    <surname>Smith</surname>
    <number>862918394</number>
    <email>smith123@gmail</email>
      </m:postMemberAndContacts>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

#### DELETE
To delete a member and their contacts with specific id:
```aidl
<?xml version = "1.0"?>
<SOAP-ENV:Envelope
   xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
   SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Body>
     <m:deleteMemberAndContacts xmlns:m = "http://gymService/">
       <id>12345</id>
     </m:deleteMemberAndContacts>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
#### PUT
To update a member and their contacts with specific id:
```aidl
<?xml version = "1.0"?>
<SOAP-ENV:Envelope
   xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
   SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Body>
   <m:putMemberAndContacts xmlns:m = "http://gymService/">
   	<id>123</id>
   	<membershipStartDate>2019-03-01</membershipStartDate>
   	<membershipEndDate>2020-03-01</membershipEndDate>
    <plan>premium</plan>
    <name>Will</name>
    <surname>Smith</surname>
    <number>862918394</number>
    <email>smith123@gmail</email>
      </m:putMemberAndContacts>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### Follow these instructions to make REST requests:

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
