document.addEventListener('DOMContentLoaded', function () {
  const mapContainer = document.getElementById('map');
  if (!mapContainer) return;

  // Usa las variables globales definidas en el HTML
  var lat = typeof eventLatitude !== 'undefined' ? eventLatitude : 19.4326;
  var lng = typeof eventLongitude !== 'undefined' ? eventLongitude : -99.1332;

  var map = L.map('map', {
    center: [lat, lng],
    zoom: 13,
    zoomControl: false,
    dragging: false,
    scrollWheelZoom: false,
    doubleClickZoom: false,
    boxZoom: false,
    keyboard: false,
    tap: false,
    touchZoom: false,
  });

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '',
  }).addTo(map);

  L.marker([lat, lng]).addTo(map).bindPopup('Ubicación del evento').openPopup();
});
