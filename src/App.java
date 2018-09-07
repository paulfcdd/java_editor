import EditorPackage.Editor;

/**
 * @author Paul Novikov (paulfcdd@gmail.com)
 * @version 1.0
 */
public class App {

    private Editor editor;

    private App() {
        editor = new Editor();
    }

    public static void main(String[] args) {
        App app = new App();
        app.editor.run();
     }
}
