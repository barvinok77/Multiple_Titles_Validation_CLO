package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Multiple_Titles_CLO {

	@Parameter(names = { "-l", "--list" }, variableArity = true, description = "Your list")
	static List<String> list = new ArrayList<String>();

	@Parameter(names = "--help", help = true, hidden = true)
	static boolean help;

	public static void main(String[] args) {

		JCommander clo = new JCommander(new Multiple_Titles_CLO(), args);

		if (help) {
			clo.usage();
			System.exit(0);
		}

		if (list.size() == 0) {
			System.err.println("List is empty");
			System.exit(1);
		}
			
		WebDriver driver = new FirefoxDriver();

		for (String l : list) {
			System.out.println("");
			System.out.println("List item = " + l);
			
			String text_case_id = "TC-001.0" + (list.indexOf(l) + 1);
			
			String param[] = l.split("\\|");

			String url = param[0];
			String title_expected = param[1];

			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();
			
			if (title_expected.equals(title_actual)) {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "PASSED");
			} else {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "FAILED");
			}

		}

		driver.quit();

	}

}
