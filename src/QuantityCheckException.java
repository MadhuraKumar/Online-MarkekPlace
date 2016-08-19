/**
 * @author Madhura 
 * This class create a custom exception.
 *
 */

public class QuantityCheckException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private int quant;
	
	public QuantityCheckException(int quant){
		super();
		this.quant = quant;
	}
	
	
	@Override
	public String getMessage(){
		return "Quantity should be less than or equal to " + quant;
	}
	

}
