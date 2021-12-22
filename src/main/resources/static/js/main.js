// Grabbing data from form
const pokemonContainer = document.querySelector(".pokemon-container");
const formEl = document.querySelector("form");
const inputEl = document.querySelector("input[type=text]");

// listen for user event
formEl.addEventListener("submit", (e) => {
    e.preventDefault();
    pokemonContainer.innterHtml = "";
    getPokemon(inputEl.value);
});

//async function
async function getPokemon(name = "ditto") {
    const res= await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);
    const pokemon = await res.json();
    const pokemonEl = document.createElement("div");

    pokemonEl.classList.add("pokemon");
    pokemonEl.innerHTML = `<div class = "info">
    <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/${pokemon.id}.svg" width = "200">
    <h2>${pokemon.name} </h2>
    </div>

    <div class="stats">${pokemon.stats.map((stat) => {
        return `<p>${stat.stat.name}: ${stat.base_stat}</p>`;
    }).join("")}
    </div>
    
    <div class="abilities">${pokemon.abilities.map((ability) => {
        return `<p>${ability.ability.name}</p>`;
    }).join("")}
    </div>
    `;
    pokemonContainer.appendChild(pokemonEl);
   
}
// RUN IT ~~~~~~~~~~~~~~~~~~!!!
getPokemon();