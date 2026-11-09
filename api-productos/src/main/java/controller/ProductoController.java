package com.tienda.api.controller;

import com.tienda.api.model.Producto;
import com.tienda.api.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    // Inyección de dependencias por constructor (sin usar "new")
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // 1. Listar todos -> HTTP 200
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // 2. Obtener por ID -> HTTP 200
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    // 3. Registrar un producto nuevo -> HTTP 201
    @PostMapping
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registrar(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // 4. Eliminar producto por ID -> HTTP 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}