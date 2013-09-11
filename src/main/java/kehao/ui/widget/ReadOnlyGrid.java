package kehao.ui.widget;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ReadOnlyGrid extends JTable {

  private DefaultTableModel model;

  public ReadOnlyGrid(String[] columns) {
    model = new ReadOnlyTableModel(columns, 0);
    setModel(model);
    setMultiSelection(false);
    setAutoSort(true);
    setColumnReorderingAllowed(false);
  }

  public void setMultiSelection(boolean enabled) {
    if(enabled) {
      setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    } else {
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
  }

  public void setAutoSort(boolean enabled) {
    setAutoCreateRowSorter(enabled);
  }

  public void setColumnReorderingAllowed(boolean enabled) {
    getTableHeader().setReorderingAllowed(enabled);
  }

  public void addRow(String[] row){
    model.addRow(row);
  }

  public void removeRow(int index) {
    model.removeRow(index);
  }

  public void clear() {
    model.setRowCount(0);
  }

  @Override
  public void setEnabled(boolean enabled) {
    if(!enabled) {
      setBackground(Color.lightGray);
      setForeground(Color.darkGray);
    } else {
      setBackground(null);
      setForeground(null);
    }
    super.setEnabled(enabled);
  }

  public void onSelect(ListSelectionListener listener) {
    getSelectionModel().addListSelectionListener(listener);
  }
}
