package locators;

public class ContactPage {

	 public String contactLink = "//a[text()='Contact']";
	 public String submit="//a[text()='Submit']";
	 public String foreName="//span[text()='Forename is required']";
	 public String email="//span[text()='Email is required']";
	 public String message="//span[text()='Message is required']";
	 public String fInput="//label[text()='Forename ']/following-sibling::div//input";
	 public String eInput="//label[text()='Email ']/following-sibling::div//input";
	 public String mInput="//label[text()='Message ']/following-sibling::div//textarea";
	 public String feedbackMessage="//strong[contains(text(),'Thanks')]//ancestor::div[1]";
	 public String invalidEmail="//span[text()='Please enter a valid email']";
}
