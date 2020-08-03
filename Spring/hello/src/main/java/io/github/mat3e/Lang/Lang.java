package io.github.mat3e.Lang;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "languages")
public class Lang {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    @Column(name = "welcomemsg")
    //musimy zmienić ponieważ spring przyjął że nasz welcomeMsg w bazie dnaych jest zapisany jako welcome_msg
    private String welcomeMsg;
    private String code;


    /**
     * Hinernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    public Lang() {
    }

    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMsg = welcomeMsg;
        this.code = code;
    }


    public Integer getId() {
        return id;
    }


    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
