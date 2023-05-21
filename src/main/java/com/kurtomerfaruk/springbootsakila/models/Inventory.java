package com.kurtomerfaruk.springbootsakila.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 10:58
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    @ManyToOne
    private Film filmId;
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne
    private Store storeId;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryId", fetch = FetchType.LAZY)
//    private List<Rental> rentalList;

}
