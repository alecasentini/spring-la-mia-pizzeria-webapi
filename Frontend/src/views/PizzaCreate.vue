<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0"

const pizza = ref({
    nome: '',
    descrizione: '',
    foto: '',
    prezzo: 0
});
const router = useRouter();

function createPizza() {
    axios.post(API_URL, pizza.value)
        .then(res => {
            console.log(res.data);
            alert('Pizza creata con successo!');
            router.push('/');
        })
        .catch(err => console.log(err));
}
</script>

<template>
    <div class="container py-3">
        <h3>Crea una Pizza</h3>
        <form @submit.prevent="createPizza">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" v-model="pizza.nome" class="form-control" id="nome">
            </div>

            <div class="mb-3">
                <label for="descrizione" class="form-label">Descrizione</label>
                <input type="text" v-model="pizza.descrizione" class="form-control" id="descrizione">
            </div>

            <div class="mb-3">
                <label for="foto" class="form-label">URL Foto</label>
                <input type="text" v-model="pizza.foto" class="form-control" id="foto">
            </div>

            <div class="mb-3">
                <label for="prezzo" class="form-label">Prezzo</label>
                <input type="number" step="0.01" v-model.number="pizza.prezzo" class="form-control" id="prezzo">
            </div>

            <button type="submit" class="btn btn-primary">Crea Pizza</button>
        </form>
    </div>
</template>