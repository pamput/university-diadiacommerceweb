/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistenza.postgresql;

import java.sql.SQLException;
import persistenza.AccountsHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modello.Cliente;
import persistenza.Facade;
import persistenza.PersistenceException;
/**
 *
 * @author Kimo
 */
public class AccountsHandlerpostgresql extends AccountsHandler{

    //Dati i dati, inserisce un nuovo account nel DB
    public void addAccount(String codiceCliente,String user, String password, String role) throws PersistenceException {
        DataSource dataSource = new DataSource();
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            Facade facade = new Facadepostgresql();
            Cliente cliente = facade.getClientePerCodice(codiceCliente);
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO accounts " +
                    "(idcliente, username, password, tipo) " +
                    "VALUES (?,?,?,?);");
            statement.setInt(1, cliente.getId());
            statement.setString(2, user);
            statement.setString(3, string2md5(password));
            statement.setString(4, role);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            try {
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
        }
    }

    //Effettua l'autenticazione di un utente dati usename e password, e ne restituisce il ruolo (in caso di autenticazione negativa restituisce null)
    public String authenticate(String username,String password) throws PersistenceException{
        String role = null;
        DataSource dataSource = new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
        try {
			//Connessione al DB
			connection = dataSource.getConnection();
			//Preparazione query
			String query = "SELECT * "+
						   "FROM accounts "+
						   "WHERE username=?";
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			//Interrogazione DB
			result = statement.executeQuery();
			if (result.next())
                if( result.getString("password").equals(string2md5(password)))
                    role = result.getString("tipo");
		}catch (Exception e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				//Chiusura connessione al DB
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
        return role;
    }

    //Dato un username verifica se e gia presente nel DB
    public boolean usernamePresente(String username) throws PersistenceException{
        boolean usernamePresente = false;
        DataSource dataSource = new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
        try {
			//Connessione al DB
			connection = dataSource.getConnection();
			//Preparazione query
			String query = "SELECT * "+
						   "FROM accounts "+
						   "WHERE username=?";
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			//Interrogazione DB
			result = statement.executeQuery();
			if (result.next())
                usernamePresente = true;
		}catch (Exception e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				//Chiusura connessione al DB
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
        return usernamePresente;
    }

    //Dato un codiceCliente verifica se e effettivamente valido
    public boolean codiceClienteValido(String codiceCliente) throws PersistenceException{
        boolean codiceValido = false;
        DataSource dataSource = new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
        try {
			//Connessione al DB
			connection = dataSource.getConnection();
			//Preparazione query
			String query = "SELECT id "+
						   "FROM clienti "+
						   "WHERE codice=?";
			statement = connection.prepareStatement(query);
			statement.setString(1,codiceCliente);
			//Interrogazione DB
			result = statement.executeQuery();
			//Verifica se il codice specificato corrisponde effettivamente ad un cliente
            if (result.next()){
                //Verifica che non esista un account gia collegato all'utente specificato
                int idCliente = result.getInt("id");
                query = "SELECT * "+
						"FROM accounts "+
						"WHERE idcliente=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1,idCliente);
                result = statement.executeQuery();
                if (!result.next())
                    codiceValido = true;
            }
		}catch (Exception e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				//Chiusura connessione al DB
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
        return codiceValido;
    }

    //Dato un username ritorna il codice del cliente associato, in caso di esito negativo ritorna null
    public String retrieveCodiceClienteByUsername(String username) throws PersistenceException{
        String codiceCliente = null;
        DataSource dataSource = new DataSource();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
        try {
			//Connessione al DB
			connection = dataSource.getConnection();
			//Preparazione query
			String query = "SELECT codice "+
						   "FROM clienti, accounts "+
						   "WHERE clienti.id = accounts.idcliente AND username=?";
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			//Interrogazione DB
			result = statement.executeQuery();
			if (result.next())
                codiceCliente = result.getString("codice");
		}catch (Exception e){
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				//Chiusura connessione al DB
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
        return codiceCliente;
    }
}