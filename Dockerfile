### ----- STAGE 1: Build the JAR using Maven -----
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies first (Docker caching)
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

########################
# 2. Preload stage (JDK required)
########################

# Runtime base image with JDK 21
### ------ 2. PRELOAD DATABASE ------
FROM eclipse-temurin:21-jdk AS preload
WORKDIR /app

# Copy JAR
COPY --from=build /app/target/autocomplete-app-0.0.1-SNAPSHOT.jar app.jar

# Unpack Spring Boot fat jar (required to access classes)
RUN mkdir unpacked && \
    cd unpacked && \
    jar -xf ../app.jar

# Copy data file
COPY data ./data

# Run DbLoader from unpacked classes
RUN java -cp "unpacked/BOOT-INF/classes:unpacked/BOOT-INF/lib/*" \
    com.souvik.autocomplete.loader.DbLoader data/BoyNames.txt


########################
# 3. Runtime stage (JRE OK)
########################

FROM eclipse-temurin:21-jre-alpine AS runtime


# Copy app and DB from preload stage
COPY --from=preload /app/app.jar .
COPY --from=preload /app/data ./data

# Expose port 9090
EXPOSE 9090

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]