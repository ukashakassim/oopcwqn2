Case Study: GUI & Database Integration in Exhibition Registration System
Victoria University through the Faculty of Science and Technology is preparing for 
its first annual Innovation and Technology Exhibition, an event designed to showcase 
the creativity, research, and technological capabilities of its students. With participation
expected from the faculty departments, the need for a structured and efficient system to manage 
the registration and tracking of exhibitors has become critical. The organizing committee has 
therefore proposed the development of a Java-based desktop application to digitize and streamline 
the entire registration process internally at the University.
Currently, participant registration is handled manually using paper forms or basic spreadsheets 
during their Bi-weekly meetings at Market Plaza, which has led to issues such as duplicate entries, 
data loss, and difficulty in retrieving participant details on short notice.
Given the growing scale of the exhibition, manual methods are no longer viable. 
The university requires a robust application that offers a graphical user interface 
(GUI) for ease of use, supports full CRUD (Create, Read, Update, Delete) functionality, 
and integrates seamlessly with any DBMS particularly, a Microsoft Access database for data storage.
This application will enable exhibition administrators to efficiently register new participants, 
view and update student records, and delete entries in case of cancellations. Each participant’s 
record must include a Registration ID, Student Name, Faculty, Project Title, Contact Number, 
Email Address, and an image of the project prototype. The system should also allow administrators 
to search for participants using their Registration ID, upload and display project images, and ensure 
all input is properly validated before database interaction.
To support this, the application must utilize UCanAccess JDBC driver for connectivity with the Access
database (VUE_Exhibition.accdb), and implement proper exception handling, prepared statements for secure SQL operations,
and input validation for data integrity

System Features
1. GUI for registering participants with fields analyzed from the Project background
2. Buttons: Register, Search, Update, Delete, Clear, Exit
3. Database operations using UCanAccess JDBC Driver
Required
a) Design and implement a Java GUI using Swing or JavaFX for student registration. Ensure proper layout and labelling of form components.
b) Create the Access database with a table Participants having relevant fields. Populate with at least 5 sample entries.
c) Write Java code to perform the following database operations using PreparedStatements and exception handling.
(i) Insert (Register a participant)
(ii) Search (Retrieve participant data by Registration ID) (iii)Update (Modify existing participant details) (iv)Delete (Remove a participant)
d) Add functionality to upload and display an image file from the local machine using JFileChooser. Save the image path in the database and render it on the form.
e) Explain briefly how you validated input (e.g., no empty fields, correct email format) and ensured data consistency between the GUI and the database.

Submission Checklist
(i) Java source files (.java) should be provided in form of GitHub Link
(ii) Screenshot(s) or PDF of GUI and outputs put in the Ms. Word.
(iii)Access database file (.accdb) provided as a link from GitHub (iv)Documentation.
