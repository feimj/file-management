# file-management
This project provides a few file management API, such as finding files in a folder, sorting and ordering files, deleting files. 

## usage
1. Open file ```file-management\src\main\webapp\WEB-INF\spring\filemanagement.properties```，then modify the value of ```filemanagement.rootPath```.

2. Config your own tomcat server. Find your tomcat's ```server.xml```，and then add configuration under the tag ```<host>```.
```
<Context path="/file-management/files" docBase="F:\FRO-PC\D\fro\测试\图片管理"/>
```
  The value of ```docBase``` is the same as the value of ```filemanagement.rootPath``` you save in step 1.
  
3. Run command ```mvn clean package``` or ```mvn clean install``` to build a war file and deploy it with your tomcat server.
