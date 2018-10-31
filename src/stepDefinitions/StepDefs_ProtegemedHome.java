package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import seleniumPages.Page_ProtegemedHome;

public class StepDefs_ProtegemedHome {

	Page_ProtegemedHome protegemedHomepage = new Page_ProtegemedHome();
	
	@Given("^Abrir o browser Chrome$")
	public void abrir_o_browser_chrome() throws Exception {
		protegemedHomepage.abrirBrowser();
	}

	@When("^Abrir a Home do Protegemed$")
	public void abrir_a_home_do_protegemed() throws Exception {
		protegemedHomepage.openProtegemedHomeURL();
	}

	@Then("^Verificar que os elementos de input estao presentes$")
	public void verificar_que_os_elementos_de_input_estao_presentes() throws Exception {
		protegemedHomepage.verificarElementosDeInput();
	}

	@Then("^preencher os elementos com valores$")
	public void preencher_os_elementos_com_valores() throws Exception {
		protegemedHomepage.setValoresEventos();
	}

	@Then("^clicar no botao enviar$")
	public void clicar_no_botao_enviar() throws Exception {
		protegemedHomepage.checkImFeelingLuckyButtonIsDisplayed();
	}
	
	@Then("^abrir arquivo de log para frequencia$")
	public void abrir_arquivo_de_log_para_frequencia() throws Exception {
		protegemedHomepage.openFileLogFrequencia();
	}
	
	@Then("^abrir arquivo de log para corrente$")
	public void abrir_arquivo_de_log_para_corrente() throws Exception {
		protegemedHomepage.openFileLogCorrente();
	}
}
