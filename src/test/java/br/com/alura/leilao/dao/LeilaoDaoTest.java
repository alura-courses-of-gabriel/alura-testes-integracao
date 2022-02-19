package br.com.alura.leilao.dao;

import br.com.alura.leilao.dao.utils.JPAUtils;
import br.com.alura.leilao.dao.utils.LeilaoTestBuilder;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager entityManager;



    @BeforeEach
    void setUp() {
        this.entityManager = JPAUtils.getEntityManager();
        this.dao = new LeilaoDao(entityManager);
        this.entityManager.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        this.entityManager.getTransaction().rollback();
    }

    @Test
    public void deveInserirUmLeilao(){
        Usuario usuario = criarUsuario();
        Leilao leilao = new LeilaoTestBuilder()
                .comNome("Mochila")
                .comValor("70")
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .build();

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        assertNotNull(salvo);
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario("Fulano", "fulano@email.com", "123456789");
        entityManager.persist(usuario);
        System.out.println(usuario.getId());
        return usuario;
    }

}