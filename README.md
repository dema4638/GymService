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
/books/01234567890

Body:
{"Pavadinimas": "", "Autorius": "", "Metai":""}
```
To change book's title, author or year it was written, according to it's ISBN. If given ISBN in URI is not found in the library, a new book is created on the list.

#### DELETE
```aidl
URI:
/books/9786090138823
```
To delete the book which ISBN is given in URI.

#### POST
```aidl
URI:
/books

Body:
{"Pavadinimas": "", "Autorius": "", "ISBN": "", "Metai":""}
```
To add new book to the library.
# GymService
