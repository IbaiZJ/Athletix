<#
.SYNOPSIS
    Backup SOLO de estructura (sin datos) de MySQL.
#>

# Configuración (igual que el anterior)
$MySQLHost = "localhost"
$MySQLPort = "3306"
$MySQLUser = "root"
$MySQLPassword = "root"
$DatabaseName = "athletix"

# Ruta de destino: ../db/schema/
$ScriptDir = $PSScriptRoot
$BackupDir = Join-Path -Path (Split-Path -Path $ScriptDir -Parent) -ChildPath "db\schema"
$BackupPath = Join-Path -Path $BackupDir -ChildPath "athletix_schema_$(Get-Date -Format 'yyyyMMdd_HHmmss').sql"

# Crear carpeta si no existe
if (-not (Test-Path -Path $BackupDir)) {
    New-Item -ItemType Directory -Path $BackupDir | Out-Null
}

# Comando mysqldump (--no-data = solo estructura)
try {
    & mysqldump `
        --host=$MySQLHost `
        --port=$MySQLPort `
        --user=$MySQLUser `
        --password=$MySQLPassword `
        --no-data `
        --routines `
        --triggers `
        --databases $DatabaseName `
        --result-file=$BackupPath `
        --no-tablespaces `
        --skip-lock-tables

    Write-Host "✅ Backup de ESTRUCTURA completado: $BackupPath" -ForegroundColor Green
} catch {
    Write-Host "❌ Error: $_" -ForegroundColor Red
}