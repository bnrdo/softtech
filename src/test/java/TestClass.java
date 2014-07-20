import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;


public class TestClass {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//System.out.println(PasswordUtil.generateDefaultPassword());
		//System.out.println((new Date()).getTime());
		int x = 555;
		System.out.println(new DecimalFormat("000000000000000").format((new Date()).getTime()));
	}
	

}
