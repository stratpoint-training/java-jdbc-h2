# **🔥 SimpleJDBC - H2 MySQL Mode CRUD App**  
_A simple yet powerful Java JDBC app with H2 Database (MySQL Mode)!_ 😎  

## **📌 Features**
✅ **Zero setup** – No need to install MySQL, just run!  
✅ **CRUD Operations** – Create, Read, Update, Delete users.  
✅ **Interactive CLI** – Enter commands dynamically.  
✅ **Lightweight** – Uses only Java + JDBC (No extra dependencies).  

---

## **🚀 Getting Started**
### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/stratpoint-training/java-jdbc-h2.git
cd SimpleJDBC
```

### **2️⃣ Add Dependencies**
If using **Maven**, make sure your `pom.xml` has:
```xml
<dependencies>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.224</version>
    </dependency>
</dependencies>
```

### **3️⃣ Run the Java App**
Compile and run:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.example.SimpleJDBC"

```
Or run directly via **IDE (IntelliJ / Eclipse)**.

---

## **🎮 How to Use**
After running, you'll see this menu:
```
📌 Choose an action:
1️⃣ Insert User
2️⃣ View Users
3️⃣ Update User
4️⃣ Delete User
5️⃣ Exit
👉 Enter choice: 
```
Just enter a number (e.g., `1` for adding a user) and follow the instructions!

### **🛠️ CRUD Operations**
| Action | Command |
|--------|---------|
| Add User | Enter `1`, then type the name |
| View Users | Enter `2` |
| Update User | Enter `3`, then provide ID & new name |
| Delete User | Enter `4`, then provide ID |
| Exit | Enter `5` |

---

## **📂 Project Structure**
```
📦 SimpleJDBC
 ┣ 📜 src/main/java/SimpleJDBC.java  # Main app
 ┣ 📜 pom.xml                        # Maven dependencies
 ┣ 📜 README.md                      # This file
```

---

## **🔮 Future Enhancements**
- [ ] Add **HikariCP** for better performance  
- [ ] Connect to a real **MySQL database**  
- [ ] Convert into a **Spring Boot REST API**  

---

## **📣 Contribute**
🔥 Found a bug? Have a suggestion?  
Feel free to **fork this repo** and submit a **pull request!** 🚀  

---

## **📜 License**
📖 This project is **MIT Licensed** – Use it freely! 😃  



