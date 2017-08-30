package br.com.higornucci.loja.tx;

import br.com.higornucci.loja.dao.Transacional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

    @Inject private EntityManager manager;

    @AroundInvoke
    public Object executaTX(InvocationContext contexto) throws Exception {
        manager.getTransaction().begin();

        Object resultado = contexto.proceed();

        manager.getTransaction().commit();

        return resultado;
    }
}
