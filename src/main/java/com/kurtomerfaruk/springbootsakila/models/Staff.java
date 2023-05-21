package com.kurtomerfaruk.springbootsakila.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Table(name = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Short staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne
    private Address addressId;
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne
    private Store storeId;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "managerStaffId", fetch = FetchType.LAZY)
    private Store store;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId", fetch = FetchType.LAZY)
//    private List<Rental> rentalList;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId", fetch = FetchType.LAZY)
//    private List<Payment> paymentList;

}
