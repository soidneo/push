package com.mitocode.bean;

import com.mitocode.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@RequestScoped
public class PersonaBean {

    private Persona persona = new Persona();
    private static List<Persona> lista = new ArrayList();
    private String summary;
    private final static String CHANNEL = "/notify";
    private String detail;

    public List<Persona> getLista() {
        return lista;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setLista(List<Persona> lista) {
        PersonaBean.lista = lista;
    }

    public void agregar() {
        PersonaBean.lista.add(persona);
        notificarPUSH();
    }
     public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
     
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void notificarPUSH() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml4(summary), StringEscapeUtils.escapeHtml4(detail)));
    }
}
