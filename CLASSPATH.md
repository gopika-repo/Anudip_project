# Classpath Configuration for Library Management System

## Project Structure
```
javapro2/
├── src/                    # Source files
│   ├── dto/               # Data Transfer Objects
│   ├── dao/               # DAO Interfaces
│   ├── daoimpl/           # DAO Implementations
│   ├── service/           # Business Logic Services
│   ├── db/                # Database Connection
│   ├── ui/                # Swing UI Frames
│   └── main/              # Application Entry Point
├── bin/                   # Compiled .class files (auto-generated)
├── lib/                   # External Libraries (MySQL JDBC driver)
├── build.bat              # Batch build script
├── build.ps1              # PowerShell build script
├── run.bat                # Batch run script
├── run.ps1                # PowerShell run script
└── schema.sql             # MySQL database schema
```

## Classpath Components

1. **Source Classpath**: `src/` (during compilation)
2. **Compiled Classpath**: `bin/` (contains all .class files)
3. **External Libraries**: `lib/*` (MySQL JDBC driver)

## Compilation

### Command Line (Windows - CMD)
```
javac -d bin -cp "lib\*" src\db\DBConnection.java src\dto\*.java src\dao\*.java src\daoimpl\*.java src\service\*.java src\ui\*.java src\main\LibraryMain.java
```

### Command Line (Windows - PowerShell)
```powershell
javac -d bin -cp "lib\*" src/db/DBConnection.java src/dto/*.java src/dao/*.java src/daoimpl/*.java src/service/*.java src/ui/*.java src/main/LibraryMain.java
```

### Using Build Scripts
```
# Windows CMD
build.bat

# Windows PowerShell
.\build.ps1 -ExecutionPolicy Bypass
```

## Execution

### Command Line
```
java -cp "bin;lib\*" main.LibraryMain
```

### Using Run Scripts
```
# Windows CMD
run.bat

# Windows PowerShell
.\run.ps1
```

## Required JARs in lib/

Download and place the following in the `lib/` folder:
- **mysql-connector-java-8.x.x.jar** (or higher)
  - Download from: https://dev.mysql.com/downloads/connector/j/
  - Or use maven: `mvn dependency:copy-dependencies -DoutputDirectory=lib`

## Classpath Format

### Windows (using ; as separator)
```
bin;lib\mysql-connector-java-8.x.x.jar;lib\other-lib.jar
```

Or using wildcard (simpler):
```
bin;lib\*
```

### Linux/Mac (using : as separator)
```
bin:lib/mysql-connector-java-8.x.x.jar:lib/other-lib.jar
```

Or using wildcard:
```
bin:lib/*
```

## VS Code Configuration (launch.json)

If using VS Code with Java Extension Pack, create `.vscode/launch.json`:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "Launch Library Management System",
            "type": "java",
            "name": "Launch LibraryMain",
            "request": "launch",
            "mainClass": "main.LibraryMain",
            "projectName": "javapro2",
            "cwd": "${workspaceFolder}",
            "console": "integratedTerminal",
            "env": {
                "CLASSPATH": "${workspaceFolder}/bin;${workspaceFolder}/lib/*"
            }
        }
    ]
}
```

## Troubleshooting

### ClassNotFoundException for MySQL Driver
- Ensure `mysql-connector-java-x.x.x.jar` is in the `lib/` folder
- Verify classpath includes `-cp "bin;lib\*"`

### Package Errors
- All files must be compiled together with proper imports
- Use the build scripts provided for hassle-free compilation

### Database Connection Issues
- Update credentials in `src/db/DBConnection.java`
- Ensure MySQL server is running
- Run `schema.sql` to create the database and tables

## MySQL Setup

1. Open MySQL Workbench or MySQL CLI
2. Run the schema.sql file:
   ```sql
   source /path/to/javapro2/schema.sql;
   ```
3. Update password in `src/db/DBConnection.java`
4. Recompile the project

## Clean Build

To clean and rebuild:
```
rmdir /s bin
build.bat
```

Or in PowerShell:
```powershell
Remove-Item -Recurse -Force bin
.\build.ps1
```
