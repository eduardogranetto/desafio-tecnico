# LEMBRE-ME
Repositório geral da aplicação 

#RUN
 java -jar target/lembre.me-0.0.1-alpha.jar --spring.data.mongodb.uri=mongodb://localhost:27017/lembre.me
 java -jar target/lembre.me-0.0.1-alpha.jar --spring.config.name=work
 java -jar target/lembre.me-0.0.1-alpha.jar --spring.config.name=heroku
 
 
#HEROKU
heroku
heroku create 
heroku apps:rename lembre-me
git push heroku master
heroku addons:create mongolab:sandbox //Mongo db free

#HEROKU (VINCULAR PROJETO)
heroku git:remote lembre-me