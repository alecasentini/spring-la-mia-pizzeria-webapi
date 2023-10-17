<script setup>
import { onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0"

const pizza = ref(null);
const route = useRoute();

watch(route, () => {
    getPizzaDetails();
}, { immediate: true });

function getPizzaDetails() {
    const id = route.params.id;
    axios.get(API_URL + '/' + id)
        .then(res => {
            const data = res.data;
            pizza.value = data;
        })
        .catch(err => console.log(err));
}
</script>

<template>
    <div class="container" v-if="pizza">
        <h1 class="py-3">{{ pizza.nome }}</h1>
        <div style="width: 500px; height: 500px;">
            <img class="card-img-top" :src="pizza.foto" :alt="pizza.nome"
                style="width: 100%; height: 100%;object-fit: cover;" />
            <div class="card-body py-3">
                <p class="card-text py-3 mb-0"><strong>Descrizione:</strong> {{ pizza.descrizione }}</p>
                <p class="card-text py-3">
                    <strong>Ingredienti:</strong>
                    <span v-for="(ingredient, index) in pizza.ingredients" :key="index">{{ ingredient.name }}<span
                            v-if="index !== pizza.ingredients.length - 1">, </span></span>
                </p>
                <!-- Prezzo -->
                <p class="card-text">
                    <span v-if="pizza.offerteSpeciali.length > 0">
                        <strong>Prezzo:</strong> <s>{{ pizza.prezzo }}€</s>
                        {{ pizza.prezzo - (pizza.prezzo * pizza.offerteSpeciali[0].scontoPercentuale / 100) }}
                    </span>
                    <span v-else>
                        Prezzo: {{ pizza.prezzo }}
                    </span><span>€</span>
                </p>

                <!-- Elenco delle offerte speciali -->
                <h2 class="py-3">Offerte speciali:</h2>
                <div class="py-3" v-if="pizza.offerteSpeciali.length === 0">
                    Nessuna offerta speciale disponibile.
                </div>
                <ul v-else>
                    <li v-for="(offerta, index) in pizza.offerteSpeciali" :key="index">
                        {{ offerta.titolo }}: dal {{ offerta.dataInizio }} al {{ offerta.dataFine }} con uno sconto del {{
                            offerta.scontoPercentuale }}%
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>
