package org.example;

import Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = entityManagerFactory.createEntityManager();
        System.out.println("en marcha Facundo");

        try{
            em.getTransaction().begin();

            Cliente cliente = Cliente.builder()
                    .nombre("facundo")
                    .apellido("Caina")
                    .dni(45024102)
                    .build();

            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Talcahuano")
                    .numero(123)
                    .build();

            Factura factura = Factura.builder()
                    .fecha("04/09/2024")
                    .numero(34)
                    .total(23000)
                    .build();

            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();
            Categoria bebidas = Categoria.builder()
                    .denominacion("Bebidas")
                    .build();
            Articulo articulo1 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Leche en polvo")
                    .precio(10000)
                    .build();
            Articulo articulo2 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Brilla pisos")
                    .precio(20000)
                    .build();
            Articulo articulo3 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("CocaCola 1.5L")
                    .precio(35000)
                    .build();

            cliente.setDomicilio(domicilio);
            domicilio.setCliente(cliente);
            factura.setCliente(cliente);

            articulo1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo1);

            articulo2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo2);

            articulo3.getCategorias().add(bebidas);
            bebidas.getArticulos().add(articulo3);

            DetalleFactura detalle1= DetalleFactura.builder().build();
            detalle1.setArticulo(articulo1);
            detalle1.setCantidad(3);
            detalle1.setSubtotal(3000);

            articulo1.getDetalle().add(detalle1);
            factura.getDetalle().add(detalle1);
            detalle1.setFactura(factura);

            DetalleFactura detalle2 = DetalleFactura.builder().build();
            detalle2.setArticulo(articulo2);
            detalle2.setCantidad(4);
            detalle2.setSubtotal(4000);

            articulo2.getDetalle().add(detalle2);
            factura.getDetalle().add(detalle2);
            detalle2.setFactura(factura);

            DetalleFactura detalle3 = DetalleFactura.builder().build();
            detalle3.setArticulo(articulo3);
            detalle3.setCantidad(4);
            detalle3.setSubtotal(5000);

            articulo3.getDetalle().add(detalle3);
            factura.getDetalle().add(detalle3);
            detalle3.setFactura(factura);

            factura.setTotal(45000);

            em.persist(cliente);

            em.flush();

            em.getTransaction().commit();

        }catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
        entityManagerFactory.close();
    }

    }
