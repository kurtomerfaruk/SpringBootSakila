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
public class FilmActorPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 3443396649127907916L;
    @Column(name = "actor_id")
    private short actorId;
    @Column(name = "film_id")
    private short filmId;

}
