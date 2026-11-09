package com.tienda.api.service;

import com.tienda.api.exception.ProductoInvalidoException;
import com.tienda.api.exception.ProductoNoEncontradoException;
import com.tienda.api.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    // Colección temporal en memoria
    private final List<Producto> productos = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductoService() {
        // Dos productos iniciales para pruebas
        productos.add(new Producto(idGenerator.getAndIncrement(), "Laptop Lenovo", 750.0, 10));
        productos.add(new Producto(idGenerator.getAndIncrement(), "Mouse Inalambrico", 25.0, 30));
    }

    // Listar todos los productos
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    // Obtener por ID o lanzar excepción si no existe
    public Producto obtenerPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto con el ID: " + id));
    }

    // Registrar producto validando precio y stock
    public Producto registrar(Producto producto) {
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio del producto debe ser estrictamente mayor a 0.");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock no puede ser un valor negativo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ProductoInvalidoException("El nombre del producto es obligatorio.");
        }

        producto.setId(idGenerator.getAndIncrement());
        productos.add(producto);
        return producto;
    }

    // Eliminar producto o lanzar excepción si no existe
    public void eliminar(Long id) {
        Producto productoExistente = obtenerPorId(id);
        productos.remove(productoExistente);
    }
}