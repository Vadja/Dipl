package org.iit.dr.view.form.distribution.table;

import org.iit.dr.view.component.JTableExt;
import org.iit.dr.view.component.cell_editors.ComboBoxTableCellEditor;
import org.iit.dr.view.component.cell_editors.NotSelectableComboBoxEditor;
import org.iit.dr.view.model.table.distribution.DistributionCompaniesTableModel;

/**
 * 
 * @author Uladzimir_Babkou
 * 
 */
public class DistributionCompaniesTable extends JTableExt
{
  private static final long serialVersionUID = -291468281884598083L;

  public DistributionCompaniesTable( DistributionCompaniesTableModel model )
  {
    super( model, DistributionCompaniesTableModel.COLUMNS_PROPORTIONS );

    setCellSelectionEnabled( true );
    final ComboBoxTableCellEditor requestsEditor = new NotSelectableComboBoxEditor();
    getColumnModel().getColumn( 3 ).setCellEditor( requestsEditor );

    final ComboBoxTableCellEditor distributionEditor = new NotSelectableComboBoxEditor();
    getColumnModel().getColumn( 4 ).setCellEditor( distributionEditor );

    requestsEditor.setComboBoxItemRemovable( false );
    distributionEditor.setComboBoxItemRemovable( false );
  }
}
