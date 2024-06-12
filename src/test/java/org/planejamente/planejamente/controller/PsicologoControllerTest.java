package org.planejamente.planejamente.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.planejamente.planejamente.controller.PsicologoController;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoExibir;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.service.PsicologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
     class PsicologoControllerTest {

        @Mock
        private PsicologoService psicologoService;

        @InjectMocks
        private PsicologoController psicologoController;

        private PsicologoDtoConsultar psicologoDto;

        @BeforeEach
        public void setUp() {
            psicologoDto = new PsicologoDtoConsultar();
        }

        @Test
        public void testListar() {
            List<PsicologoDtoConsultar> lista = new ArrayList<>();
            lista.add(psicologoDto);

            when(psicologoService.listarTodos()).thenReturn(lista);

            ResponseEntity<List<PsicologoDtoConsultar>> response = psicologoController.listar();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(lista, response.getBody());
        }

        @Test
        public void testBuscarPorId() {
            UUID id = UUID.randomUUID();
            Psicologo psicologoRetornado = new Psicologo();


//            when(psicologoService.buscarPorId(id)).thenReturn(psicologoRetornado)


            ResponseEntity<PsicologoDtoExibir> response = psicologoController.buscarPorId(id);


            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void testListarPorGenero() {
            String genero = "Feminino";

            List<PsicologoDtoConsultar> lista = new ArrayList<>();
            lista.add(psicologoDto);

            when(psicologoService.listarPorGenero(genero)).thenReturn(lista);

            ResponseEntity<List<PsicologoDtoConsultar>> response = psicologoController.listarPorGenero(genero);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(lista, response.getBody());
        }

        @Test
        public void testListarOrdenado() {
            List<PsicologoDtoConsultar> listaOrdenada = new ArrayList<>();
            listaOrdenada.add(psicologoDto);

            when(psicologoService.listarOrdenado()).thenReturn(listaOrdenada);

            ResponseEntity<List<PsicologoDtoConsultar>> response = psicologoController.listarOrdenado();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(listaOrdenada, response.getBody());
        }

        @Test
        public void testListarEmMatriz() {
            int colunas = 3;

            List<List<PsicologoDtoConsultar>> matriz = new ArrayList<>();
            List<PsicologoDtoConsultar> linha = new ArrayList<>();
            linha.add(psicologoDto);
            matriz.add(linha);

            when(psicologoService.listarEmMatriz(colunas)).thenReturn(matriz);

            ResponseEntity<List<List<PsicologoDtoConsultar>>> response = psicologoController.listarEmMatriz(colunas);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(matriz, response.getBody());
        }
    }
