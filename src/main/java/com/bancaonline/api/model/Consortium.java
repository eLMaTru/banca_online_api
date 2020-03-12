package com.bancaonline.api.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

import java.time.LocalDateTime;

/**
 * The Consortium class.
 */
@Entity
@Table(name = "consortium")
public class Consortium implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "consortium_id")
    private Long id;

    @Column(length = 55)
    private String name;

    @Column(length = 300)
    private String note;

    @Column(name = "allowed_ips")
    private int allowedIps;

    @Column(name = "s3_bucket_name")
    @NotBlank(message = "s3BucketName can't be null or empty")
    private String s3BucketName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(length = 300)
    private String logo;

    /**
     * Instantiates a new Consortium.
     */
    public Consortium() {

    }

    /**
     * Instantiates a new Consortium by id.
     *
     * @param id the id
     */
    public Consortium(Long id) {
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
     * Gets note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets allowed ips.
     *
     * @return the allowed ips quantity
     */
    public int getAllowedIps() {
        return allowedIps;
    }

    /**
     * Sets allowed ips.
     *
     * @param allowedIps the allowed ips quantity
     */
    public void setAllowedIps(int allowedIps) {
        this.allowedIps = allowedIps;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets logo.
     *
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo.
     *
     * @param logo the logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }

    @Override
    public String toString() {
        return "Consortium [allowedIps=" + allowedIps + ", createdDate=" + createdDate + ", id=" + id + ", logo=" + logo
                + ", name=" + name + ", note=" + note + "]";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
