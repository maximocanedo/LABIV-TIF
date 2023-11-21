"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
import {
    ProvinceDropdownList,
    LocaltiesDropdownList,
    CountriesDropdownList,
    MDCDropdownList,
    TwoLineListElement,
    ClientListItemElement,
    MDCCheckbox
}
    from './components/index.js';


const chkDisabled = new MDCCheckbox('Mostrar usuarios deshabilitados');
const provinciaDDL = new ProvinceDropdownList('Filtrar por provincia', 'Todas las provincias');
const localidadesDDL = new LocaltiesDropdownList('Filtrar por localidad', provinciaDDL, 'Todas las localidades');
const paisesDDL = new CountriesDropdownList('Filtrar por nacionalidad', "Todos los países");
const sexoDDL = new MDCDropdownList("Filtrar por sexo", [
    { text: "Todos los géneros", value: "A", selected: true },
    { text: "Masculino", value: "M" },
    { text: "Femenino", value: "F"}
]);
document.querySelector("#selects").append(
    chkDisabled.getElement(), sexoDDL.getElement(), provinciaDDL.getElement(), localidadesDDL.getElement(), paisesDDL.getElement()
);
console.log(material.mdc);
const getData = async (data = null) => {
    let ue = "?";
    if (data != null) {
        const filteredData = Object.fromEntries(
            Object.entries(data).filter(([key, value]) => value !== null)
        );
        ue += new URLSearchParams(filteredData).toString();
    }
    return fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/clients" + ue, {
        headers: auth.AUTH_HEADER,
        method: "GET",
    })
        .then((raw) => {
            if (raw.status != 200) {
                material.showSnackbar(
                    "Hubo un problema al intentar cargar los datos. "
                );
            }
            return raw.json();
        })
        .then((json) => {
            return json;
        })
        .catch((err) => {
            console.error(err);
            material.showSnackbar(err);
        });
};

const searchEvent = async (e) => {
    const q = document.searchForm.q.value;
    const status = !document.searchForm.showInactive.checked;
    const province =
        document.searchForm.provincias.value == "-1"
            ? null
            : document.searchForm.provincias.value;
    const localty =
        document.searchForm.localidades.value == "-1"
            ? null
            : document.searchForm.localidades.value;
    const sex =
        document.searchForm.sexo.value == "M" ||
        document.searchForm.sexo.value == "F"
            ? document.searchForm.sexo.value
            : null;
    const country =
        document.searchForm.pais.value == "-1"
            ? null
            : document.searchForm.pais.value;
    const data = {
        q,
        status,
        province,
        localty,
        sex,
        country,
    };
    const fetchedData = await getData(data);
    fillData(fetchedData);
};


const clientListContainer = document.querySelector(".client-list-container");
const listaClientes = new TwoLineListElement();
console.log(listaClientes);
clientListContainer.append(listaClientes.getElement());

const fillData = (data) => {
    material.loadElements();
    listaClientes.clear();
    data.listReturned.forEach((element) => {
        const listItem = new ClientListItemElement(element);
        listItem.handleClick(e => console.log(e));
        listaClientes.add(listItem);
    });
};

(async () => {
    material.loadElements();
    const actualUser = await auth.allowAdmin();
    const data = await getData();
    document.searchForm.searchBtn.addEventListener("click", async () => {
        await searchEvent();
    });
    fillData(data);
})();
