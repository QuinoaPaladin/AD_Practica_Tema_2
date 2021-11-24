package es.studium.adpracticatema2;

public class Cliente
{
	private int idCliente;
	private String nombre;
	private String apellidos;
	private String email;
	private String DNI;
	private String clave;
	
	/* Constructor por defecto */
	  public Cliente () 
	  {
		  idCliente = 0;
		  nombre = "";
		  apellidos = "";	
		  email = "";
		  DNI = "";
		  clave = "";
	  }
	 
	  /* Constructor con parámetros */
	  public Cliente (int i, String n, String a, String e, String d, String c) 
	  {
		  idCliente = i;
		  nombre = n;
		  apellidos = a;	
		  email = e;
		  DNI = d;
		  clave = c;
	  }
	  
	  public int getidCliente() 
	  {
		  return idCliente;
	  }
	 
	  public void set(int i) 
	  {
		  idCliente = i;
	  }
	  
	  public String getNombre() 
	  {
		  return nombre;
	  }
	 
	  public void setNombre(String n) 
	  {
		  nombre = n;
	  }
	  public String getApellidos() 
	  {
		  return apellidos;
	  }
	 
	  public void setApellidos(String a) 
	  {
		  apellidos = a;
	  }
	  public String getEmail() 
	  {
		  return email;
	  }
	 
	  public void setEmail(String e) 
	  {
		  email = e;
	  }
	  public String getDNI() 
	  {
		  return DNI;
	  }
	 
	  public void setDNI(String d) 
	  {
		  DNI = d;
	  }
	  public String getClave() 
	  {
		  return clave;
	  }
	 
	  public void setClave(String c) 
	  {
		  clave = c;
	  }
}
