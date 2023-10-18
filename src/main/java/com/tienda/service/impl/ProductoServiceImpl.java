
package com.tienda.service.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl
        implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activo) {
        var productos = productoDao.findAll();
        if (activo) {
            
            //Eliminaria de la lista las productos inactivas 

          productos.removeIf(e-> !e.isActivo());
        }

        return productos;
    }

    @Override
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto) {
        
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    public void delete(Producto producto) {
        
        productoDao.delete(producto);
      
    }

    @Override
    public void save(Producto producto) {
      
        productoDao.save(producto);
        
    }

}








