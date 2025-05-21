<#
.SYNOPSIS
    Backup SOLO de datos (sin estructura) de MySQL.
#>

# Configuración
$MySQLHost = "localhost"
$MySQLPort = "3306"
$MySQLUser = "root"
$MySQLPassword = "root"
$DatabaseName = "athletix"

# Ruta de destino: ../db/data/
$ScriptDir = $PSScriptRoot
$BackupDir = Join-Path -Path (Split-Path -Path $ScriptDir -Parent) -ChildPath "db\data"
$BackupPath = Join-Path -Path $BackupDir -ChildPath "athletix_data_$(Get-Date -Format 'yyyyMMdd_HHmmss').sql"

# Crear carpeta si no existe
if (-not (Test-Path -Path $BackupDir)) {
    New-Item -ItemType Directory -Path $BackupDir | Out-Null
}

# Comando mysqldump (--no-create-info = solo datos)
try {
    & mysqldump `
        --host=$MySQLHost `
        --port=$MySQLPort `
        --user=$MySQLUser `
        --password=$MySQLPassword `
        --no-create-info `
        --skip-triggers `
        --skip-add-drop-table `
        --databases $DatabaseName `
        --result-file=$BackupPath `
        --no-tablespaces `
        --skip-lock-tables

    Write-Host "✅ Backup de DATOS completado: $BackupPath" -ForegroundColor Green
} catch {
    Write-Host "❌ Error: $_" -ForegroundColor Red
}