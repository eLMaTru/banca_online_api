package com.bancaonline.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Advertisement Type class.
 */
@Entity
@Table(name = "advertisement_type")
public class AdvertisementType implements Serializable {

    public AdvertisementType() {

    }

    public AdvertisementType(Long id) {
        this.id = id;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "advertisement_type_id")
    private Long id;

    @Column(length = 55)
    private String name;

    @Column(length = 300)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The enum Type.
     */
    public enum Type {

        /**
         * TOP ADVERTISEMENT type.
         */
        TOP_ADVERTISEMENT(1),

        /**
         * MARQUEE ADVERTISEMENT type.
         */
        MARQUEE_ADVERTISEMENT(2);

        private final long id;

        Type(int id) {
            this.id = id;
        }

        /**
         * As array TOP ADVERTISEMENT long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayEnabled() {
            return new Long[] { TOP_ADVERTISEMENT.getId() };
        }

        /**
         * As array MARQUEE ADVERTISEMENT long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayDisabled() {
            return new Long[] { MARQUEE_ADVERTISEMENT.getId() };
        }

        /**
         * Gets id.
         *
         * @return the id
         */
        public long getId() {
            return id;
        }

        /**
         * To status status.
         *
         * @return the status
         */
        public Status toStatus() {
            return new Status(id);
        }

    }

}