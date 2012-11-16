INSERT INTO user (id, created_date, deleted, modified_date, version, name, email, password, role) VALUES (1, '2012-06-01 16:02:49', '\0', NULL, 0, 'Administrator', 'admin@faqmasters.com', '52b890d1943a985cce2adae3e656e93799e18c1c92de09925204d3aeb28920136f81e5b6f454dfd3c437073e85865b49840c487a29f69ae39a514cd07e666179', 'ADMIN'),
	(2, '2012-11-09 17:51:34', '\0', NULL, 0, 'venky@gmail.com', 'Venky', '5fc6eaa208496dde8160ec4de6b9bec15b1be4cf2c8001b858fee355ba91b18f9b1a3b59de99148c06a17584b3fc1139ffafa2b1a300d46d4400c751b6fa1a9f', 'ADMIN');
	
INSERT INTO category (id, created_date, deleted, modified_date, version, categoryDescription, name) VALUES
	(1, '2012-11-09 17:52:15', '\0', NULL, 0, 'Java Category', 'Java'),
	(2, '2012-11-09 17:52:36', '\0', NULL, 0, 'php category', 'PHP'),
	(3, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for .NET', '.NET'),
	(4, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C#.NET', 'C#.NET'),
	(5, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ASP.NET', 'ASP.NET'),
	(6, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for AS400', 'AS400'),
	(7, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C', 'C'),
	(8, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for C++', 'C++'),
	(9, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ASP', 'SAP'),
	(10, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for DBMS', 'DBMS'),
	(11, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for MySQL', 'MySQL'),
	(12, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Oracle', 'Oracle'),
	(13, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for PostgreSQL', 'PostgreSQL'),
	(14, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for DB2', 'DB2'),
	(15, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for HTML', 'HTML'),
	(16, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for JavaScript', 'JavaScript'),
	(17, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for HTML5', 'HTML5'),
	(18, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Flash', 'Flash'),
	(19, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for ActionScript', 'ActionScript'),
	(20, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2SE. Core java, collections, threading etc..', 'J2SE'),
	(21, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. EJB', 'J2EE'),
	(22, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. Servlets.', 'Servlet'),
	(23, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for J2EE. JSP', 'JSP'),
	(24, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for core spring framework', 'Spring'),
	(25, '2012-11-09 17:53:05', '\0', NULL, 0, 'category for Spring MVC Framework', 'Spring MVC');

INSERT INTO parent_category(parent_category_id, category_id) VALUES (1, 20), (18, 19), (1, 21), (1, 22), (1, 23), (1, 24), (1, 25); 