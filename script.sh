# compiling source code
javac -classpath ./lib/servlet-api.jar:./lib/r-w-x_file.jar -d ./bin/framework --enable-preview --release 19 $(find  ./framework/src/ -name '*.java')
cd ./bin/framework

# exporting the framework to a jar file
jar cvf ../../spring.jar *
cd ../../

# creating the directory structure for the project test to deploy
mkdir ./temp ./temp/views ./temp/models ./temp/WEB-INF ./temp/WEB-INF/classes ./temp/WEB-INF/lib

# copying jar file to the project library and the web.xml file
cp ./spring.jar ./temp/WEB-INF/lib
cp ./lib/*.jar ./temp/WEB-INF/lib
cp ./test-framework/web.xml ./temp/WEB-INF/
cp ./test-framework/*.jsp ./temp/
cp -R ./test-framework/views/* ./temp/views/
cp -R ./test-framework/models/* ./temp/models/

# compiling models and other user necessity to the project classes directory
javac -classpath ./spring.jar -d ./temp/WEB-INF/classes --enable-preview --release 19 $(find ./test-framework/ -name '*.java')
cd ./temp

# exporting the temp directory to a war file and move it to tomcat webapps folder
jar cvf /opt/apache-tomcat/apache-tomcat-10.1.7/webapps/spring.war *
cd ../

# removing temp directory
#rm -r ./temp
