package io.github.mat3e.Todo;

import io.github.mat3e.HibernateUtil;

import java.util.List;
import java.util.Optional;

class TodoRepository {

    List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(Todo.class, id);
        result.setDone(!result.getDone());

        transaction.commit();

        session.close();
        return result;
    }

    Todo addTodo(Todo newTodo) {

        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        if (newTodo.getText().equals("")){
            transaction.setRollbackOnly();
            session.close();
        }

        session.persist(newTodo);   //zapisanie nowego obiektu w bazie
        transaction.commit();
        session.close();
        return newTodo;
    }



}
