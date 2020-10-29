import java.security.MessageDigest;
import java.util.ArrayList;

public class ManejadorEncriptacion {
	
	public static ArrayList<String> arregloLetras1 = new ArrayList();
	public static ArrayList<String> arregloLetras2 = new ArrayList();
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
	
	public static String identificar_entrada(String hash, String algoritmo) throws Exception {
		String re = "";
		for(String s : arregloLetras1) {
			byte[] temp = generar_codigo(s,algoritmo);
			String stemp = imprimirHash(temp);
			if(stemp.equals(hash)) {
				re = s;
			}
		}
		if(re.equals("")) {
			for(String s : arregloLetras2) {
				byte[] temp = generar_codigo(s,algoritmo);
				String stemp = imprimirHash(temp);
				if(stemp.equals(hash)) {
					re = s;
				}
			}
		}
		return re;
	}
	
	public static void llenarArreglos() {
		for(int a = 97; a < 123; a++) {
			char c = (char)a;
			String sc = c+"";
			arregloLetras1.add(sc);
			for(int b = 97; b < 123; b++) {
				char c2 = (char)b;
				String sc2 = c+""+c2+"";
				arregloLetras2.add(sc2);
			}
		}
	}
	
	public static String imprimirHash(byte[] hash) {
		String pri = "";
		for(int i = 0; i < hash.length; i++) {
			if((hash[i] & 0xff) <= 0xf) {
				pri += "0";
			}
			pri += Integer.toHexString(hash[i] & 0xff).toLowerCase();
		}
		return pri;
	}	
	
	public static void main(String args[]) {
		llenarArreglos();
		String t = "ne";
		String a = "MD5";
		try {
			byte[] h = generar_codigo(t,a);
			String hash = imprimirHash(h);
			System.out.println(hash);
			System.out.println(identificar_entrada(hash,a));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
