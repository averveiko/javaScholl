import ru.sbt.Plugin;

public class AnotherPlugin implements Plugin {
	@Override
	public void doUsefull() {
		System.out.println("Module " + this.getClass() + " running ...");
	}
}