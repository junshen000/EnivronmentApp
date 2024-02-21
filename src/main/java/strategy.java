
public interface strategy {
	public result calculate(selection selection_a) throws nameException;
	public Data read_data(selection selection_b, String name) throws nameException;
}
