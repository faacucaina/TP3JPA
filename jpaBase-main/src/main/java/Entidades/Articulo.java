package Entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor

@Entity
@Table(name = "Articulo")
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private String denominacion;
    private int precio;


    @OneToMany(mappedBy = "articulo")
    private Set<DetalleFactura> detalle = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name= "ArticuloCategoria",
            joinColumns =  @JoinColumn( name = "articulo_id"),
            inverseJoinColumns = @JoinColumn (name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();
}


