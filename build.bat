@echo off
REM Build script for Library Management System
REM Compiles all Java source files with MySQL JDBC driver in classpath

setlocal enabledelayedexpansion

REM Set directories
set SRC_DIR=src
set BIN_DIR=bin
set LIB_DIR=lib

REM Create bin directory if it doesn't exist
if not exist %BIN_DIR% mkdir %BIN_DIR%

REM Compile all Java files
echo Compiling Java files...
javac -d %BIN_DIR% -cp "%LIB_DIR%\*" "%SRC_DIR%\db\DBConnection.java" ^
  "%SRC_DIR%\dto\MemberDTO.java" ^
  "%SRC_DIR%\dto\BookDTO.java" ^
  "%SRC_DIR%\dto\BorrowRecordDTO.java" ^
  "%SRC_DIR%\dao\MemberDAO.java" ^
  "%SRC_DIR%\dao\BookDAO.java" ^
  "%SRC_DIR%\dao\BorrowDAO.java" ^
  "%SRC_DIR%\daoimpl\MemberDAOImpl.java" ^
  "%SRC_DIR%\daoimpl\BookDAOImpl.java" ^
  "%SRC_DIR%\daoimpl\BorrowDAOImpl.java" ^
  "%SRC_DIR%\service\MemberService.java" ^
  "%SRC_DIR%\service\BookService.java" ^
  "%SRC_DIR%\service\BorrowService.java" ^
  "%SRC_DIR%\service\AdminService.java" ^
  "%SRC_DIR%\ui\MainFrame.java" ^
  "%SRC_DIR%\ui\RegisterFrame.java" ^
  "%SRC_DIR%\ui\MemberLoginFrame.java" ^
  "%SRC_DIR%\ui\MemberDashboardFrame.java" ^
  "%SRC_DIR%\ui\BrowseBooksFrame.java" ^
  "%SRC_DIR%\ui\MyBorrowsFrame.java" ^
  "%SRC_DIR%\ui\AdminDashboardFrame.java" ^
  "%SRC_DIR%\main\LibraryMain.java"

if !errorlevel! equ 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo   java -cp "bin;lib\*" main.LibraryMain
) else (
    echo Compilation failed!
    exit /b 1
)

endlocal
