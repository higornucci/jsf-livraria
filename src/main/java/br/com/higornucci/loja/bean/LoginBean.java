package br.com.higornucci.loja.bean;

import br.com.higornucci.loja.dao.UsuarioDao;
import br.com.higornucci.loja.modelo.Usuario;
import br.com.higornucci.loja.util.RedirectView;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LoginBean {

    private Usuario usuario = new Usuario();

    public RedirectView efetuaLogin() {
        System.out.println("Fazendo login do usuário "
                + this.usuario.getEmail());

        boolean existe = new UsuarioDao().existe(this.usuario);

        if (existe) {
            return new RedirectView("livro");
        }
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
