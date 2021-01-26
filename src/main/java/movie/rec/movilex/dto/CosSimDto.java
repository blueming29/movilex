package movie.rec.movilex.dto;

public class CosSimDto {
	private Integer key;
	private double value;
	
	public CosSimDto() {
		// TODO Auto-generated constructor stub
	}

	public CosSimDto(Integer key, double value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	
}
