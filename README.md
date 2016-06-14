#Desafio-tecnico
Projeto desafio técnico

##No Ar
Existe uma versão no ar em:

[https://desafio-tecnico.herokuapp.com/](https://desafio-tecnico.herokuapp.com/)

##Executando:

###Dependencias:

-Java 8
-Maven 3

##Rodando

```bash
       
      mvn clean install && java -jar target/desafio-tecnico-0.0.1.jar --spring.datasource.url=jdbc:mysql://IP:PORTA/BASE --spring.datasource.username=USUARIO_BD --spring.datasource.password=SENHA_BD
```

ou 


```bash
       
      java -jar target/desafio-tecnico-0.0.1.jar --spring.datasource.url=jdbc:mysql://IP:PORTA/BASE --spring.datasource.username=USUARIO_BD --spring.datasource.password=SENHA_BD
```