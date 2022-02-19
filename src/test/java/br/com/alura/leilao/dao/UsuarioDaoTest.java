package br.com.alura.leilao.dao;

import br.com.alura.leilao.dao.utils.JPAUtils;
import br.com.alura.leilao.model.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

class UsuarioDaoTest {

    private UsuarioDao dao;

    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.entityManager = JPAUtils.getEntityManager();
        this.dao = new UsuarioDao(entityManager);
        this.entityManager.getTransaction().begin();


    }

    @AfterEach
    void tearDown() {
        this.entityManager.getTransaction().rollback();
    }

    @Test
    public void deveEncontrarUsuarioPorNome() {
        Usuario usuario = new Usuario("fulano", "email@email.com", "123456789");
        this.entityManager.persist(usuario);

        Usuario retornado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(retornado);
    }

    @Test
    public void deveRemoverUmUsuario(){
        Usuario usuario = new Usuario("fulano", "email@email.com", "123456789");
        this.entityManager.persist(usuario);

        dao.deletar(usuario);

        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
    }

    @Test
    public void naoDeveEncontrarUsuarioInexistente() {
        Usuario usuario = new Usuario("fulano", "email@email.com", "123456789");
        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername(usuario.getNome()));
    }


}