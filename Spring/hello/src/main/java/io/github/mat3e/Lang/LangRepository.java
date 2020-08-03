package io.github.mat3e.Lang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LangRepository extends JpaRepository<Lang, Integer> {
    //Już ma stworzone proste metody np jak findAll()

    List<Lang> findByCodeIsContaining(String codeFragment); // po nazwie metody spring potrafi zdefiniowac sql pod spodem
    //ta metoda wyszuka wszytkie obiekty których code zawiera w sobie podaną literkę

    List<Lang> findByCodeAndWelcomeMsg(String code, String welcomeMsg);

}
