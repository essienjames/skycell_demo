# 🌐 SkyCell Demo - Test Automation Framework

Welcome to the **SkyCell Demo** project!  
This is a lightweight, maintainable, and robust end-to-end test automation framework.
It automates testing for SkyCell’s SkyMind web UI.

---

## 🧰 Tech Stack

- ⚙️ **Java 21**
- 🎭 **Playwright for Java**
- 📦 **Maven**
- ✅ **JUnit 5**
- 💻 **Zsh / Terminal-friendly setup**

---

## 📦 Project Setup

### 1️⃣ Prerequisites
Ensure you have the following installed:
- Java 21+
- Maven 3.8+

### 2️⃣ Install Dependencies

```bash
    mvn clean install
```

---

## 🎯 Running Tests

### 1️⃣ Run All Tests
Tests will run in headless mode by default

```bash
    mvn test
```

To run headed use:
```bash
    mvn test -Dheadless=false    
```

### 2️⃣ Run a Specific Test

```bash
    mvn test -Dtest=AssetsTableTest    
```

## 📸 Tracing & Debugging
Playwright's tracing is enabled and will capture:
- ✅ Screenshots
- ✅ Snapshots
- ✅ Source files

Results are saved in traces/trace.zip for each test run, to open the Trace Viewer use:
```bash
    npx playwright show-trace traces/trace.zip
```

### Happy Testing! 👍
