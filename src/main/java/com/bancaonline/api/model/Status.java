package com.bancaonline.api.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Status.
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7941769011539363186L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "status_id")
    private Long id;

    @Column(length = 55)
    private String name;

    @Column(length = 300)
    private String description;

    /**
     * Instantiates a new Status.
     */
    public Status() {

    }

    /**
     * Instantiates a new Status.
     *
     * @param id the id
     */
    public Status(Long id) {
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

    /**
     * The enum Type.
     */
    public enum Type {

        ENABLED(1),
        /**
         * Disabled type.
         */
        DISABLED(2),
        /**
         * Deleted type.
         */
        DELETED(3),
        /**
         * wined type.
         */
        WINED(4),
        /**
         * not wined type.
         */
        NOT_WINED(5);

        private final long id;

        Type(int id) {
            this.id = id;
        }

        /**
         * As array enabled long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayEnabled() {
            return new Long[] { ENABLED.getId() };
        }

        /**
         * As array disabled long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayDisabled() {
            return new Long[] { DISABLED.getId() };
        }

        /**
         * As array wined long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayWined() {
            return new Long[] { WINED.getId() };
        }

        /**
         * As array not wined long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayNotWined() {
            return new Long[] { NOT_WINED.getId() };
        }

        /**
         * As array all long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayAll() {
            return new Long[] { ENABLED.getId(), DISABLED.getId() };
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
