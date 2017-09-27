# DentistManagment
A system that allows different dentists to create accounts and manage patients

written using javafx for UI and mysql for the database.
the deafult username for the mysql connection is "root" with password "a" change this to suit your own setup in
DentistManagment/utils/dataBaseFiles/DataBase.java

When compiling make sure to include DentistManagment/utils/dataBaseFiles/mysql-connector-java-5.1.41/mysql-connector-java-5.1.41-bin.jar
inside your class path.


Dentists can create and login to an account
Dentists can add/delete patients:
                          for each patient you can add/delete: 
                                                        dental procedure,
                                                        Invoices,
                                                        Payments,
                                                        Reports.
                                                        
The first time you run the program click create database. The program will then close and the next time you run it, it will present you with a login screen where you can create a dentist account.


