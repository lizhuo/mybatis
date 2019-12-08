package com.lyx.selenium;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author lizhuo
 * @Description: Selenium junit 测试类
 * @date 2019-12-01 18:33
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTest extends TestCase {

	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("../chromedriver.exe"))
				.usingAnyFreePort()
				.build();
		service.start();
	}

	@AfterClass
	public static void createAndStopService() {
		//service.stop();
	}

	@Before
	public void createDriver() {
		driver = new RemoteWebDriver(service.getUrl(),
				DesiredCapabilities.chrome());
	}

	@After
	public void quitDriver() {
		driver.quit();
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {
		// 设置环境变量：chrome驱动位置
		System.out.println(System.getProperty("user.dir"));
		//System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

		driver.get("http://www.baidu.com/");

		WebElement searchBox = driver.findElement(By.id("kw"));
		Thread.sleep(3000); // wait 3s

		searchBox.sendKeys("惠州学院");
		Thread.sleep(3000); // wait 3s

		searchBox.submit(); // 提交查询内容
		Thread.sleep(3000);

		driver.quit(); // 驱动退出
	}
}