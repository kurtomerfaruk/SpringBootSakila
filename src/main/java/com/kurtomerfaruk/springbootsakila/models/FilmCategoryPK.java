package com.kurtomerfaruk.springbootsakila.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 10:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class FilmCategoryPK implements Serializable {

    @Serial
    private static final long serialVersionUID = -6830645206062070512L;
    @Column(name = "film_id")
    private short filmId;
    @Column(name = "category_id")
    private short categoryId;

}
