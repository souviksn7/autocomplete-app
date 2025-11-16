
# Autocomplete Application (Spring Boot + Trie + H2 + Docker)

A high-performance autocomplete service built using Spring Boot, H2 database, and a Trie data structure to achieve O(K) lookup time, where K = length of the prefix.
The application loads names from a file into H2 DB, builds an in-memory Trie, and exposes a clean REST API.

---

## 📦 Features

- REST API endpoint for name suggestions
- Fast O(K) prefix search using Trie
- Loader utility to read .txt and preload H2 file-based DB
- Clean architecture (Controller → Service → Repository → Trie DS)
- Case-insensitive search, original-case output
- Packaged as a Docker image
- Runs from Eclipse, IntelliJ, or Docker
- Integration with H2 database via Spring Data JPA
- Global exception handling

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.5.7
- Maven
- Spring Data JPA
- H2 Database (file-based)
- Docker (JDK for preload, JRE for runtime)

---

---

## 🐳 Running with Docker

### 1. Build the Docker image

```bash
docker build -t autocomplete-app .
```

### 2. Run the container

```bash
docker run -p 9090:9090 autocomplete-app
```

---

## 📦 Dockerfile Summary

- Stage 1: Build JAR using Maven
- Stage 2: Use JDK to preload H2 DB using DbLoader
- Stage 3: Use lightweight JRE to run the application

This ensures:

- final image is small
- DB is preloaded
- application runs efficiently

## 🧪 Running Locally

### 1. Clone the repository

```bash
git clone https://github.com/souviksn7/autocomplete-app.git
cd autocomplete-app
```

### 2. Build the project

```bash
mvn clean package
```

### 3. Load names into H2 (one-time step):

```bash
java -cp target/classes:~/.m2/repository/com/h2database/h2/<version>/h2-<version>.jar \
  com.souvik.autocomplete.loader.DbLoader data/BoyNames.txt
```

### 4. Run with Spring Boot

```bash
mvn spring-boot:run
```
---

## 🌐 Testing the API (Docker or Local)

### Example Request:

```bash
http://localhost:9090/api/autocomplete?prefix=al
```

### Response:

```json
{
  "suggestions": ["Alan", "Albert", "Alice"]
}
```
### Using curl:

```
curl "http://localhost:9090/api/autocomplete?prefix=al"
```

## 🧾 Project Structure

```
autocomplete-app/
│
├── data/                   # TXT file + generated H2 database files
│   └── BoyNames.txt
│
├── src/main/java/com/souvik/autocomplete/
│   ├── controller/
│   ├── datastructure/      # Trie + TrieNode
│   ├── dto/
│   ├── exception/
│   ├── loader/             # DbLoader
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── AutocompleteAppApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── Dockerfile
├── pom.xml
└── README.md

```

---

## ❗ Error Handling

- Global exception handler returns consistent JSON:
```json
{
  "timestamp": "...",
  "error": "Exception",
  "message": "Something went wrong"
}
```

---

## 📋 Final Notes

This repository includes:

- Source code
- Dockerfile
- DbLoader utility
- BoyNames.txt
- Persistent H2 database output (ignored using .gitignore)
- Clean project structure
- Professional comments and documentation

---

## 👨‍💻 Author

- Created by Souvik Nandi
- [souviknandi235@gmail.com]

---

## 📄 License

This project is part of a technical assignment and intended for demonstration only.