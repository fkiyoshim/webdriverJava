package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;

//@RunWith(DataDrivenTestRunner.class)
//@DataLoader(filePaths = "teste.csv")

public class InformacoesUsuariosPageObjectsTest {
    private WebDriver navegador ;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarumaInformacaoAdicionaldoUsuario() {
        String textoToast = new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin("julio0001","123456")
                .clicarMe()
                .clicarAbaMoreDataPage()
                .clicarNoBotaoAddMoreDataAbaoutYou()
               .adicionarContato("Phone","+551933336666")
            .capturarTextToast();

        assertEquals("Your contact has been added!", textoToast);
    }

@After
    public void tearDown() {
            navegador.quit();
        }
    }