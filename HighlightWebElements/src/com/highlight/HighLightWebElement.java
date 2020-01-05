// ©2019 Martin Holmes
 
package com.highlight;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class HighLightWebElement extends Frame implements ActionListener, WindowListener, ItemListener {
	
	private static String driverPath = "C:\\Martin_Holmes_Files\\Downloads\\chromedriver.exe";
	private static String homePage = "file:///C:/Martin_Holmes_Files/Test%20HTML/Website/XPath.html";
	private static String versionNumber = "v2.0.1";

	private Label lblXpath1; 
	private Label lblFound1;
	private Label lblFoundTotal1;
	private Button btnRunxPath1; 
	private TextField tfXpath1; 
	private TextField tfMax1; 

	private Label lblXpath2; 
	private Label lblFound2; 
	private Label lblFoundTotal2;
	private TextField tfXpath2; 
	private TextField tfMax2; 
	private Checkbox cbRun2;
	
	private Label lblSleep; 
	private Label lblCopyright;
	private TextField tfSleep; 

	private Button btnWebDriver; // Declare a Button component

	public static WebDriver driver = null;
	public WebElement lastElement;

	public HighLightWebElement() {

		setLayout(null);
		// ========================================================
		// Add and position the components

		int Row1 = 50;
		int Row2 = 90;

		// -------
		// Row 1
		lblXpath1 = new Label("xPath 1");
		lblXpath1.setBounds(20, Row1, 40, 20);
		add(lblXpath1);

		tfXpath1 = new TextField("/html/body/form/*", 40);
		tfXpath1.setBounds(70, Row1, 400, 20);
		add(tfXpath1);

		tfMax1 = new TextField("10", 10);
		tfMax1.setBounds(480, Row1, 20, 20);
		add(tfMax1);

		btnRunxPath1 = new Button("run xPath");
		btnRunxPath1.setBounds(510, Row1, 100, 20);
		add(btnRunxPath1);

		lblFound1 = new Label("Items Found = ", Label.LEFT);
		lblFound1.setBounds(100, Row1 + 20, 200, 20);
		add(lblFound1);
		
		lblFoundTotal1 = new Label("Total = ", Label.LEFT);
		lblFoundTotal1.setBounds(320, Row1 + 20, 200, 20);
		add(lblFoundTotal1);

		// -------
		// Row 2
		lblXpath2 = new Label("xPath 2");
		lblXpath2.setBounds(20, Row2, 40, 20);
		add(lblXpath2);

		tfXpath2 = new TextField(".//*", 40);
		tfXpath2.setBounds(70, Row2, 400, 20);
		tfXpath2.setEnabled(false);
		add(tfXpath2);

		tfMax2 = new TextField("10", 10);
		tfMax2.setBounds(480, Row2, 20, 20);
		add(tfMax2);
		
		cbRun2 = new Checkbox("Run2",false);
		cbRun2.setBounds(510,Row2, 10,20);
		cbRun2.setState(false);
		add(cbRun2);

		lblFound2 = new Label("Items Found = ", Label.LEFT);
		lblFound2.setBounds(100, Row2 + 20, 200, 20);
		add(lblFound2);
		
		lblFoundTotal2 = new Label("Total = ", Label.LEFT);
		lblFoundTotal2.setBounds(320, Row2 + 20, 200, 20);
		add(lblFoundTotal2);


		// --------
		// Sleep
		lblSleep = new Label("Sleep /ms", Label.LEFT);
		lblSleep.setBounds(20, Row2 + 50, 60, 20);
		add(lblSleep);
		
		tfSleep = new TextField("1000", 50);
		tfSleep.setBounds(90, Row2 + 50, 50, 20);
		add(tfSleep);
		
		lblCopyright = new Label("© 2019 Martin Holmes  " + versionNumber);
		lblCopyright.setBounds(150, Row2 + 50, 200, 20);
		add(lblCopyright);
		
		// --------
		// Click button
		btnWebDriver = new Button("click()"); // construct the Button component
		btnWebDriver.setBounds(510, Row2 + 50, 50, 20);
		add(btnWebDriver); // "super" Frame container adds Button component

		// Set Listeners
		btnRunxPath1.addActionListener(this);
		btnWebDriver.addActionListener(this);
		cbRun2.addItemListener(this);
		addWindowListener(this);

		// Set Window Position
		setTitle("Highlight Web Elements");
		setSize(800, 200);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	// =====================================================================
	public static void main(String[] args) {
		new HighLightWebElement();

		//System.setProperty("webdriver.chrome.driver", "C:\\Martin_Holmes_Files\\Downloads\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		//driver.get("file:///C:/Martin_Holmes_Files/Test%20HTML/Website/BrentfordBank.html");
		driver.get(homePage); 

	}

	// =====================================================================
	// Highlight WebElements
	public void highlightWebElement(WebDriver driver, WebElement myTest, String highlightColour) {

		int w = myTest.getSize().getWidth() + 8;
		int h = myTest.getSize().getHeight() + 8;
		//String hs = myTest.getCssValue("height"); 
		//int h = Integer.parseInt(hs);
		int t = myTest.getLocation().getY() - 6;
		int l = myTest.getLocation().getX() - 8;
		String tagName = myTest.getTagName();
		String id = myTest.getAttribute("id");
		String eClass = myTest.getAttribute("class");
		String text = myTest.getText();
		text = text.replaceAll("\n", "?"); // replace carriage returns
		int textLength = text.length();
		if(textLength > 30) {
			textLength = 30;
			text = text.substring(0, textLength-1);
		}
		

		String labelText = tagName + "#" + text + " ";
		System.out.println("TAG=" + tagName + "  ID=" + id + "   CLASS=" + eClass + "    TEXT='" + text + "'");

		int labelWidth = labelText.length() * 10;
		int labelHeight = 20;
		int labelTop = t - 20;
		int labelLeft = l + 5;

		if (t < 20) {
			labelTop = t + h + 3;
		}

		// System.out.println(myTest.getAttribute("id") + "Width = " + w);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//System.out.println("HERE >>> 1");
		js.executeScript(" " + "var btn=document.createElement('DIV');" + "btn.id = 'Border';"
				+ "btn.style = 'border:3px solid " + highlightColour + ";" + " position: absolute;" + "width: " + w
				+ "px; " + "height:" + h + "px; " + "top:" + t + "px; " + "left:" + l + "px; z-index: 1000';"
				+ "document.body.appendChild(btn);");
		//System.out.println("HERE >>> 2");
		js.executeScript(" " + "var btn=document.createElement('DIV');" + "btn.id = 'Label';"
				+ "btn.style = 'color: white; font-size:15px; background-color: black; border:2px solid " + "black" + ";"
				+ " position: absolute;" + "width: " + labelWidth + "px; " + "height:" + 18 + "px; " + "top:" + labelTop
				+ "px; " + "left:" + labelLeft + "px; z-index: 1000';" + "document.body.appendChild(btn);");
		//System.out.println("HERE >>> 3");
		js.executeScript(
				"var element = document.getElementById('Label');" + "element.innerHTML = '" + labelText + "';");
		//System.out.println("HERE >>> 4");
		int sleep = Integer.parseInt(tfSleep.getText());
		sleepTime(sleep);
		//js.executeScript("var int = document.getElementById('Border').  alert('hi');");

		js.executeScript(
				"var element = document.getElementById('Border');" + "element.parentNode.removeChild(element);");
		//System.out.println("HERE >>> 5");
		js.executeScript(
				"var element = document.getElementById('Label');" + "element.parentNode.removeChild(element);");
		//System.out.println("HERE >>> 6");

	}

	protected static void sleepTime(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(cbRun2.getState()) {
			tfXpath2.setEnabled(true);
		} else {
			tfXpath2.setEnabled(false);
		}
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e.getActionCommand());
		tfXpath1.setEnabled(false);
		tfXpath2.setEnabled(false);
		System.out.println("-----------------------------------------------------");

		int numberFound1 = 0;
		int numberFound2 = 0;
		int numberVisible1 = 0;
		int numberVisible2 = 0;

		String xPath1 = tfXpath1.getText();
		String xPath2 = tfXpath2.getText();
		Boolean runXpath2 = true;
		if (xPath2.contentEquals("") | !cbRun2.getState()) {
			runXpath2 = false;
		}

		int max1 = Integer.parseInt(tfMax1.getText());
		int max2 = Integer.parseInt(tfMax2.getText());

		switch (e.getActionCommand()) {
		case "run xPath":
			tfXpath1.setEnabled(false);

			lblFound1.setText("No Items Found!");
			lblFound2.setText("No Items Found!");

			try {
				List<WebElement> elem1 = driver.findElements(By.xpath(xPath1));

				numberFound1 = elem1.size();
				lblFoundTotal1.setText("Total = " + numberFound1);
				if (numberFound1 > max1) {
					numberFound1 = max1;
				}

				for (int i = 0; i < numberFound1; i++) {
					if (elem1.get(i).isDisplayed()) {
						numberVisible1++;
						lblFound1.setText("Found " + numberFound1 + "(" + numberVisible1 + ")");
						highlightWebElement(driver, elem1.get(i), "blue");
						lastElement = elem1.get(i);

						if (runXpath2) {
							List<WebElement> elem2 = elem1.get(i).findElements(By.xpath(xPath2));

							numberFound2 = elem2.size();
							lblFoundTotal2.setText("Total = " + numberFound2);
							numberVisible2 = 0;
							if (numberFound2 > max2) {
								numberFound2 = max2;
							}
							for (int j = 0; j < numberFound2; j++) {
								if (elem2.get(j).isDisplayed()) {
									numberVisible2++;
									lblFound2.setText("Found " + numberFound2 + "(" + numberVisible2 + ")");
									highlightWebElement(driver, elem2.get(j), "green");
									lastElement = elem2.get(j);
									//System.out.println(" sdf ");
								} else {
									System.out.println(elem2.get(j).getTagName() + " is not visible");
								}
							}
							lblFound2.setText("Found " + numberFound2 + "(" + numberVisible2 + ")");
						}
					} else {
						System.out.println(elem1.get(i).getTagName() + " is not visible");
					}

				}


			} catch (Exception e1) {
				System.out.println("could not find" + e1.getMessage());
				lblFound1.setText("ERROR - Refresh Page and try again");
			}
			// tfXpath.setEnabled(true);
			break;
		case "click()":
			System.out.println("Click Tried");
			highlightWebElement(driver, lastElement, "blue");
			lastElement.click();


			break;
		}
		sleepTime(500);
		tfXpath1.setEnabled(true);
		if(cbRun2.getState()) {
			tfXpath2.setEnabled(true);
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		driver.quit();
		System.exit(0);
		// driver.quit();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
