package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import junit.framework.Assert;

public class ClienteTest {

	private HttpServer server;

	@Before
	public void iniciaServidor() {
		server = Servidor.inicializaServidor();
	}

	@After
	public void interrompeServidor() {
		server.stop();
	}

	@Test
	public void testaBuscarCarrinho() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");

		String conteudo = target.path("/carrinhos").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

		System.out.println(conteudo);

	}

}
