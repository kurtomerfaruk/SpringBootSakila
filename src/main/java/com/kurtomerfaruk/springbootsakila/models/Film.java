package com.kurtomerfaruk.springbootsakila.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "film")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    @Temporal(TemporalType.DATE)
    private Date releaseYear;
    @Column(name = "rental_duration")
    private short rentalDuration;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Column(name = "rating")
    private String rating;
    @Column(name = "special_features")
    private String specialFeatures;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", fetch = FetchType.LAZY)
//    private List<FilmCategory> filmCategoryList;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", fetch = FetchType.LAZY)
//    private List<FilmActor> filmActorList;
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    @ManyToOne
    private Language languageId;
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
    @ManyToOne
    private Language originalLanguageId;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmId", fetch = FetchType.LAZY)
//    private List<Inventory> inventoryList;

}
