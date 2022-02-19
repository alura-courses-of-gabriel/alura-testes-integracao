package br.com.alura.leilao.dao.utils;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoTestBuilder {
    private Leilao leilao;

    public LeilaoTestBuilder(){
        this.leilao = new Leilao();
    }
    public LeilaoTestBuilder comNome(String mochila) {
        leilao.setNome(mochila);
        return this;
    }

    public LeilaoTestBuilder comValor(String valor) {
        this.leilao.setValorInicial(new BigDecimal(valor));
        return this;
    }

    public LeilaoTestBuilder comUsuario(Usuario usuario) {
        this.leilao.setUsuario(usuario);
        return this;
    }

    public LeilaoTestBuilder comData(LocalDate date) {
        this.leilao.setDataAbertura(date);
        return this;
    }

    public Leilao build() {
        return this.leilao;
    }
}
