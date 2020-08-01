package io.github.mat3e.Todo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



//przy towrzeniu nowej klasy mapujacej tabele w sql musimy dodac
// ja do pliku hibernate.cfg.xml !!!

@Entity
@Table(name = "todo")
class Todo {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String text;
    private boolean done;


    /**
     * Hinernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    public Todo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
