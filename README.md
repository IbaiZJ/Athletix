# Athletix

Athletix is a modern social fitness tracking platform built with Spring Boot that allows users to track their fitness activities, connect with friends, join groups, participate in challenges, and manage events.

## Features

- User authentication and profile management
- Activity tracking and monitoring
- Social networking (friends and groups)
- Real-time chat functionality
- Event creation and management
- Fitness challenges
- Notifications system
- Multi-language support

## Prerequisites

- Java 21 or higher
- Maven
- MySQL Database
- Node.js and npm (for frontend assets)

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/IbaiZJ/Athletix.git
cd Athletix
```

2. Configure the database:
   - Create a MySQL database
   - Import the initial schema from `db/Athletix.sql`
   - Update `src/main/resources/application.properties` with your database credentials

3. Install frontend dependencies:
```bash
npm install
```

4. Build and run the application:
```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`

## Project Structure

```
Athletix/
├── src/main/
│   ├── java/com/athletix/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # MVC Controllers
│   │   ├── model/           # Entity classes
│   │   ├── service/         # Business logic
│   │   └── enums/           # Enumerations
│   └── resources/
│       ├── templates/       # Thymeleaf templates
│       └── static/          # Static assets
├── db/                      # Database scripts
└── scripts/                 # Utility scripts
```

## Database Backup

The project includes PowerShell scripts for database backup:
- `scripts/dbBackup.ps1`: Full database backup
- `scripts/dbDataBackup.ps1`: Data-only backup
- `scripts/dbSchemaBackup.ps1`: Schema-only backup

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Set Up

### Descargara Java 21 o mas: 
https://www.oracle.com/es/java/technologies/downloads/

### Descargar Node js:
 https://nodejs.org/en/download

### Habilitar la ejecucion de scripts
Ejecutar como administrador powershell

```bash
 Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
```
    