# 创建Dockerfile 文件 编写 Dockerfile 内容
FROM java:11

COPY *.jar /app.jar

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

