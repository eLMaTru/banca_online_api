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
 * TokenType class
 */
@Entity
@Table(name = "token_type")
public class TokenType implements Serializable {

    private static final long serialVersionUID = -6678713752525421210L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "token_type_id")
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(length = 300)
    private String description;

    public TokenType() {

    }

    public TokenType(Long id) {
        this.id = id;
    }

    public TokenType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * The enum Type.
     */
    public enum Type {

        /**
         * For production environment.
         */
        PRODUCTION(1),
        /**
         * For test environment.
         */
        TEST(2);

        private final long id;

        Type(int id) {
            this.id = id;
        }

        /**
         * As array production long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayProduction() {
            return new Long[] { PRODUCTION.getId() };
        }

        /**
         * As array test long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayTest() {
            return new Long[] { TEST.getId() };
        }

        /**
         * As array all long [ ].
         *
         * @return the long [ ]
         */
        public static Long[] asArrayAll() {
            return new Long[] { PRODUCTION.getId(), TEST.getId() };
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
         * To TokenType.
         *
         * @return the TokenType
         */
        public TokenType toTokenType(Long id) {
            return new TokenType(id);
        }

    }

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

}
