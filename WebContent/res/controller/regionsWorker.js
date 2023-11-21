import * as provinces from "../data/provinces.js";
import * as countries from "../data/countries.js";
import * as localties from "../data/localties.js";

self.addEventListener('message', async (e) => {
    const { action, data } = e.data;

    if (action === 'getProvinces') {
        const provincias = await provinces.getProvinces();
        self.postMessage({ action: 'provincesLoaded', data: provincias });
    } else if (action === 'getCountries') {
        const paises = await countries.getCountries();
        self.postMessage({ action: 'countriesLoaded', data: paises });
    } else if (action === 'getLocalities') {
        const locs = await localties.getLocalties(data);
        self.postMessage({ action: 'localitiesLoaded', data: locs });
    }
});