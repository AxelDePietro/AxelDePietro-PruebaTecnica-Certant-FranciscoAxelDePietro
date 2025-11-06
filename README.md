# Prueba Tecnica Gestor de Reservas

Prueba Tecnica brindada por Certant.

#### Tecnologias Usadas

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

#### Informacion Previa a compilar la aplicacion

la api no posee un front ya que fue diseñada con Thymeleaf, las vista viajan directo del backend al cliente.

Se debe crear una base de datos local en MySQL la cual debe estar vacia, ademas de reemplzar la url, usuario y contraseña en el respectivo aplication.properties.

Ejemplo de script sql:

		CREATE DATABASE IF NOT EXISTS pruebatecnica_certant;
		USE pruebatecnica_certant;


De lo demas (crear y relacionar entidades) se ocupara Spring.
