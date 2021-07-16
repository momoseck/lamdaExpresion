package lambdaEx;

@FunctionalInterface
public interface Operator {
 public void operate(int num);
 public default int getTaxRate() {
	 return 0;
 }
}
