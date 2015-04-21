package todochallenge.dto;

public class Todo {
	private int idtodo;
	private String note;
	private String status;
	
	public Todo(){}
	
	public Todo(int idtodo, String note, String status) {
		super();
		this.idtodo = idtodo;
		this.note = note;
		this.status = status;
	}

	public int getIdtodo() {
		return idtodo;
	}

	public void setIdtodo(int idtodo) {
		this.idtodo = idtodo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
