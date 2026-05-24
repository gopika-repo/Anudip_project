# Build script for Library Management System
# Compiles all Java source files with MySQL JDBC driver in classpath

$SRC_DIR = "src"
$BIN_DIR = "bin"
$LIB_DIR = "lib"

# Create bin directory if it doesn't exist
if (-not (Test-Path $BIN_DIR)) {
    New-Item -ItemType Directory -Path $BIN_DIR | Out-Null
}

# Compile all Java files
Write-Host "Compiling Java files..."

$sourceFiles = @(
    "$SRC_DIR\db\DBConnection.java",
    "$SRC_DIR\dto\MemberDTO.java",
    "$SRC_DIR\dto\BookDTO.java",
    "$SRC_DIR\dto\BorrowRecordDTO.java",
    "$SRC_DIR\dao\MemberDAO.java",
    "$SRC_DIR\dao\BookDAO.java",
    "$SRC_DIR\dao\BorrowDAO.java",
    "$SRC_DIR\daoimpl\MemberDAOImpl.java",
    "$SRC_DIR\daoimpl\BookDAOImpl.java",
    "$SRC_DIR\daoimpl\BorrowDAOImpl.java",
    "$SRC_DIR\service\MemberService.java",
    "$SRC_DIR\service\BookService.java",
    "$SRC_DIR\service\BorrowService.java",
    "$SRC_DIR\service\AdminService.java",
    "$SRC_DIR\ui\MainFrame.java",
    "$SRC_DIR\ui\RegisterFrame.java",
    "$SRC_DIR\ui\MemberLoginFrame.java",
    "$SRC_DIR\ui\MemberDashboardFrame.java",
    "$SRC_DIR\ui\BrowseBooksFrame.java",
    "$SRC_DIR\ui\MyBorrowsFrame.java",
    "$SRC_DIR\ui\AdminDashboardFrame.java",
    "$SRC_DIR\main\LibraryMain.java"
)

$classpath = "$LIB_DIR\*"
$fileList = $sourceFiles -join '" "' | ForEach-Object { "`"$_`"" }

$compileCmd = "javac -d $BIN_DIR -cp `"$classpath`" $fileList"
Invoke-Expression $compileCmd

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "Compilation successful!"
    Write-Host ""
    Write-Host "To run the application, use:"
    Write-Host "  java -cp `"bin;lib\*`" main.LibraryMain"
} else {
    Write-Host "Compilation failed!"
    exit 1
}
