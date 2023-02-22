package com.chillmo.developer.tournamentApp.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This class represents a Token.
 */
@Getter
@Setter
@Table(name = "token")
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String tokenContent;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userID")
    private User user;

    /**
     * Token constructor.
     *
     * @param tokenContent   Token tokenContent
     * @param expiresAt      Token expiresAt
     * @param user           Token user
     */
    public Token(final String tokenContent, final LocalDateTime expiresAt, final User user) {
        this.tokenContent = tokenContent;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    protected Token() {

    }
}

