document.addEventListener("DOMContentLoaded", function () {
    const mapContainer = document.getElementById("map");
    if (!mapContainer) return;

    let selectedLat = 43.1157;
    let selectedLng = -2.4129;

    const map = L.map("map", {
        center: [selectedLat, selectedLng],
        zoom: 13,
    });

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        attribution: "",
    }).addTo(map);

    let marker = L.marker([selectedLat, selectedLng])
        .addTo(map)
        .bindPopup("Ubicación predeterminada: Bergara")
        .openPopup();

    map.on("click", function (e) {
        selectedLat = e.latlng.lat.toFixed(6);
        selectedLng = e.latlng.lng.toFixed(6);

        marker.setLatLng(e.latlng).update();
        marker.setPopupContent(`Ubicación seleccionada:<br>Lat: ${selectedLat}, Lng: ${selectedLng}`).openPopup();

        document.getElementById("lat").value = selectedLat;
        document.getElementById("lng").value = selectedLng;
    });
});
