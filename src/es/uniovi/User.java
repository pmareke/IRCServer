package es.uniovi;
import java.net.Socket;

public class User {
	private Socket socket;
	private String nick;
	private Boolean connected;
	
	/**
	 * Constructor para crear un usuario conectado
	 * @param nick Nick del usuario
	 * @param socket Socket al que esta conectado
	 */
	public User(String nick, Socket socket) {
		this.nick = nick;
		this.socket = socket;
		this.connected = true;
	}
	
	/**
	 * Cambiar el estado de conexion del usuario
	 * @param state Estado de conexion del usuario
	 */
	public void setConnected(Boolean state) {
		this.connected = state;
	}
	
	/**
	 * Obtener el estado de conexion de un usuario
	 * @return Estado de conexion
	 */
	public Boolean getConnected() {
		return connected;
	}
	
	/**
	 * Obtener el socket del usuario
	 * @return Socket que usa el usuario
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Obtiene informacion del usuario y del socket al que se conecta de una forma unica para
	 * todo el servidor
	 * @return String con la informacion
	 */
	public String getCompleteInfo() {
		return this.getNick()+"@"+this.getSocket().getInetAddress().getHostAddress()+":"+this.getSocket().getPort();
	}
	
	/**
	 * Obtener el nick del usuario
	 * @return String del nick
	 */
	synchronized public String getNick() {
		return nick;
	}
	
	/**
	 * Metodo para cambiar el nick del usuario. Es un cambio a bajo nivel, 
	 * no se modifican otros objetos.
	 * 
	 * Debe ser llamado desde la clase GlobalObject para asegurar sincronismo.
	 * @param nick Nuevo nick
	 */
	public synchronized void setNick(String nick) {
		if (nick.length() > 0) {
			this.nick = nick;
		}
	}
	
	/**
	 * Comprobacion a muy bajo nivel de que el usuario es valido
	 * No comprueba todas las posibilidades pero al nivel en que se usara
	 * es suficiente.
	 * @return Boolean indicando la validez
	 */
	public Boolean isValid() {
		// Es valido si el nick tiene letras y el socket no esta cerrado
		if ((this.nick.length() > 0) && (this.socket.isClosed() == false)) {
			return true;
		}
		
		return false;
	}
}
