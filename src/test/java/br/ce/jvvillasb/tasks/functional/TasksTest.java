package br.ce.jvvillasb.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAaplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAaplicacao();

		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescrição() {
		WebDriver driver = acessarAaplicacao();

		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAaplicacao();

		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAaplicacao();

		try {
			// clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// fechar o browser
			driver.quit();
		}
	}
}
