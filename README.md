
https://github.com/mmsobotka/senior-calendar/assets/73854682/601112c5-2c85-4fd9-aeb7-e3df825678b5

Project Launch Instructions:
1. Open the xampp/htdocs folder.
2. Paste the add_data.php and get_data.php files into this folder from the server-scripts project folder.
3. Launch the XAMPP application: click start next to Apache and MySQL.
4. Enter localhost in the browser.
5. Go to the phpMyAdmin tab at the top right corner, at the very end.
6. On the left are options for creating a database, click New and create a new database with the default settings named senior-calendar.
7. Go to our database and enter the SQL tab, located in the top panel.
8. Create a new table in the database with the command: CREATE TABLE data ( TIMESTAMP date, USER_ID text, TEMPERATURE float, SUGAR_LEVEL float, WEIGHT int, BLOOD_PRESSURE int, HEART_RATE int, SATURATION int); and click Go.
9. Then, using the ipconfig command, retrieve our IP from the IPv4 Address, the IP will be useful for recording in the project.
10. Assign the retrieved IP to a variable in the project in the ProjectVariables interface to IPv4 with the suffix :80, an example variable value: 192.168.0.229:80.
11. Run the project in the emulator, adding new data to the database using the save measurement button. It's important to remember the conditions for accepting input from the validate_data function of the MainActivity3 class. The table structure and saved data can be viewed on the phpMyAdmin page by clicking on the table tab in our database section.
