package kata5;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class Kata5 {
	
	 // Cadena de conexión SQLite
	static String url = "jdbc:sqlite:KATA5.db";
	
	public static void main(String[] args) {
//		createNewDatabase("KATA5.db");
//		createNewTable();
//		
//		Kata5 idt = new Kata5();
//		// Se insertar 3 nuevas líneas
//		idt.insert("Yusra","Boutahar","DIS");
//		idt.insert("Pedro","Almodobar","PSOE");
//		idt.insert("Hasan","Boutahar","GUI");
//		
//		idt.selectAll();
		
		// Kata5P1: versión 2, crear tabla "EMAIL"
		// Instrucción SQL para crear  tabla EMAIL
		String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
						+ " id integer PRIMARY KEY AUTOINCREMENT,\n"
							+ " Mail text NOT NULL);";
		
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			// Se crea la nueva tabla
			stmt.execute(sql);
			System.out.println("Tabla creada");
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		// Kata5P1 versión 3:
		String dirEmails = "src/File/emailsfile.txt";
		List<String> lista = MailListReader.read(dirEmails);
		
		String sql1 = "INSERT INTO EMAIL(Mail) VALUES(?)";
		
		try (Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt = conn.prepareStatement(sql1)) {
			
			for(String s1: lista) {
				pstmt.setString(1, s1);
				pstmt.executeUpdate();	
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
	}
	
	public static void createNewDatabase(String filename) {
		String url = "jdbc:sqlite:" + filename ;
		
		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("El driver es " + meta.getDriverName());
				System.out.println("Se ha creado una nueva BD.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public static void createNewTable() {

		// Instrucción SQL para crear nueva tabla
		String sql = "CREATE TABLE IF NOT EXISTS PEOPLE (\n"
						+ " id integer PRIMARY KEY AUTOINCREMENT,\n"
							+ " Name text NOT NULL, \n"
								+ "Apellidos text NOT NULL, \n"
									+ "Departamento text NOT NULL);";
		
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			// Se crea la nueva tabla
			stmt.execute(sql);
			System.out.println("Tabla creada");
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// Método para insertar datos en la tabla direcc_email
	public void insert(String Name, String Apellidos, String Dept) {
		String sql = "INSERT INTO PEOPLE(Name,Apellidos,Departamento) VALUES(?,?,?)";
		
		try (Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, Name);
			pstmt.setString(2, Apellidos);
			pstmt.setString(3, Dept);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectAll() {
		String sql = "SELECT * FROM PEOPLE";
		
		try (Connection conn = DriverManager.getConnection(url);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)){
			
			// Bucle sobre el conjunto de resgistros.
			while(rs.next()) {
				System.out.println( rs.getInt("id") + "\t" +
									rs.getString("Name") + "\t" +
									rs.getString("Apellidos") + "\t" +
									rs.getString("Departamento") + "\t");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
