package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;


public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);
		
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia(){
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar primeiroMes = new GregorianCalendar(2015, 10, 8, 8, 30);
		Calendar segundoMes = new GregorianCalendar(2015, 6, 8, 8, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, primeiroMes);
		Assert.assertFalse(negociacao.isMesmoDia(segundoMes));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		Calendar primeiroAno = new GregorianCalendar(2015, 10, 8, 8, 30);
		Calendar segundoAno = new GregorianCalendar(2014, 10, 8, 8, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, segundoAno);
		Assert.assertFalse(negociacao.isMesmoDia(primeiroAno));
	}

}
