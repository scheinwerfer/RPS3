import com.github.freva.asciitable.AsciiTable;
import java.util.List;

public class Table {

    private final List<String> options;
    private final int argsLength;

    public Table(List<String> options, int argsLength) {
        this.options = options;
        this.argsLength = argsLength;
    }

    public void drawTable() {
        Rules rules = new Rules();
        String[][] data = new String[options.size() + 1][options.size() + 1];
        for (int i = 0, m = 1; i < options.size(); i++, m++) {
            for (int j = 0, n = 1; j < options.size(); j++, n++) {
                data[0][n] = options.get(j);
                data[n][0] = options.get(j);
                data[m][n] = rules.whoWin( i, j, argsLength);
            }
        }
        data[0][0] = "USER / PC";
        System.out.println(AsciiTable.getTable(data));
    }
}