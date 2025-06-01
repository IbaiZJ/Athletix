- Tracking-eko perfileko irudia konpondu
- Login egiterakoan behar duen lekura berbidali
- Tracking estadistikak responsive


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Athletix - Eventos Deportivos</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: '#3B82F6',
                        secondary: '#10B981',
                        danger: '#EF4444',
                        warning: '#F59E0B',
                    },
                    fontFamily: {
                        sans: ['Inter', 'sans-serif'],
                    },
                }
            }
        }
    </script>
</head>
<body class="bg-gray-50 font-sans">
    <!-- Navbar -->
    <nav class="bg-white shadow-sm">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between h-16">
                <div class="flex items-center">
                    <span class="text-xl font-bold text-primary">Athletix</span>
                </div>
                <div class="flex items-center space-x-4">
                    <a href="#" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Inicio</a>
                    <a href="#" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Mis Eventos</a>
                    <a href="#" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-100">Crear Evento</a>
                    <button class="bg-primary text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-blue-600">Iniciar Sesión</button>
                </div>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <div class="bg-primary text-white py-16">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <h1 class="text-4xl font-bold mb-4">Encuentra tu próximo desafío deportivo</h1>
            <p class="text-xl mb-8">Participa en eventos increíbles y conecta con otros atletas</p>
            <div class="max-w-md mx-auto">
                <div class="flex">
                    <input type="text" placeholder="Buscar eventos..." class="flex-grow px-4 py-3 rounded-l-lg focus:outline-none text-gray-800">
                    <button class="bg-secondary hover:bg-green-600 text-white px-6 py-3 rounded-r-lg font-medium">Buscar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <!-- Filters -->
        <div class="mb-8 flex flex-wrap items-center justify-between gap-4">
            <h2 class="text-2xl font-bold text-gray-800">Próximos Eventos</h2>
            <div class="flex space-x-2">
                <select class="border-gray-300 rounded-md px-3 py-2 bg-white text-gray-700 shadow-sm focus:outline-none focus:ring-primary focus:border-primary">
                    <option>Todos los niveles</option>
                    <option>Principiante</option>
                    <option>Intermedio</option>
                    <option>Avanzado</option>
                </select>
                <select class="border-gray-300 rounded-md px-3 py-2 bg-white text-gray-700 shadow-sm focus:outline-none focus:ring-primary focus:border-primary">
                    <option>Cualquier distancia</option>
                    <option>0-5 km</option>
                    <option>5-10 km</option>
                    <option>10+ km</option>
                </select>
            </div>
        </div>

        <!-- Events Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <!-- Event Card 1 -->
            <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                <div class="h-48 bg-blue-100 flex items-center justify-center">
                    <span class="text-gray-500">Imagen del evento</span>
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-2">
                        <h3 class="text-xl font-bold text-gray-800">Carrera de Montaña</h3>
                        <span class="bg-green-100 text-green-800 text-xs px-2 py-1 rounded-full">Intermedio</span>
                    </div>
                    <p class="text-gray-600 mb-4">Una emocionante carrera por senderos de montaña con vistas espectaculares.</p>
                    
                    <div class="space-y-3 mb-4">
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                            </svg>
                            <span>Valle de Tena, Huesca</span>
                        </div>
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                            </svg>
                            <span>15 Junio 2023 · 09:00</span>
                        </div>
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"></path>
                            </svg>
                            <span>12.5 km</span>
                        </div>
                    </div>
                    
                    <div class="flex justify-between items-center">
                        <span class="text-sm text-gray-500">25 participantes</span>
                        <button class="bg-primary hover:bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium">Ver Detalles</button>
                    </div>
                </div>
            </div>

            <!-- Event Card 2 -->
            <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                <div class="h-48 bg-green-100 flex items-center justify-center">
                    <span class="text-gray-500">Imagen del evento</span>
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-2">
                        <h3 class="text-xl font-bold text-gray-800">Triatlón Urbano</h3>
                        <span class="bg-yellow-100 text-yellow-800 text-xs px-2 py-1 rounded-full">Avanzado</span>
                    </div>
                    <p class="text-gray-600 mb-4">Natación, ciclismo y carrera por el centro de la ciudad.</p>
                    
                    <div class="space-y-3 mb-4">
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                            </svg>
                            <span>Parque del Retiro, Madrid</span>
                        </div>
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                            </svg>
                            <span>22 Julio 2023 · 08:30</span>
                        </div>
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"></path>
                            </svg>
                            <span>5 km natación · 20 km bici · 10 km carrera</span>
                        </div>
                    </div>
                    
                    <div class="flex justify-between items-center">
                        <span class="text-sm text-gray-500">42 participantes</span>
                        <button class="bg-primary hover:bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium">Ver Detalles</button>
                    </div>
                </div>
            </div>

            <!-- Event Card 3 -->
            <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                <div class="h-48 bg-purple-100 flex items-center justify-center">
                    <span class="text-gray-500">Imagen del evento</span>
                </div>
                <div class="p-6">
                    <div class="flex justify-between items-start mb-2">
                        <h3 class="text-xl font-bold text-gray-800">Yoga en la Playa</h3>
                        <span class="bg-blue-100 text-blue-800 text-xs px-2 py-1 rounded-full">Principiante</span>
                    </div>
                    <p class="text-gray-600 mb-4">Sesión de yoga al amanecer en la playa de la Barceloneta.</p>
                    
                    <div class="space-y-3 mb-4">
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                            </svg>
                            <span>Playa de la Barceloneta, Barcelona</span>
                        </div>
                        <div class="flex items-center text-gray-700">
                            <svg class="w-5 h-5 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                            </svg>
                            <span>Cada domingo · 07:00</span>
                        </div>
                    </div>
                    
                    <div class="flex justify-between items-center">
                        <span class="text-sm text-gray-500">15 participantes</span>
                        <button class="bg-primary hover:bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium">Ver Detalles</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-gray-800 text-white py-12">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="grid grid-cols-1 md:grid-cols-4 gap-8">
                <div>
                    <h3 class="text-lg font-semibold mb-4">Athletix</h3>
                    <p class="text-gray-400">Conectando atletas y organizadores de eventos deportivos.</p>
                </div>
                <div>
                    <h3 class="text-lg font-semibold mb-4">Eventos</h3>
                    <ul class="space-y-2">
                        <li><a href="#" class="text-gray-400 hover:text-white">Próximos eventos</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Eventos pasados</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Crear evento</a></li>
                    </ul>
                </div>
                <div>
                    <h3 class="text-lg font-semibold mb-4">Comunidad</h3>
                    <ul class="space-y-2">
                        <li><a href="#" class="text-gray-400 hover:text-white">Foros</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Grupos</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Blog</a></li>
                    </ul>
                </div>
                <div>
                    <h3 class="text-lg font-semibold mb-4">Legal</h3>
                    <ul class="space-y-2">
                        <li><a href="#" class="text-gray-400 hover:text-white">Términos</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Privacidad</a></li>
                        <li><a href="#" class="text-gray-400 hover:text-white">Cookies</a></li>
                    </ul>
                </div>
            </div>
            <div class="border-t border-gray-700 mt-8 pt-8 text-center text-gray-400">
                <p>© 2023 Athletix. Todos los derechos reservados.</p>
            </div>
        </div>
    </footer>
</body>
</html>