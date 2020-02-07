package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Screenshot;
import suporte.Web;
import suporte.generator;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "asa.csv")
public class informacoesUsuarioTest{
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        //abrindo o navegador
       navegador = Web.createChrome();

        //clicar no campo login que está dentro do formlario de id signinbox
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //digitar  no campo login que está dentro do formlario de id signinbox o texto julio0001
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //digitar no campo name password que está dentro do formlario de id signinbox o texto 123456
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //clicar no botão sign in
        navegador.findElement(By.linkText("SIGN IN")).click();

        // validar que estou logado na página

        //clicar em um link que possui a class me
        navegador.findElement(By.className("me")).click();

        //clicar em um link que possui o texto more data about you
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdicionarumaInformacaoAdicionaldoUsuario( @Param(name="tipo")String tipo,@Param(name="contato")String contato,@Param(name="mensagem")String mensagemEsperada){
        //clicar no botão add more data pelo xpath
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //identificar a popup onde esta o furmlario de id more data
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // clicar no combo type  e escolher a opção fone
        WebElement campoType =  popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // no campo "contact" digitar um telefone +5519 999990000
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // clicar no link save que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //na mensagem de id "toast-container" que o texto "Your contact has been added"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
       assertEquals(mensagemEsperada, mensagem);
   }

   //@Test
   public void  removerumContatoDeUmUsuario(){
    // clicar no elemento pelo xpath //span[text()="+551923232121"]/following-sibling::a
    navegador.findElement(By.xpath("//span[text()=\"+551923232121\"]/following-sibling::a")).click();

    // confirmar a janela javascript
    navegador.switchTo().alert().accept();

    // validar que a mensagem apresentada foi "Rest in peace, dear phone"
       WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
       String mensagem = mensagemPop.getText();
       assertEquals("Rest in peace, dear phone!", mensagem);

        //tirando print do cenário
       String screenshotArquivo = "/home/franciscom/screeshotStep/" + test.getMethodName() + generator.dataHoraParaArquivo() + ".png";
       Screenshot.tirar(navegador,screenshotArquivo);

       // aguardar ate 10s para que a janela desapareça
       WebDriverWait aguardar = new WebDriverWait(navegador,10);
       aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

       // clicar no link com o texto logout
        navegador.findElement(By.linkText("Logout")).click();
   }


   @After
    public void tearDown(){
       //fechar o navegador
       navegador.quit();
   }

}
