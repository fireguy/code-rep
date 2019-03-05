package hellocucumber;

import hellocucumber.IsItFriday;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

public class Stepdefs {
	private String today;
	private String actualAnswer;

	@Given("^today is Sunday$")
	public void today_is_Sunday() {
		today = "Sunday";
	}

	@When("^I ask whether it's Friday yet$")
	public void i_ask_whether_it_s_Friday_yet() {
		actualAnswer = IsItFriday.isItFriday(today);
	}

	@Then("^I should be told \"([^\"]*)\"$")
	public void i_should_be_told(String expectedAnswer) {
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	@Given("^today is Friday$")
	public void today_is_Friday() {
	    this.today = "Friday";
	}
	
	@Given("^today is \"([^\"]*)\"$")
	public void today_is(String today) throws Exception {
		this.today = today;
	}

	
}