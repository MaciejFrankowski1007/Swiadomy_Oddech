document.addEventListener('DOMContentLoaded', function () {
    const useMyLocationBtn = document.getElementById('use-my-location');

    if (useMyLocationBtn) {
        useMyLocationBtn.addEventListener('click', function () {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    const latitude = position.coords.latitude;
                    const longitude = position.coords.longitude;

                    fetch(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${latitude},${longitude}&key=AIzaSyDOl9J7LET2d0WngD7WNEsn0FIbkR2VzHI`)
                        .then(response => response.json())
                        .then(data => {
                            if (!data.results || data.results.length === 0) {
                                throw new Error('Nie znaleziono wyniku, dla podanej lokalizacji');
                            }
                            const city = data.results[0].address_components.find(c => c.types.includes('locality')).long_name;
                            const state = data.results[0].address_components.find(c => c.types.includes('administrative_area_level_1')).long_name;
                            const country = data.results[0].address_components.find(c => c.types.includes('country')).short_name;

                            const airQualityUrl = `/getAirQualityByCity?city=${city}`;
                            console.log("Requesting AirQuality API with URL: ", airQualityUrl);

                            fetch(airQualityUrl)
                                .then(response => {
                                    if (!response.ok) {
                                        console.error(`Błąd: Status ${response.status} - ${response.statusText}`);
                                        throw new Error(`Błąd: Status ${response.statusText}`);
                                    }
                                    return response.json();
                                })
                                .then(airQualityData => {
                                    console.log(airQualityData);
                                    const queryParams = new URLSearchParams({
                                        city: city,
                                        state: state,
                                        country: country,
                                        airQualityData: JSON.stringify(airQualityData)
                                    }).toString();
                                    window.location.href = `result.jsp?${queryParams}`;
                                })
                                .catch(error => console.error('Błąd:', error));
                        })
                        .catch(error => console.error('Problem z Geolokacją:', error));
                });
            } else {
                alert("Problem z Geolokacją.");
            }
        });
    }
});
