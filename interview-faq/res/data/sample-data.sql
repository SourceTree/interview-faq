INSERT INTO user (id, created_date, deleted, modified_date, version, name, email, password, role) VALUES (1, '2012-06-01 16:02:49', '\0', NULL, 0, 'Administrator', 'admin@faqmasters.com', '52b890d1943a985cce2adae3e656e93799e18c1c92de09925204d3aeb28920136f81e5b6f454dfd3c437073e85865b49840c487a29f69ae39a514cd07e666179', 'ADMIN'),
	(2, '2012-11-09 17:51:34', '\0', NULL, 0, 'venky@gmail.com', 'Venky', '5fc6eaa208496dde8160ec4de6b9bec15b1be4cf2c8001b858fee355ba91b18f9b1a3b59de99148c06a17584b3fc1139ffafa2b1a300d46d4400c751b6fa1a9f', 'ADMIN');
	
INSERT INTO category (id, created_date, deleted, modified_date, version, categoryDescription, displayname,name) VALUES
	(1, '2012-11-09 17:52:15', '\0', NULL, 0, 'Java Category', 'Java','Java'),
	(2, '2012-11-09 17:52:36', '\0', NULL, 0, 'php category', 'PHP','PHP'),
	(3, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for .NET', '.NET','DOTNET'),
	(4, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C#.NET', 'C#.NET','CNET'),
	(5, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ASP.NET', 'ASP.NET','ASPNET'),
	(6, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for AS400', 'AS400','AS400'),
	(7, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C', 'C','C'),
	(8, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C++', 'C++','C++'),
	(9, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ASP', 'SAP','SAP'),
	(10, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for DBMS', 'DBMS','DBMS'),
	(11, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for MySQL', 'MySQL','MYSQL'),
	(12, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Oracle', 'Oracle','Oracle'),
	(13, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for PostgreSQL', 'PostgreSQL','PostgreSQL'),
	(14, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for DB2', 'DB2','DB2'),
	(15, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for HTML', 'HTML','HTML'),
	(16, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for JavaScript', 'JavaScript','JavaScript'),
	(17, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for HTML5', 'HTML5','HTML5'),
	(18, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Flash', 'Flash','Flash'),
	(19, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ActionScript', 'ActionScript','ActionScript'),
	(20, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2SE. Core java, collections, threading etc..', 'J2SE','J2SE'),
	(21, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. EJB', 'J2EE','J2EE'),
	(22, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. Servlets.', 'Servlet','Servlet'),
	(23, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. JSP', 'JSP','JSP'),
	(24, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for core spring framework', 'Spring','Spring'),
	(25, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Spring MVC Framework', 'Spring MVC','SpringMVC');

INSERT INTO PARENT_CATEGORY(parent_category_id, category_id) VALUES (1, 20), (18, 19), (1, 21), (1, 22), (1, 23), (1, 24), (1, 25); 

INSERT INTO question(id, created_date, deleted, modified_date, version, answer,question) VALUES
	(1, '2012-11-09 17:52:15', '\0', NULL, 0, 'Hello welcome to java answer','What is Java'),
	(2, '2012-11-09 17:52:36', '\0', NULL, 0, 'An abstract class can have instance methods that implement a default behavior. An Interface can only declare constants and instance methods, but cannot implement default behavior and all methods are implicitly abstract. An interface has all public members and no implementation. An abstract class is a class which may have the usual flavors of class members (private, protected, etc.), but has some abstract methods.', 'What is the difference between an Interface and an Abstract class?'),
	(3, '2012-11-09 17:53:05', '\0', NULL, 0, 'The purpose of garbage collection is to identify and discard objects that are no longer needed by a program so that their resources can be reclaimed and reused. A Java object is subject to garbage collection when it becomes unreachable to the program in which it is used.', 'What is the purpose of garbage collection in Java, and when is it used?'),
	(4, '2012-11-09 17:53:05', '\0', NULL, 0, 'With respect to multithreading, synchronization is the capability to control the access of multiple threads to shared resources. Without synchonization, it is possible for one thread to modify a shared variable while another thread is in the process of using or updating same shared variable. This usually leads to significant errors.', 'Describe synchronization in respect to multithreading.'),
	(5, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 5', 'Welcome question 5?'),
	(6, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 6', 'Welcome question 6?'),
	(7, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 7', 'Welcome Myquestion 7?'),
	(8, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 8', 'Welcome Myquestion 8?'),
	(9, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 9', 'Welcome Myquestion 9?'),
	(10, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 10', 'Welcome Myquestion 10?'),
	(11, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 11', 'Welcome Myquestion 11?'),
	(12, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 12', 'Welcome Myquestion 12?'),
	(13, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 13', 'Welcome Myquestion 13?'),
	(14, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 14', 'Welcome Myquestion 14?'),
	(15, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 15', 'Welcome Myquestion 15?'),
	(16, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 16', 'Welcome Myquestion 16?'),
	(17, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 17', 'Welcome Myquestion 17?'),
	(18, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 18', 'Welcome Myquestion 18?'),
	(19, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 19', 'Welcome Myquestion 19?'),
	(20, '2012-11-09 17:53:05', '\0', NULL, 0, 'A brief description of answer 20', 'Welcome Myquestion 20?');

INSERT INTO CATEGORY_QUESTION(question_id,category_id) VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),
(15,1),(16,2),(17,2),(18,2),(19,2),(12,2),(1,2),(2,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(11,4),(12,4),(1,20),(2,20),(3,20),(4,21),(5,21),(6,21);	
