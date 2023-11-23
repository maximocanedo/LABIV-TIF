"use strict";
import * as material from "../../res/controller/mdc.controller.js";
import * as auth from "../../res/data/auth.js";
import {
    ProvinceDropdownList,
    LocaltiesDropdownList,
    CountriesDropdownList,
    MDCDropdownList,
    TwoLineListElement,
    TwoLineListItemElement,
    ClientListItemElement,
    MDCCheckbox, ClientDetailsView
}
    from '../../res/controller/components/index.js';

console.log(material);

const filterDetails = document.querySelector("#filterDetails");
const chkDisabled = new MDCCheckbox('Mostrar usuarios deshabilitados');
const provinciaDDL = new ProvinceDropdownList('Filtrar por provincia', 'Todas las provincias');
const localidadesDDL = new LocaltiesDropdownList('Filtrar por localidad', provinciaDDL, 'Todas las localidades');
const paisesDDL = new CountriesDropdownList('Filtrar por nacionalidad', "Todos los países");
const sexoDDL = new MDCDropdownList("Filtrar por sexo", [
    { text: "Todos los géneros", value: "A", selected: true },
    { text: "Masculino", value: "M" },
    { text: "Femenino", value: "F"}
]);
const getFilterValues = () => ({
    includeDisabled: chkDisabled.materialElement.checked,
    provincia: (() => {
        return (
            provinciaDDL.materialElement.foundation.getSelectedIndex() < 1
                ? {text: null, value: null}
                : { text: provinciaDDL.selectedText.innerText, value: provinciaDDL.getValue() }
        );})(),
    localidad:  (() => { return (localidadesDDL.materialElement.foundation.getSelectedIndex() < 1 ? {text: null, value: null} : { text: localidadesDDL.selectedText.innerText, value: localidadesDDL.getValue() } )})(),
    pais:  (() => { return (paisesDDL.materialElement.foundation.getSelectedIndex() < 1 ? {text: null, value: null} : { text: paisesDDL.selectedText.innerText, value: paisesDDL.getValue() } )})(),
    sexo:  (() => { return (sexoDDL.materialElement.foundation.getSelectedIndex() < 1  ? {text: null, value: null} : { text: sexoDDL.selectedText.innerText, value: sexoDDL.getValue() } )})()
});
const updateFilterDetails = (event, details) => {
    let data = getFilterValues();
    let str = "";
    str = `${data.sexo.text == null ? "" : data.sexo.text } ${data.localidad.text != null || data.provincia.text != null ? (data.sexo.text != null ? ", de " : "De ") : ""} ${data.localidad.text != null ? data.localidad.text + " (" + data.provincia.text + ")" : (data.provincia.text != null ? data.provincia.text : "")}`;
    str += `${data.pais.text != null ? "; Nacional de " + data.pais.text : ""}`;
    filterDetails.innerText = str;
}
provinciaDDL.onChange(updateFilterDetails);
localidadesDDL.onChange(updateFilterDetails);
paisesDDL.onChange(updateFilterDetails);
sexoDDL.onChange(updateFilterDetails);





document.querySelector("#mostrarFiltrosBtn").addEventListener('click', e => {
    let el = document.querySelector("#selects");
    if(el.classList.contains("hidden")) el.classList.remove("hidden");
    else el.classList.add("hidden");
});
document.querySelector("#selects").append(
    chkDisabled.getElement(), sexoDDL.getElement(), provinciaDDL.getElement(), localidadesDDL.getElement(), paisesDDL.getElement()
);
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
    const fdata = getFilterValues();
    const status = !fdata.includeDisabled;
    const province = fdata.provincia.value;
    const localty = fdata.localidad.value;
    const sex = fdata.sexo.value != null && (fdata.sexo.value == "M" || fdata.sexo.value == "F") ? fdata.sexo.value : null;
    const country = fdata.pais.value;
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

const clientDetailViewContainer = document.querySelector("#clientDetailViewContainer");

const clientListContainer = document.querySelector(".client-list-container");
const listaClientes = new TwoLineListElement();
clientListContainer.append(listaClientes.getElement());

const fillData = (data) => {
    material.loadElements();
    listaClientes.clear();
    data.listReturned.forEach((element) => {
        const listItem = new ClientListItemElement(element);
        listItem.handleClick(e => {
            console.log(e);
            let clientData = e.payload;

            let el = new ClientDetailsView(clientData);
            clientDetailViewContainer.innerHTML = "";
            clientDetailViewContainer.append(el.getElement());
        });
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
