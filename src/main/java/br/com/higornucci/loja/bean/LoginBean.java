package br.com.higornucci.loja.bean;

import br.com.higornucci.loja.dao.UsuarioDao;
import br.com.higornucci.loja.modelo.Usuario;
import br.com.higornucci.loja.util.RedirectView;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class LoginBean {

    private Usuario usuario = new Usuario();

    public RedirectView efetuaLogin() {
        System.out.println("Fazendo login do usuário "
                + this.usuario.getEmail());

        FacesContext context = FacesContext.getCurrentInstance();
        boolean existe = new UsuarioDao().existe(this.usuario);

        if (existe) {
            context.getExternalContext().getSessionMap()
                    .put("usuarioLogado", this.usuario);
            return new RedirectView("livro");
        }
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Usuário não encontrado"));
        return new RedirectView("login");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String deslogar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";
    }
}
