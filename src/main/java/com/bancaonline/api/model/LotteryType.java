package com.bancaonline.api.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Lottery type.
 */
@Entity
@Table(name = "lottery_type")
public class LotteryType  implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -7941769011539363180L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "lottery_type_id")
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    public LotteryType(){}


    /**
     * Instantiates a new Lottery type.
     *
     * @param id the id
     */
    public LotteryType(Long id){
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
