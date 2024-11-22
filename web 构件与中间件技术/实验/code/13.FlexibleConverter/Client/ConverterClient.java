import FlexConverter.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

public class ConverterClient{
	public static void main(String[] args){
		try{
			Context initial = new InitialContext();
			ConverterHome converterHome = (ConverterHome)PortableRemoteObject.narrow(
				initial.lookup("Converter"), ConverterHome.class);
//			System.out.println("look up OK");
//			System.exit(0);
			Converter converter = converterHome.create();
//			System.out.println("create OK");
			double amount = converter.dollarToYen(100.00);
			System.out.println("$100 equals Yen " + String.valueOf(amount));
			amount = converter.yenToEuro(100.00);
			System.out.println("Yen 100 equals Euro " + String.valueOf(amount));
			converter.remove();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
