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
@Table(name = "store")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Short storeId;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId", fetch = FetchType.LAZY)
//    private List<Staff> staffList;
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne
    private Address addressId;
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id")
    @OneToOne
    private Staff managerStaffId;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId", fetch = FetchType.LAZY)
//    private List<Inventory> inventoryList;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId", fetch = FetchType.LAZY)
//    private List<Customer> customerList;

}
