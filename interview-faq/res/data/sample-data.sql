INSERT INTO user (id, created_date, deleted, modified_date, version, name, email, password, role) VALUES (1, '2012-06-01 16:02:49', '\0', NULL, 0, 'Administrator', 'admin@faqmasters.com', '6f87c6a32d765bbfb7113732e046a71d9537cbea3cd2c145c5cf08c95997b88b', 'ADMIN'),
	(2, '2012-11-09 17:51:34', '\0', NULL, 0, 'venky@gmail.com', 'Venky', '573027f536ca557e79b8e8247db75dad96a0aa50dc150c4ff3d34a06b98562cd', 'ADMIN');
	
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