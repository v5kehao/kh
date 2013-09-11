package kehao.ui.widget;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ReadOnlyTableModel extends DefaultTableModel {

  public ReadOnlyTableModel() {
  }

  public ReadOnlyTableModel(int rowCount, int columnCount) {
    super(rowCount, columnCount);
  }

  public ReadOnlyTableModel(Vector columnNames, int rowCount) {
    super(columnNames, rowCount);
  }

  public ReadOnlyTableModel(Object[] columnNames, int rowCount) {
    super(columnNames, rowCount);
  }

  public ReadOnlyTableModel(Vector data, Vector columnNames) {
    super(data, columnNames);
  }

  public ReadOnlyTableModel(Object[][] data, Object[] columnNames) {
    super(data, columnNames);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
