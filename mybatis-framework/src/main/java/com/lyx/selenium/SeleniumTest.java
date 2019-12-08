package com.lyx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author lizhuo
 * @Description: Selenium 普通测试类
 * @date 2019-12-01 18:33
 */
public class SeleniumTest {

	public static void main(String args[]) throws InterruptedException {
		System.out.println("haha");
		System.out.println(System.getProperty("user.dir"));
		SeleniumTest seleniumTest = new SeleniumTest();
		seleniumTest.queryBaidu();
	}

	public void queryBaidu() throws InterruptedException {
		// 设置环境变量：chrome驱动位置
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

		WebDriver driver = new ChromeDriver(); // 创建驱动
		driver.get("http://www.baidu.com/");
		Thread.sleep(3000); // wait 3s

		WebElement searchBox = driver.findElement(By.id("kw"));
		searchBox.sendKeys("李燕贤");
		Thread.sleep(3000); // wait 3s

		searchBox.submit(); // 提交查询内容
		Thread.sleep(3000);

		driver.quit(); // 驱动退出
	}

}
