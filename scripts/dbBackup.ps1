<#
.SYNOPSIS
    Script para hacer backup de una base de datos MySQL en la carpeta ../db.
.DESCRIPTION
    Exporta la base de datos MySQL usando mysqldump y guarda el archivo .sql en ../db.
#>

# Configuración de la base de datos
$MySQLHost = "localhost"
$MySQLPort = "3306"
$MySQLUser = "root"
$MySQLPassword = "root"
$DatabaseName = "athletix"

# Nombre del archivo de backup (con fecha)
$BackupFileName = "athletix_backup_$(Get-Date -Format 'yyyyMMdd_HHmmss').sql"

# Ruta de destino: ../db (una carpeta atrás y luego dentro de /db)
$ScriptDir = $PSScriptRoot
$ParentDir = Split-Path -Path $ScriptDir -Parent
$BackupDir = Join-Path -Path $ParentDir -ChildPath "db"
$BackupPath = Join-Path -Path $BackupDir -ChildPath $BackupFileName

# Asegurar que la carpeta ../db exista
if (-not (Test-Path -Path $BackupDir)) {
    New-Item -ItemType Directory -Path $BackupDir | Out-Null
}

# Comando mysqldump
$MySQLDumpPath = "mysqldump"  # Asegúrate de que esté en el PATH

try {
    # Ejecutar mysqldump
    & $MySQLDumpPath `
        --host=$MySQLHost `
        --port=$MySQLPort `
        --user=$MySQLUser `
        --password=$MySQLPassword `
        --databases $DatabaseName `
        --result-file=$BackupPath `
        --no-tablespaces `
        --skip-lock-tables

    Write-Host "✅ Backup completado: $BackupPath" -ForegroundColor Green
} catch {
    Write-Host "❌ Error durante el backup: $_" -ForegroundColor Red
}