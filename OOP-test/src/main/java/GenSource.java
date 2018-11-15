
public class GenSource {
	public Source getRandomSource() {
		return new Source(new Source().getRandomLink(), new Source().getRandomTimeExtracted());
	}
}
