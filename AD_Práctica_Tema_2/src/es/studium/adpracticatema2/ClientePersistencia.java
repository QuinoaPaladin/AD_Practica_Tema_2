package es.studium.adpracticatema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class ClientePersistencia
{

	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) 
	{
		/* Devuelve el id del nuevo cliente */
		
		int id = (int) -1L;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC", "root", "Studium2020;");		
			
			PreparedStatement p = dbcon.prepareStatement("insert into clientes (nombre, apellidos, email, dni, clave) " + "values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		    p.setString(1, nombre);
		    p.setString(2, apellidos);
		    p.setString(3, email);
		    p.setString(4, dni);
		    p.setString(5, clave);
		    p.executeUpdate();		    
		   
		    ResultSet rs = p.getGeneratedKeys();
		    if (rs.next()) 
		    {
		        id = rs.getInt(1);
		        //System.out.println("El ID del nuevo cliente es: " + id + " (línea 38)");
		        return id;
		    }
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return id;
		
	}

	public static String readCliente(int idCliente, String campo) 
	{
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
		String valor = "";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC", "root", "Studium2020;");
			
			Statement sentencia = dbcon.createStatement();
			String ins = "SELECT" + " " + campo + " " + " FROM clientes WHERE idCliente =" + "" + idCliente;
			ResultSet resultado = sentencia.executeQuery("select " + campo + " from clientes WHERE idCliente = " + idCliente);
			// Mostrar los datos
			if (resultado.next()) 
			{
				if (resultado.wasNull())
				{
					valor = "No existen los apellidos";
				}
				
				valor = resultado.getString(campo);				
			}
			dbcon.close();
		} 
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
		return valor;
}

public static boolean updateCliente(int idCliente, String campo, String nuevoValor) 
{
	/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. */
	boolean action = false;
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC", "root", "Studium2020;");
		
		Statement sentencia = dbcon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String ins = "UPDATE clientes SET " + campo + " ='"+ nuevoValor +"'" + " WHERE idCliente=" + idCliente + ";";
		
		sentencia.executeUpdate(ins);
		
		if (sentencia.executeUpdate(ins) <= 0) 
		{
			action = false;
			//System.out.println(action + " (linea 102)");		
		}
		
		else 
		{
			action = true;
			//System.out.println(action + " (linea 108)");		
		}
		dbcon.close();
		
	}	
	catch (ClassNotFoundException e)
	{
		e.printStackTrace();
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}
	return action;	
}

public static boolean deleteCliente(int idCliente) 
{
	/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
	
	boolean action = false;
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC", "root", "Studium2020;");
		
		//Statement sentencia = dbcon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		//String ins = "DELETE FROM clientes WHERE idCliente = " + idCliente + ";";
			
		PreparedStatement p = dbcon.prepareStatement("DELETE FROM clientes WHERE idCliente =?");
		p.setInt(1, idCliente);
		int deleted = p.executeUpdate();
		
			if (deleted != 0 )
			{
				//System.out.println("Se ha borrado de la BD");
				action = true;
				//System.out.println(action);
			}		
		
	}	
		catch (ClassNotFoundException e)
	{
			e.printStackTrace();
	} 
		catch (SQLException e)
	{
			e.printStackTrace();
	}	
	return action;
	}
	
}
