package org.iit.dr.view.form.distribution.table;

import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.cell_editors.ComboBoxTableCellEditor;
import org.iit.dr.view.component.cell_editors.NotSelectableComboBoxEditor;
import org.iit.dr.view.component.cell_editors.SelectableComboBoxEditor;
import org.iit.dr.view.model.table.distribution.DistributionStudentsTableModel;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class DistributionStudentsTable extends JTableExt
{
  private static final long serialVersionUID = -9045290890876486966L;

  public DistributionStudentsTable( DistributionStudentsTableModel model )
  {
    super( model, DistributionStudentsTableModel.COLUMNS_PROPORTIONS );
    setCellSelectionEnabled( true );
    final ComboBoxTableCellEditor requestsEditor =
      new NotSelectableComboBoxEditor( null, "+ добавить", new CompaniesShortNamesSelectAction() );
    getColumnModel().getColumn( 4 ).setCellEditor( requestsEditor );
    final ComboBoxTableCellEditor distributionPlaceEditor =
      new SelectableComboBoxEditor( null, "другое", new CompaniesShortNamesSelectAction() );
    getColumnModel().getColumn( 5 ).setCellEditor( distributionPlaceEditor );

    requestsEditor.setComboBoxItemRemovable( true );
    distributionPlaceEditor.setComboBoxItemRemovable( false );
  }
}
