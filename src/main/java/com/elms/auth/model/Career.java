package com.elms.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "db_elms_career")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Career extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "career_name")
    private String careerName;
    @Column(name = "career_path_seo")  // name, path seo, address, logo, banner, hotline, setting, lattitude, longitude, lang
    private String careerPath;
    @Column(name = "address")
    private String address;
    @Column(name = "logo_path")
    private String logoPath;
    @Column(name = "banner_path")
    private String bannerPath;
    @Column(name = "hotline")
    private String hotline;
    @Column(name = "settings" ,  columnDefinition = "TEXT")
    private String settings;
    @Column(name = "lang")
    private String lang;
    private Double latitude;
    private Double longitude;
    @OneToOne(mappedBy = "career", fetch = FetchType.LAZY)
    private DbConfig dbConfig;
}
