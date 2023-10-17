<script setup>
import { onMounted, ref, computed } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0"

const pizze = ref(null);
const searchQuery = ref('');

function getAllPizzas() {
  axios.get(API_URL)
    .then(res => {
      const data = res.data;
      pizze.value = data;
    })
    .catch(err => console.log(err));
}
onMounted(() => {
  getAllPizzas();
});

const filteredPizzas = computed(() => {
  if (!searchQuery.value) {
    return pizze.value;
  }
  return pizze.value.filter(pizza => pizza.nome.toLowerCase().includes(searchQuery.value.toLowerCase()));
});
</script>

<template>
  <main>
    <div class="container">

      <h1>Pizze</h1>
      <div class="py-3">
        <form @submit.prevent>
          <div class="w-50">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Cerca pizza per nome" v-model="searchQuery">
              <div class="input-group-append ps-5">
                <button class="btn btn-outline-secondary" type="submit">Cerca</button>
              </div>
            </div>
          </div>
        </form>
      </div>

      <div class="py-3 text-center" v-if="filteredPizzas && filteredPizzas.length === 0">
        <p>Non ci sono risultati</p>
      </div>

      <div class="py-3" v-if="filteredPizzas && filteredPizzas.length > 0">
        <div class="card-deck d-flex flex-wrap gap-5">
          <div class="card" :class="{ 'border border-3 border-primary': pizza.offerteSpeciali.length > 0 }"
            style="width: 250px; height: 425px;" v-for="pizza in filteredPizzas" :key="pizza.id">
            <div style="height: 150px;">
              <img class="card-img-top" :src="pizza.foto" :alt="pizza.nome"
                style="width: 100%; height: 100%;object-fit: cover;" />
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                v-if="pizza.offerteSpeciali.length > 0">
                OFFERTA
              </span>
            </div>
            <div class="card-body" style="height: 275px;">
              <h5 class="card-title text-center">{{ pizza.nome }}</h5>
              <p class="card-text">{{ pizza.descrizione }}</p>
              <p class="card-text">
                <span v-if="pizza.offerteSpeciali.length > 0">
                  Prezzo: <s>{{ pizza.prezzo }}</s>€
                  {{ pizza.prezzo - (pizza.prezzo * pizza.offerteSpeciali[0].scontoPercentuale / 100) }}€
                </span>
                <span v-else>
                  Prezzo: {{ pizza.prezzo }} €
                </span>
              </p>
              <button @click="showDetails(pizza.id)" class="btn btn-primary">Dettagli</button>
              <button @click="editPizza(pizza.id)" class="btn btn-primary ms-1">Modifica</button>
              <button @click="deletePizza(pizza.id)" class="btn btn-danger mt-1">Elimina</button>
            </div>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>
