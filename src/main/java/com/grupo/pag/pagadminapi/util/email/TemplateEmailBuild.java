package com.grupo.pag.pagadminapi.util.email;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class TemplateEmailBuild {

    private TemplateEmailBuild() {}


    public static class Builder {
        private TipoTemplateEmail tipoTemplateEmail;

        private VelocityEngine velocityEngine;

        private HashMap<String, Object> varaveis;

        public   Builder(TipoTemplateEmail tipoTemplateEmail) {
            this.tipoTemplateEmail = tipoTemplateEmail;
            varaveis = new HashMap<>();
            velocityEngine = new VelocityEngine();
            velocityEngine.init();
        }

        public  Builder getInstance(){
            return this;
        }

        public Builder setVariavel(String nome, Object valor) {
            varaveis.put(nome, valor);
            return this;
        }

        private InputStream selecaoTemplateHtml() throws IOException {
            if (TipoTemplateEmail.CONFIRMACAO_EMAIL == tipoTemplateEmail) {
                return new ClassPathResource("templates/templateConfirmacaoEmail.html").getInputStream();
            }
            throw new RuntimeException(" Template não definido");
        }

        private Reader getTemplate() {
            try {
                return new BufferedReader(new InputStreamReader(selecaoTemplateHtml(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private VelocityContext getVelocityContext() {
            VelocityContext velocityContext = new VelocityContext();
            this.varaveis.entrySet().forEach(variavel -> velocityContext.put(variavel.getKey(), variavel.getValue()));
            return velocityContext;
        }

        public String getHtmlTemplate() {
            Reader template = getTemplate();
            StringWriter writer = new StringWriter();
            velocityEngine.evaluate(getVelocityContext(), writer, "log Tag", template);
            return writer.toString();
        }

    }

}
